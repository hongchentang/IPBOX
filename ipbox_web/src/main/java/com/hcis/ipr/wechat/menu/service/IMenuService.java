package com.hcis.ipr.wechat.menu.service;

import java.util.List;
import java.util.Map;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.wechat.menu.entity.Menu;

public interface IMenuService extends IBaseService<Menu>{
	/**
	 * 菜单树
	 * @param searchParam
	 * @return
	 */
	public List<Map<String, Object>> treeView(SearchParam searchParam);

	/**
	 * 保存菜单
	 * @param menu
	 * @param operatorId
	 * @return 错误信息
	 */
	public String save(Menu menu,String operatorId);
	
	/**
	 * 计算菜单数量
	 * @param parameters
	 * @return
	 * 
	 */
	public int countByMap(Map<String, Object> parameters);
	
	/**
	 * 生成菜单
	 * 将本地的菜单配置生成到服务器上
	 * 没有完全融合所有的菜单类型
	 * 主要是click和view两种，如有其它类型加入，需先行测试
	 * view的请求为外部链接时，无Oauth，否则有，默认OauthScope.SNSAPI_BASE
	 * @return 错误信息
	 */
	public String generate();
}
