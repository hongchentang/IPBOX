package com.hcis.ipr.space.utils;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.core.utils.CommonConfig;

/**
 * 个人端通用方法
 * @author wuwentao
 * @date 2015年4月21日
 */
public class SpaceUtils {
	
	public static List<String> menus = null;

	/**
	 * 得到当前选择的菜单
	 * 如果请求不与任何菜单匹配，则返回session中上一次匹配的菜单ID
	 * @param request
	 * @return
	 */
	public static String getSelectedMenuId(HttpServletRequest request) {
		String selectedMenuId = "";
		String uri = request.getRequestURI();
		String menuId = uri.substring(uri.lastIndexOf("/")+1,uri.indexOf(".do"));//请求地址
		if(null==menus) {
			String _menus = CommonConfig.getProperty(SpaceConstants.SPACE_MENU);
			menus = Arrays.asList(_menus.split(","));
		}
		if(menus.contains(menuId)) {
			selectedMenuId = menuId;
			request.getSession().setAttribute(SpaceConstants.SPACE_MENU_ID, menuId);
		} else {
			selectedMenuId = (String) request.getSession().getAttribute(SpaceConstants.SPACE_MENU_ID);
		}
		return selectedMenuId;
	}
	
}
