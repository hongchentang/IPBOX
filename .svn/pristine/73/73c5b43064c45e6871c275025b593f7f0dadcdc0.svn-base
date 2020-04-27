package com.hcis.ipr.warehouse.patentHouse.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.bson.Document;
import org.springframework.stereotype.Service;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.warehouse.patentHouse.dao.PatentMongoDao;
import com.hcis.ipr.warehouse.patentHouse.entity.PatentMongo;
import com.hcis.ipr.warehouse.patentHouse.service.IPatentMongoService;
import net.sf.json.JSONObject;

@Service("patentMongoService")
public class PatentMongoServiceImpl implements IPatentMongoService{
	
	@Resource
	private PatentMongoDao patentMongoDao;
	
	/**
	 * 增加
	 * @param PatentMongo
	 * @param loginUser
	 * @return
	 */
	@Override
	public int addPatentMongo(PatentMongo patentMongo,LoginUser loginUser){
		return 1;
	}
	
	/**
	 * 数据列表
	 * @param searchParam
	 * @return
	 */
	@Override
	public List<PatentMongo> selectBySearchParam(SearchParam searchParam){
//		return null;
		return patentMongoDao.selectBySearchParam(searchParam);
	}
	

	/**
	 * 逻辑删除记录
	 * @param userQuestion
	 * @return
	 */
	@Override
	public int deleteByLogic(PatentMongo patentMongo){
		return 200;
	}
	
	@Override
	public List<PatentMongo> list(SearchParam searchParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatentMongo read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(PatentMongo obj, String creator) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(PatentMongo obj, String updateBy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(PatentMongo obj, String updateBy) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据某个字段查找出符合的所有记录
	 * @param map
	 * @return
	 */
	@Override
	public List<PatentMongo> selectByMap(Map<String,Object> map){
		return null;
	}

	/**
	 * 计算出不同类型的数据数量
	 * @param map
	 * @return
	 */
	@Override
	public int selectCount(Map<String,Object> map){
		return 200;
	}
	/**
	 * 根据用户关键字进行查询
	 * @param SearchParam
	 * @return JSONArray json数组数据类型
	 */
	@Override
	public Map<String, Object> selectJsonSearchParam(int pageNumber,int pageSize,SearchParam searchParam){
		return patentMongoDao.selectJsonSearchParam(pageNumber,pageSize,searchParam);
	}
	
	@Override
	public JSONObject selectByPatentId(SearchParam searchParam){
		return patentMongoDao.selectByPatentId(searchParam);
	}
	@Override
	public void insertMany(String type,List<Document> documents){
		patentMongoDao.insertMany(type,documents);
	}

    @Override
    public JSONObject selectByPatentByAppNumber(String appNumber) {
        return patentMongoDao.selectByPatentByAppNumber(appNumber);
    }

    @Override
    public List<Document> getLegalListByIds(List<String> mongoPatentIdList) {
        return patentMongoDao.selectByPatentIds(mongoPatentIdList);
    }

}
