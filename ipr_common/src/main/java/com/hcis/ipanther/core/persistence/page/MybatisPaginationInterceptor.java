
package com.hcis.ipanther.core.persistence.page;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.hibernate.dialect.Dialect;

import com.hcis.ipanther.core.page.IPage;
import com.hcis.ipanther.core.page.Pagination;

/**
 * 覆盖com.hcis.ipanther.core.persistence.page.MybatisPaginationInterceptor
 * 原文件不兼容H2数据库
 * @author wuwentao
 * @date 2015年8月12日
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }) })
public class MybatisPaginationInterceptor implements Interceptor {

	Dialect dialect;
	
	String databaseType;

	public Object intercept(Invocation invocation) throws Throwable {
		Object parameterObject = invocation.getArgs()[1];
		if (parameterObject != null && parameterObject instanceof IPage) {
			IPage page = (IPage) parameterObject;
			if (page.getPagination() == null
					|| page.getPagination().isAvailable()) {
				return pageIntercept(invocation);
			}
		}

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		String dialectClass = properties.getProperty("dialectClass");
		databaseType = properties.getProperty("databaseType");
		try {
			dialect = (Dialect) Class.forName(dialectClass).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Object pageIntercept(Invocation invocation) throws Throwable {
		int rowCount = 0;
		MappedStatement mappedStatement = (MappedStatement) invocation
				.getArgs()[0];
		Object parameterObject = invocation.getArgs()[1];
		SqlSource sqlSource = getCountSqlSource(mappedStatement,
				parameterObject);
		MappedStatement newMappedStatement = copyMappedStatementBySqlSource(
				mappedStatement, sqlSource);
		Connection connection = newMappedStatement.getConfiguration()
				.getEnvironment().getDataSource().getConnection();
		BoundSql bs = sqlSource.getBoundSql(parameterObject);

		List<ParameterMapping> pmLs = bs.getParameterMappings();

		for (ParameterMapping pm : pmLs) {
			String propertyName = pm.getProperty();
			if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)) {
				bs.setAdditionalParameter(propertyName, mappedStatement
						.getSqlSource().getBoundSql(parameterObject)
						.getAdditionalParameter(propertyName));
			}
		}

		DefaultParameterHandler dp = new DefaultParameterHandler(
				mappedStatement, parameterObject, bs);
		PreparedStatement countStmt = connection.prepareStatement(bs.getSql());
		dp.setParameters(countStmt);
		ResultSet rs = countStmt.executeQuery();
		if (rs.next()) {
			rowCount = rs.getInt(1);
		}
		rs.close();
		countStmt.close();
		connection.close();

		if (parameterObject != null && parameterObject instanceof IPage) {
			IPage page = (IPage) parameterObject;
			if (page.getPagination() == null) {
				page.setPagination(new Pagination());
			}
			page.getPagination().setRowCount(rowCount);
			page.getPagination().pagination();
		}

		sqlSource = getPageLimitSqlSource(mappedStatement, parameterObject);
		bs = sqlSource.getBoundSql(parameterObject);

		pmLs = bs.getParameterMappings();
		for (ParameterMapping pm : pmLs) {
			String propertyName = pm.getProperty();
			if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)) {
				bs.setAdditionalParameter(propertyName, mappedStatement
						.getSqlSource().getBoundSql(parameterObject)
						.getAdditionalParameter(propertyName));
			}
		}

		newMappedStatement = copyMappedStatementBySqlSource(mappedStatement,
				new BoundSqlSqlSource(bs));
		invocation.getArgs()[0] = newMappedStatement;
		return invocation.proceed();
	}

	private String getMapperSQL(MappedStatement mappedStatement,
			Object parameterObject) throws Throwable {
		SqlSource nowSqlSource = mappedStatement.getSqlSource();
		Class<?> sqlSourceType = nowSqlSource == null ? Object.class
				: nowSqlSource.getClass();
		Field rootSqlNodeField = sqlSourceType.getDeclaredField("rootSqlNode");
		rootSqlNodeField.setAccessible(true);
		SqlNode sqlNode = (SqlNode) rootSqlNodeField.get(nowSqlSource);
		DynamicContext context = new DynamicContext(
				mappedStatement.getConfiguration(), parameterObject);
		sqlNode.apply(context);
		return context.getSql();
	}

	private SqlSource getCountSqlSource(MappedStatement mappedStatement,
			Object parameterObject) throws Throwable {
		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(
				mappedStatement.getConfiguration());
		String mapperSQL = getMapperSQL(mappedStatement, parameterObject);
		Class<?> parameterType = parameterObject == null ? Object.class
				: parameterObject.getClass();
		String newSql = "select count(1) from ( " + mapperSQL + " ) t";
		Map<String, Object> additionalParameters = new HashMap<String, Object>();
		List<ParameterMapping> pmLs = mappedStatement.getParameterMap()
				.getParameterMappings();
		for (ParameterMapping pm : pmLs) {
			String propertyName = pm.getProperty();
			if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)) {
				additionalParameters.put(propertyName, mappedStatement
						.getSqlSource().getBoundSql(parameterObject)
						.getAdditionalParameter(propertyName));
			}
		}
		return sqlSourceParser.parse(newSql, parameterType,
				additionalParameters);
	}

	private SqlSource getPageLimitSqlSource(MappedStatement mappedStatement,
			Object parameterObject) throws Throwable {
		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(
				mappedStatement.getConfiguration());
		String mapperSQL = getMapperSQL(mappedStatement, parameterObject);
		Class<?> parameterType = parameterObject == null ? Object.class
				: parameterObject.getClass();
		String newSql = "";
		if (parameterObject != null && parameterObject instanceof IPage) {
			IPage page = (IPage) parameterObject;
			newSql = getLimitString(mapperSQL, page.getPagination()
					.getCurrentMinRow(), page.getPagination()
					.getCurrentMaxRow());
		}
		Map<String, Object> additionalParameters = new HashMap<String, Object>();
		List<ParameterMapping> pmLs = mappedStatement.getParameterMap()
				.getParameterMappings();
		for (ParameterMapping pm : pmLs) {
			String propertyName = pm.getProperty();
			if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)) {
				additionalParameters.put(propertyName, mappedStatement
						.getSqlSource().getBoundSql(parameterObject)
						.getAdditionalParameter(propertyName));
			}
		}
		return sqlSourceParser.parse(newSql, parameterType,
				additionalParameters);
	}

	private MappedStatement copyMappedStatementBySqlSource(
			MappedStatement mappedStatement, SqlSource sqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(
				mappedStatement.getConfiguration(), mappedStatement.getId(),
				sqlSource, mappedStatement.getSqlCommandType());
		builder.resource(mappedStatement.getResource());
		builder.fetchSize(mappedStatement.getFetchSize());
		builder.statementType(mappedStatement.getStatementType());
		builder.keyGenerator(mappedStatement.getKeyGenerator());

		String[] keyProperties = mappedStatement.getKeyProperties();
		if (keyProperties != null && keyProperties.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String prop : keyProperties) {
				sb.append(prop).append(",");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			builder.keyProperty(sb.toString());
		}
		builder.timeout(mappedStatement.getTimeout());
		builder.parameterMap(mappedStatement.getParameterMap());
		builder.resultMaps(mappedStatement.getResultMaps());
		builder.cache(mappedStatement.getCache());
		MappedStatement newMappedStatement = builder.build();
		return newMappedStatement;
	}

	private String getLimitString(String sql, int offset, int limit) {

		String whereLowerCase = null;
		String finalSql = null;
		// String sqlLowerCase = sql.trim().toLowerCase();

		int whereIndex = sql.indexOf("where");
		if (whereIndex > 0) {
			whereLowerCase = sql.substring(whereIndex);
		}

		String limitArgs[] = new String[] { String.valueOf(limit),
				String.valueOf(offset) };
		String newSql = dialect.getLimitString(sql, offset, limit);
		String sqls[] = newSql.split("\\?");
		String querySQL = "";
		if (sqls.length == 1) {
			querySQL = querySQL + " " + sqls[0] + limit;
		} else {
			if(StringUtils.isNotEmpty(databaseType)&&(databaseType.equalsIgnoreCase("MYSQL"))){
				//querySQL = querySQL+" "+sqls[0]+String.valueOf(limit-offset)+sqls[1]+limitArgs[1];
				querySQL = querySQL+" "+sqls[0]+limitArgs[1]+","+String.valueOf(limit-offset);
			}else if(StringUtils.isNotEmpty(databaseType)&&(databaseType.equalsIgnoreCase("H2"))){//增加对H2数据库的兼容_wuwentao
				querySQL = querySQL+" "+sqls[0]+String.valueOf(limit-offset)+sqls[1]+limitArgs[1];
			}else{
				for (int i = 0; i < 2; i++) {				
					querySQL = querySQL + " " + sqls[i] + limitArgs[i];
				}
			}
			
		}

		querySQL = querySQL.trim();

		if (whereLowerCase == null || "".endsWith(whereLowerCase)) {
			return querySQL;
		}

		int finalWhereIndex = querySQL.indexOf(whereLowerCase);

		if (finalWhereIndex > 0) {
			String begin = querySQL.substring(0, finalWhereIndex);
			String end = querySQL.substring(finalWhereIndex
					+ whereLowerCase.length());
			finalSql = begin + sql.substring(whereIndex) + end;
		}
		return finalSql;

	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

}