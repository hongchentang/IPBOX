package com.hcis.ipr.wechat.menu.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.wechat.menu.dao.MenuDao;
import com.hcis.ipr.wechat.menu.entity.Menu;
import com.hcis.ipr.wechat.menu.service.IMenuService;
import com.hcis.ipr.wechat.menu.utils.MenuUtils;
@Service("menuService")
public class MenuServiceImpl extends  BaseServiceImpl<Menu> implements IMenuService{
	@Autowired
	private MenuDao baseDao;
	
	@Autowired
	private ApiConfig apiConfig;

	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}
	
	@Override
	public List<Map<String, Object>> treeView(SearchParam searchParam) {
		Map<String, Object> map=searchParam.getParamMap();
		List<Map<String, Object>> list=baseDao.selectListMenu(map);
		return list;
	}
	@Override
	public String save(Menu menu,String operatorId) {
		String id = menu.getId();
		String parentId = menu.getParentId();
		String menuKey = menu.getMenuKey();
		if(StringUtils.isNotEmpty(menuKey)) {//判断key是否有相同的
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("notId", id);
			parameters.put("menuKey", menuKey);
			if(this.countByMap(parameters)>0) {
				return "菜单的KEY已经存在";
			}
		}
		if(StringUtils.isEmpty(parentId)) {//一级菜单，检查菜单数量是否超过3个
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("isParent", true);
			parameters.put("notId", id);
			if(this.countByMap(parameters)>=3) {
				return "一级菜单数量已满";
			}
		} else {//二级菜单，检查菜单数量是否超过5个
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("parentId", parentId);
			parameters.put("notId", id);
			if(this.countByMap(parameters)>=5) {
				return "二级菜单数量已满";
			}
		}
		if(StringUtils.isNotEmpty(id)) {//更新
			this.update(menu, operatorId);
		} else {//新增
			this.create(menu, operatorId);
		}
		return null;
	}
	
	public int countByMap(Map<String, Object> parameters){
		return baseDao.countByMap(parameters);
	}

	@Override
	public String generate() {
		com.github.sd4324530.fastweixin.api.entity.Menu _menu = new com.github.sd4324530.fastweixin.api.entity.Menu();
		_menu.setMenuId("zsitlmsMenu");
		List<MenuButton> btns = new ArrayList<MenuButton>();
		_menu.setButton(btns);
		
		/*
		 * 处理一级菜单
		 */
		SearchParam searchParam = new SearchParam(false);
		searchParam.getParamMap().put("isParent", true);//只查询一级菜单
		List<Menu> parentMenus = this.list(searchParam);
		Map<String,MenuButton> menuButtonMap = new HashMap<String,MenuButton>();//key为菜单的ID，value为微信菜单组
		for (Menu menu : parentMenus) {
			MenuButton btnGroup = MenuUtils.createMenu(MenuUtils.getMenuType(menu.getType()), menu.getMenuKey(), menu.getName(), menu.getUrl());
			menuButtonMap.put(menu.getId(), btnGroup);
			btns.add(btnGroup);
		}
		/*
		 * 处理二级菜单
		 */
		searchParam = new SearchParam(false);
		searchParam.getParamMap().put("isParent", false);//只查询二级菜单
		List<Menu> menus = this.list(searchParam);
		for (Menu menu : menus) {
			MenuButton parentMenu = menuButtonMap.get(menu.getParentId());
			List<MenuButton> subButtons = parentMenu.getSubButton();
			if(null==subButtons) {
				subButtons = new ArrayList<MenuButton>();
				parentMenu.setSubButton(subButtons);
			}
			subButtons.add(MenuUtils.createMenu(MenuUtils.getMenuType(menu.getType()), menu.getMenuKey(), menu.getName(), menu.getUrl()));
		}
		MenuAPI menuApi = new MenuAPI(apiConfig);
		ResultType resultType = menuApi.createMenu(_menu);
		return ResultType.SUCCESS.getCode().toString().equals(resultType.getCode().toString())?"":resultType.getDescription();
	}
}
