package com.hcis.ipanther.common.security.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.common.security.utils.RoleConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.dao.RoleDao;
import com.hcis.ipanther.common.security.dao.RolePriDao;
import com.hcis.ipanther.common.security.dao.UserRoleDao;
import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IModuleService;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.common.security.utils.JsonModule;
import com.hcis.ipanther.common.security.utils.SecurityConstants;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
	@Resource
	private RoleDao baseDao;
	@Resource
	private RolePriDao rolePriDao;
	@Resource
	private UserRoleDao userRoleDao;
	@Autowired
	private IModuleService moduleService;
	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#list(SearchParam searchParam)
	 */
	@Override
	public List<Role> list(SearchParam searchParam) {
		String moduleId=CommonConfig.getProperty("app.module.id");
		String[] moduleIds=moduleId.split(";");
		if(null!=moduleIds){
			Module module=moduleService.read(moduleIds[moduleIds.length-1]);
			if(StringUtils.equals(module.getType(), SecurityConstants.MODULE_TYPE_SLAVE)){
				searchParam.getParamMap().put("module", module.getId());
			}
			List<Role> list = baseDao.selectBySearchParam(searchParam);
			return list;
		}
		return null;
	}

	public Role view(Role role){
		SearchParam searchParam=new SearchParam();
		Pagination p=new Pagination();
		JSONArray jsonArray=null;
		role=this.read(role.getId());
		if(StringUtils.isNotBlank(role.getModule())){
			StringBuffer moduleName=new StringBuffer();
			StringBuffer moduleId=new StringBuffer();
			moduleId.append("'");
			jsonArray=JSONArray.fromObject(role.getModule());
			for(int i=0;i<jsonArray.size();i++){
				String id=jsonArray.getJSONObject(i).get("module").toString();
				moduleId.append(id).append("','");
			}
			moduleId.append("-1'");
			p.setAvailable(false);
			searchParam.setPagination(p);
			searchParam.getParamMap().put("moduleIds", moduleId.toString());
			List<Module> list=moduleService.list(searchParam);
			for(Module m:list){
				moduleName.append(m.getName()).append(" ");
			}
			role.setModule(moduleName.toString());
		}
		return role;
	}
	

//	/* (non-Javadoc)
//	 * @see com.hcis.ipanther.core.service.IBaseService#delete(java.lang.Object)
//	 */
//	@Override
//	public int delete(Role role) {
//		//删除角色对应的功能关系
//		rolePriDao.deleteByRoleId(role.getId());
//		//删除角色与用户的对应关系
//		userRoleDao.deleteByRoleId(role.getId());
//		//删除角色
//		int delRoleNum=baseDao.deleteByPrimaryKey(role);
//		
//		return delRoleNum;
//	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#batchDelete(java.util.List,java.lang.String)
	 */
	
	@Override
	public int save(Role role, HttpServletRequest request) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		if(role!=null){
			//把role.module字段转成json数组格式
			if(StringUtils.isNotBlank(role.getModule())){
				JSONArray jsonArray=new JSONArray();
				String[] module=role.getModule().split(",");
				JsonModule jm=new JsonModule();
				for(String m:module){
					jm.setModule(m.trim());
					jsonArray.add(JSONObject.fromObject(jm));
				}
				role.setModule(jsonArray.toString());
			}
			
			//更新
			if(StringUtils.isNotEmpty(role.getId())){
				role.setUpdateTime(new Date());
				if(loginUser!=null){
					role.setUpdatedby(loginUser.getId());
				}
				return baseDao.updateByPrimaryKeySelective(role);
			}else{
				role.setDefaultValue();
				String uuid = Identities.uuid2();
				role.setId(uuid);
				if(loginUser!=null){
					role.setCreator(loginUser.getId());
				}
				return baseDao.insertSelective(role);
			}
		}
    	return 0;
	}

	@Override
	public boolean checkRoleName(String roleName, String oldRoleName) {
		int count =0;
		SearchParam s=new SearchParam();
		s.getParamMap().put("roleName", roleName);
		s.getParamMap().put("oldRoleName", oldRoleName);
		String countStr =baseDao.selectCountByName(s);
		if(null!=countStr&&!countStr.equals("")){
			count=Integer.valueOf(countStr);
		}
		return count>0?false:true;
	}

	//根据角色查询角色所有的功能权限
	public List<Role> allRolePrivilege(){
		return baseDao.selectAllRolePrivilege();
	}

	
	@Override
	public List<Role> selectByMap(Map<String, Object> map) {
		return baseDao.selectByMap(map);
	}

	@Override
	public Role read(String id) {
		return baseDao.selectByPrimaryKey(id);
	}

	@Override
	public int create(Role role, String creator) {
	   if(role!=null){
			role.setDefaultValue();
			role.setId(Identities.uuid2());
			role.setCreator(creator);
			return baseDao.insertSelective(role);
	   }
	   return 0;
	}

	@Override
	public int update(Role role, String updateBy) {
		if(role!=null){
			role.setUpdateTime(new Date());
			role.setUpdatedby(updateBy);
			return baseDao.updateByPrimaryKeySelective(role);
		}
		return 0;
	}

	@Override
	public int delete(Role role, String updateBy) {
		if(role!=null){
			role.setUpdateTime(new Date());
			role.setUpdatedby(updateBy);
			
			//删除角色对应的功能关系
			rolePriDao.deleteByRoleId(role.getId());
			//删除角色与用户的对应关系
			userRoleDao.deleteByRoleId(role.getId());
			//删除角色
			return baseDao.deleteByLogic(role);
		}
		return 0;
	}
	
	@Override
	public Role getRoleByCode(String roleCode) {
		Role role = null;
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("roleCode", roleCode);
		List<Role> roles = this.selectByMap(args);
		if(roles.size()>0) {
			role = roles.get(0);
		}
		return role;
	}

	@Override
	public Map<String, Role> select2Map(SearchParam searchParam) {

		List<Role> list = list(searchParam);
		Map<String, Role> reMap = new HashMap<>();
		for(Role role : list){
			if(!RoleConstant.SUPER_ADMIN.equals(role.getRoleCode())){
				reMap.put(role.getName(), role);
			}
		}

		return reMap;
	}

	@Override
	public RoleDao getBaseDao() {
		return baseDao;
	}

	@Override
	public int batchDelete(List<Role> baseForms, String id) {
		return 0;
	}
	
}
