package com.hcis.ipr.wechat.menu.utils;

import org.apache.commons.lang3.StringUtils;

import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.hcis.ipr.wechat.utils.WechatUtils;

/**
 * 
 * @author wuwentao
 * @date 2016年11月1日
 */
public class MenuUtils {

	/**
	 * 根据字典值得到对应的菜单枚举类型
	 * @param type
	 * @return
	 */
	public static MenuType getMenuType(String type) {
		MenuType[] menuTypes = MenuType.values();
		for (MenuType menuType : menuTypes) {
			if(StringUtils.equals(menuType.toString(), type)) {
				return menuType;
			}
		}
		return null;
	}
	
	/**
	 * 一般的菜单建立 
	 * @param menuType
	 * @param key
	 * @param name
	 * @param url
	 * @return
	 */
	public static MenuButton createMenu(MenuType menuType,String key,String name,String url) {
		return createMenu(menuType, key, name, url,OauthScope.SNSAPI_USERINFO);
	}
	
	/**
	 * 一般的菜单建立
	 * @param menuType 菜单类型
	 * @param key 
	 * @param name
	 * @param url 非链接传null
	 * @param oauthScope 非链接或者简单跳转链接传null
	 * @return
	 */
	public static MenuButton createMenu(MenuType menuType,String key,String name,String url,OauthScope oauthScope) {
		MenuButton btn = new MenuButton();
		btn.setType(menuType);
		btn.setKey(key);
		btn.setName(name);
		if(null!=url) {
			if(!StringUtils.startsWith(url, "http")) {//如果不是完整的请求链接，需要加上系统的请求域名
				url = WechatUtils.getUrl(url);
				if(null!=oauthScope) {
					btn.setUrl(WechatUtils.getOauthPageUrl(url,oauthScope));
				} else {
					btn.setUrl(url);
				}
			} else {//完整的请求无需获取Oauth权限
				btn.setUrl(url);
			}
		}
		return btn;
	}
	
}
