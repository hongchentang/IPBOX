package com.hcis.ipr.wechat.menu.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
/**
 * 微信后台管理-菜单管理
 * @author chenjiajing
 * @date 2016年10月28日
 */
@Repository
public class MenuDao extends MyBatisDao{
	/**
	 * 微信菜单列表
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> selectListMenu(Map<String, Object> parameters) {
		return this.getSqlSession().selectList(namespace+".selectListMenu",parameters);
	}
	/**
	 * 计算菜单数量
	 * @param parameters
	 * @return
	 */
	public int countByMap(Map<String, Object> parameters) {
		return (int) this.selectForOneVO("countByMap", parameters);
	}
}
