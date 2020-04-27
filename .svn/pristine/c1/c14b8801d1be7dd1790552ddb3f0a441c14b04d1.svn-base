package com.hcis.ipr.warehouse.patentHouse.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.bson.Document;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.warehouse.patentHouse.entity.PatentMongo;




public interface IPatentMongoService extends IBaseService<PatentMongo>{
	/**
	 * 增加
	 * @param PatentMongo
	 * @param loginUser
	 * @return
	 */
	public int addPatentMongo(PatentMongo patentMongo,LoginUser loginUser);
	
	/**
	 * 数据列表
	 * @param searchParam
	 * @return
	 */
	public List<PatentMongo> selectBySearchParam(SearchParam searchParam);
	
	
	
	/**
	 * 逻辑删除记录
	 * @param userQuestion
	 * @return
	 */
	public int deleteByLogic(PatentMongo patentMongo);
	
	/**
	 * 根据某个字段查找出符合的所有记录
	 * @param map
	 * @return
	 */
	public List<PatentMongo> selectByMap(Map<String,Object> map);

	/**
	 * 计算出不同类型的数据数量
	 * @param map
	 * @return
	 */
	public int selectCount(Map<String,Object> map);
	
	/**
	 * 根据参数查询数据内容 2019-06-05
	 * @author zonggaojin
	 * @param searchParam
	 * @return 返回JSONArray类型
	 */
	public Map<String, Object> selectJsonSearchParam(int pageNumber,int pageSize,SearchParam searchParam);

	public JSONObject selectByPatentId(SearchParam searchParam);
	
	public void insertMany(String type,List<Document> documents);

	JSONObject selectByPatentByAppNumber(String appNumber);

	/**
	 *
	 * @param mongoPatentIdList
	 * @return
	 */
    List<Document> getLegalListByIds(List<String> mongoPatentIdList);
}
