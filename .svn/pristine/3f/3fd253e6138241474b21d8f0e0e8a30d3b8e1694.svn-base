/*************************************************
Copyright (C), 2012
Author:����� 
Version: 
Date: 20132013-4-2
Description: // ������ϸ˵���˳����ļ���ɵ���Ҫ���ܣ�������ģ��
// ����Ľӿڣ����ֵ��ȡֵ��Χ�����弰�����Ŀ�
// �ơ�˳�򡢶����������ȹ�ϵ
Function List: // ��Ҫ�����б?ÿ����¼Ӧ�����������ܼ�Ҫ˵��
1. ....
History: // �޸���ʷ��¼�б?ÿ���޸ļ�¼Ӧ�����޸����ڡ��޸�
// �߼��޸����ݼ���
1. Date:
Author:
Modification:
2. ...
*************************************************/
package com.hcis.ipanther.common.login.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.JSONUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.dao.LoginUserDao;
import com.hcis.ipanther.common.login.service.ILoginService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.dao.PrivilegeDao;
import com.hcis.ipanther.common.security.dao.RoleDao;
import com.hcis.ipanther.common.security.entity.Privilege;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
/**
 * @author lianghuahuang
 *
 */
@Service("loginService")
public class LoginServiceImpl extends BaseServiceImpl implements ILoginService {
	
	@Resource
	private LoginUserDao loginDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private PrivilegeDao privilegeDao;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IDepartmentService departmentService;
	
	/* (non-Javadoc)
	 * @see com.hcis.ipanther.common.login.service.ILoginService#getLoginUser(com.hcis.ipanther.common.login.vo.LoginUser)
	 */
	@Override
	public LoginUser getLoginUser(LoginUser loginUser) {
		if(loginUser!=null){
			loginUser.setPassword(DigestUtils.md5Hex(loginUser.getPassword()));
			return loginDao.selectUserView(loginUser);	
		}
		else{
			return null;
		}
	}
	@Override
	public LoginUser getLoginUser(String userId) {
		if(!StringUtils.isEmpty(userId)){
			return loginDao.selectUserViewById(userId);
		}
		return null;
	}
	@Override
	public LoginUser getLoginUserByAccount(String account){
		if(StringUtils.isNotEmpty(account)){
			return loginDao.selectUserViewByAccount(account);
		}
		return null;
	}
	@Override
	public LoginUser getLoginUserByWechatId(String wechatId) {
		if(StringUtils.isNotEmpty(wechatId)){
			return loginDao.selectUserViewByWechatId(wechatId);
		}
		return null;
	}

