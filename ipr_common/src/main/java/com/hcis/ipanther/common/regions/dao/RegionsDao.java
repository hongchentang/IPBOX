/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 20132013-3-12
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
*************************************************/
package com.hcis.ipanther.common.regions.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class RegionsDao extends MyBatisDao {
	
	public List<Regions> selectCityCounties(String provinceCode){
		return this.selectForList(namespace+".selectCityCounties", provinceCode);
	}
	
	public int deleteByLogic(Regions regions){
		
		return this.update(namespace+".deleteByLogic",regions);
	}

	//根据名称模糊匹配
	public List<Regions> selectByMap(Map map){
		return this.selectForList(namespace+".selectByMap", map);
	}
	//递归
	public Object selectUserByProvinceAndCity(Map map){
		return this.selectOne(namespace+".selectUserByProvinceAndCity", map);
	}
}