	@Override
	public GetUserInfoResponse code2Session(String code) {
		//拼接url
		StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
		//appid设置
		url.append("appid=");
		url.append(AppConfig.getProperty("wechat", "wechat.appid"));
		//secret设置
		url.append("&secret=");
		url.append(AppConfig.getProperty("wechat", "wechat.secret"));
		//code设置
		url.append("&js_code=");
		url.append(code);
		url.append("&grant_type=authorization_code");
		GetUserInfoResponse responseUserInfo = null;
		try {
			//构建一个Client
			CloseableHttpClient client = HttpClientBuilder.create().build();
			//构建一个GET请求
			HttpGet get = new HttpGet(url.toString());
			//提交GET请求
			HttpResponse response = client.execute(get);
			//拿到返回的HttpResponse的"实体"
			HttpEntity result = response.getEntity();
			String content = EntityUtils.toString(result);
			//打印返回的信息
			System.out.println(content);
			responseUserInfo = (GetUserInfoResponse) JSONUtil.toBean(content, GetUserInfoResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseUserInfo;
	}

	@Override
	public LoginUser getUserByPhoneAndWechatId(String phone, String weChatId) {
		LoginUser loginUser = new LoginUser();
		loginUser.setWechatId(weChatId);
		loginUser.setPhone(phone);
		loginUser = getBaseDao().getUserByPhoneAndWechatId(loginUser);
		return loginUser;
	}


	private static Map<String, Object> parseJSON2Map(JSONObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				@SuppressWarnings("unchecked")
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * 根据用户拥有角色设置菜单
	 */
	@Override
	public void setMenu(LoginUser loginUser){
		//获取配置模板Id
		String moduleIds=CommonConfig.getProperty("app.module.id");
		String[] moduleIdArray=StringUtils.split(moduleIds,";");
		
		List<Role> roleList=roleService.allRolePrivilege();
		for(int i=0;i<moduleIdArray.length;i++){
			String moduleId=moduleIdArray[i];
			List<Privilege> privileges= getPrivilegeByRoleList(loginUser,moduleId,roleList);
			String menuString=this.createTreeMenuJson(privileges);
			loginUser.getMenuMap().put(moduleId,menuString);
			if(i==0){
				loginUser.setMenu(menuString);
				loginUser.setPrivileges(privileges);
			}
		}

		List<Privilege> list = new ArrayList<>();
		zlPrivilege("0", loginUser.getPrivileges(), list);
		for(Privilege privilege : list){
			List<Privilege> privilegeList = zlPrivilege(privilege.getId(), loginUser.getPrivileges(), privilege.getNextLevelPrivileges());
			privilege.setNextLevelPrivileges(privilegeList);
		}
		loginUser.setPrivileges(list);
//		StringBuffer MenuStr=new StringBuffer();
//		if(null!=privileges&&privileges.size()>0){
//			for(Privilege pp:privileges){
//				if(pp.getPid().equals("0")){
//					MenuStr.append(createMenu(privileges,pp));
//				}
//			}
//		}
//		loginUser.setMenu(MenuStr.toString());
	}

	private List<Privilege> zlPrivilege(String pid, List<Privilege> sourceList, List<Privilege> targetList){
		for(Privilege privilege : sourceList){
			if(pid.equals(privilege.getPid())){
				if(targetList == null){
					targetList = new ArrayList<>();
				}
				targetList.add(privilege);
			}
		}

		return targetList;
	}
	
	//根据已有的角色匹配相应模块的功能权限
	public List<Privilege> getPrivilegeByRoleList(LoginUser loginUser,String moduleId,List<Role> roleList){
		List<Privilege> privileges=new ArrayList<Privilege>();
		Map<String,Privilege> map=new HashMap<String,Privilege>();
		List<Role> userRoleList=loginUser.getRoleList();
		for(Role ur:userRoleList){
			for(Role r:roleList){
				if(StringUtils.equals(r.getId(), ur.getId())){
					for(Privilege p:r.getPrivilegeList()){
						if(StringUtils.equals(p.getModule(), moduleId)){
							//防止多个角色导致多个相同功能
							map.put(p.getId(), p);
						}
					}
					
				}
			}
		}
		if(map.size()>0){
			Iterator<Privilege> it=map.values().iterator();
			while(it.hasNext()){
				privileges.add(it.next());
			}
			
		}
		//排序
		if(privileges.size()>0){
			Collections.sort(privileges,new Comparator<Privilege>(){  
	            public int compare(Privilege arg0, Privilege arg1) {  
	            	BigDecimal a0=arg0.getOrderNo()==null?new BigDecimal(1000):arg0.getOrderNo();
	            	BigDecimal a1=arg1.getOrderNo()==null?new BigDecimal(1000):arg1.getOrderNo();
	                return a0.compareTo(a1);  
	            }  
	        });
		}
		return privileges;
	} 
	
	@Override
	public void setRoleList(LoginUser loginUser){
		User user=new User();
		user.setId(loginUser.getId());
		List<Role> roleList=roleDao.selectByUser(user);
		loginUser.setRoleList(roleList);
	}
	
	/**
	 * 创建菜单
	 * @param list
	 * @param p
	 * @return
	 */
	public StringBuffer createMenu(List<Privilege> list,Privilege p){
		boolean flg=false;
		StringBuffer str=new StringBuffer();
		str.append("<li>");
		str.append("<a href='javascript:;'>");
		str.append("<i class='fa fa-align-justify'></i>");//图标
		str.append("<span class='title'>" +p.getName()+"</span>");
		str.append("<span class='arrow'></span>");
		str.append("</a>");
		str.append("<ul class='sub-menu'>");
		for(Privilege i:list){
			//子节点
			if(p.getId().equals(i.getPid())){  
				str.append(this.createMenu(list,i));
				flg=true;
			}
		}
		str.append("</ul>");
		str.append("</li>");

		//无子节点
		if(!flg){
			str=new StringBuffer();
			str.append("<li>");
//			str.append("<a href=\"javascript:contentLoader('"+p.getUrl()+"')\">")
			str.append("<a class='ajaxify' href=\""+p.getUrl()+"\">");
			str.append("<i class='fa fa-align-justify'></i>");//图标
			str.append("<span class='title'>"+p.getName()+"</span>");
			str.append("</a>");
			str.append("</li>");
		}
		return str;
	}
	
	/**
	 * EasyUI使用的tree json 
	 * [{ 
	 * 	attributes: { 
	 * 		target: "CXFDemo", 
	 * 		url: "/ws" 
	 * 	},
	 * 	checked: false, 
	 * 	iconCls: "ext-icon-world", 
	 * 	id: "cxfDemo", 
	 * 	pid: "demo",
	 * 	state: "open", 
	 * 	text: "ApacheCXF示例" 
	 * }]
	 * 
	 * @param privilegeList
	 * @return
	 */
	private String createTreeMenuJson(List<Privilege> privilegeList){
		List<Map<String,Object>> nodeMapList=new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(privilegeList)){
			for(Privilege pri:privilegeList){
				Map<String,Object> nodeMap=new HashMap<String,Object>();
				nodeMap.put("id",pri.getId()); 
				nodeMap.put("pid",pri.getPid());
				nodeMap.put("text",pri.getName());
				nodeMap.put("iconCls","");
				nodeMap.put("state","open"); 
				nodeMap.put("checked", false);
				Map<String,Object> nodeAttributeMap=new HashMap<String,Object>();
				nodeAttributeMap.put("target",pri.getTarget()); 
				nodeAttributeMap.put("url",pri.getUrl());
				nodeMap.put("attributes", nodeAttributeMap);
				nodeMapList.add(nodeMap);
			}
			return JSONUtils.getJSONString(nodeMapList);
		}
		return "";
	}
	
	@Override
	public List<LoginUser> selectAllLoginUser() {
		return loginDao.selectAllLoginUser();
	}
 
	@Override
	public LoginUserDao getBaseDao() {
		return loginDao;
	}
}
