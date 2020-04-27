package com.hcis.ipanther.common.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.Collections3;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.dao.RoleDao;
import com.hcis.ipanther.common.security.dao.UserRoleDao;
import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.entity.UserRole;
import com.hcis.ipanther.common.security.service.IModuleService;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.security.utils.SecurityRoleUtils;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.utils.UserUtils;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.web.vo.SearchParam;
@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {
	
	@Resource
	private RoleDao roleDao;
	@Resource
	private UserRoleDao userRoleDao;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IModuleService moduleService;
	
	/**
	 * 根据用户查询拥有的角色
	 * @param user
	 * @return
	 */
	@Override
	public List<Role> selectRoleByUser(User user){
		return roleDao.selectByUser(user);
	}
	
	@Override
	public String getAllRolesCheckBox(User user,String entityName){
		StringBuffer checkBoxStr=new StringBuffer();
		String checked="";//是否选中
		int br=0;//每行放3个checkbox
		checkBoxStr.append("<div class=\"form-group\">");
		//用户拥有的角色
		List<Role> rolesByUser=null;
		if(null!=user&&user.getId()!=null){
			rolesByUser=this.selectRoleByUser(user);
		}else{
			rolesByUser=new ArrayList();
		}
		
		//获取 所有角色，生成页面复选筐标签，若该用户拥有的角色打上勾
		SearchParam searchParam=new SearchParam();
		Pagination p=new Pagination();
		p.setAvailable(false);
		 List<Role> rolesAll=roleDao.selectBySearchParam(searchParam);
		 for(Role r:rolesAll){
			 checked="";
			 for(Role userRole:rolesByUser){
				 if(userRole.getId().equals(r.getId())){
					 checked="checked=\"checked\"";
					 rolesByUser.remove(userRole);//移除比较过的，减少循环次数
					 break;
				 }
			 }
			 if(br>0){
				 checkBoxStr.append(br%4==0?"</div><div class=\"form-group\">":"");
			 }
			 checkBoxStr.append("<label class=\"col-sm-2 control-label\">"+r.getName()+"</label>");
			 checkBoxStr.append("<span class=\"col-sm-1\"> <label class=\"control-label\">");
			 checkBoxStr.append("<input type=\"checkbox\" name=\""+entityName+"\" value=\""+r.getId()+"\" "+checked+"></label> </span>");
			 br++;
		 }
		 checkBoxStr.append("</div>");
		return checkBoxStr.toString();
	}
	
	public List<Map<String,Object>> getModuleCheckBox(User user,String entityName){
		List<String> roles = UserUtils.getCanGrantRoleCodes();//当前登录人可授权的角色
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		 if(user!=null){
			List<Map<String, Object>> list=roleDao.selectRolesByUser(user);
			if(CollectionUtils.isNotEmpty(list)){
				for (Map<String, Object> map : list) {
					String roleCode = (String) map.get("roleCode");
					//角色不在可授予之内，则跳过
					if(!roles.contains(roleCode)) {
						continue;
					}
					if(map!=null&&(!map.isEmpty())){
						if(Integer.parseInt(ObjectUtils.toString(map.get("checked")))==1){
							map.put("checked", true);
						}else{
							map.put("checked", false);
						}
					}
					result.add(map);
				}
			}
			return result;
		 }
/*		String moduleId=CommonConfig.getProperty("app.module.id");
		String[] moduleIds=moduleId.split(";");
		List<Module> moduleAll=null;*/
	/*	List<Role> rolesByUser=null;//用户拥有的角色
		if(null!=user&&user.getId()!=null){
			rolesByUser=this.selectRoleByUser(user);
		}else{
			rolesByUser=new ArrayList();
		}
		
		//当前系统所能操作的所有角色
		Module module=moduleService.read(moduleIds!=null?moduleIds[moduleIds.length-1]:null);
		if(module!=null){
			SearchParam searchParam=new SearchParam();
			Pagination p=new Pagination();
			p.setAvailable(false);
			if(StringUtils.equals(module.getType(), SecurityConstants.MODULE_TYPE_MASTER)){
				moduleAll=moduleService.list(searchParam);//如果是主系统则显示所有角色
			}else{
				searchParam.getParamMap().put("module", module.getId());//若为子系统，则显示自己和公用的角色
				moduleAll=new ArrayList<Module>();
				moduleAll.add(module);
			}
			 List<Role> rolesAll=roleDao.selectBySearchParam(searchParam);
			 //角色分类，获得系统对应的角色
			 List<Map<String,Object>> moduleMap=this.separateRole(moduleAll, rolesAll);
			 //生成复选框
			 for(Map<String,Object> m:moduleMap){
				 List<Role> moduleRole= (List<Role>) m.get("moduleRole");
				 String checkBoxStr=this.createCheckBox(moduleRole, rolesByUser, entityName,m.get("name").toString());
				 m.put("checkBoxStr", checkBoxStr);
			 }
			 return moduleMap;
		}*/
		return null;
	}
	//根据模块ID把分离的各系统角色存在Map(每个MAP代表一个系统)
	public List<Map<String,Object>> separateRole(List<Module> moduleAll,List<Role> rolesAll){
		int index=0;
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> commonModule=new HashMap<String,Object>();
		List<Role> commonRole=new ArrayList<Role>();
		JSONArray jsonArray=null;
		Map<String,Object> module=null;
		List<Role> moduleRole=null;
		for(Module m:moduleAll){
			moduleRole=new ArrayList<Role>();
			module=new HashMap<String,Object>();
			module.put("name", m.getName());
			for(Role r:rolesAll){
				String moduleIds=r.getModule();
				 if(StringUtils.isNotBlank(moduleIds)){
					 jsonArray= JSONArray.fromObject(moduleIds);
					 for(int i=0;i<jsonArray.size();i++){
						 String moduleId=(jsonArray.getJSONObject(i).get("module")==null?"":(String)jsonArray.getJSONObject(i).get("module"));
						 if(StringUtils.equals(m.getId(), moduleId)){
							 moduleRole.add(r);
							 break;
						 }
					 }
				 }else{
					 if(!commonRole.contains(r)){
						 commonRole.add(r);
					 }
				 }
				 index++;
			}
			module.put("moduleRole", moduleRole);
			list.add(module);
		}
		commonModule.put("name", "通用角色");
		commonModule.put("moduleRole", commonRole);
		list.add(commonModule);
		return list;
	}
	
	//生成复选框
	public String createCheckBox(List<Role> moduleRole,List<Role> rolesByUser,String entityName,String moduleName){
		StringBuffer checkBoxStr=new StringBuffer();
		String checked="";//是否选中
		int br=0;//每行放3个checkbox
		checkBoxStr.append("<div class=\"form-group\">");
		 for(Role r:moduleRole){
			 checked="";
			 for(Role userRole:rolesByUser){
				 if(userRole.getId().equals(r.getId())){
					 checked="checked=\"checked\"";
					 break;
				 }
			 }
			 if(br>0){
				 checkBoxStr.append(br%3==0?"</div><div class=\"form-group\">":"");
			 }
			 checkBoxStr.append("<label class=\"col-sm-3 control-label\">"+r.getName()+"</label>");
			 checkBoxStr.append("<span class=\"col-sm-1\"> <label class=\"control-label\">");
			 checkBoxStr.append("<input type=\"checkbox\" onclick=\"javascript:tips(this);\" name=\""+entityName+"\" value=\""+r.getId()+"\" title=\""+moduleName+"\" "+checked+"></label> </span>");
			 br++;
		 }
		 checkBoxStr.append("</div>");
		return checkBoxStr.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#list(SearchParam searchParam)
	 */
	@Override
	public List<UserRole> list(SearchParam searchParam) {
		return null;
	}
	
	public String getRoleByUser(User user){
		
		return null;
	}


	@Override
	public int insertSelective(UserRole userRole, LoginUser loginUser) {
		return userRoleDao.insertSelective(userRole);
	}

	@Override
	public int deleteUserRole(UserRole userRole) {
		return userRoleDao.deleteByLogic(userRole);
	}

	@Override
	public int deleteByUserId(String userId) {
		return userRoleDao.deleteByUserId(userId);
	}

	@Override
	public UserRole read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(UserRole obj, String creator) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(UserRole obj, String updateBy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(UserRole obj, String updateBy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveByCode(String userId, String roleCode) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		Role role = roleService.getRoleByCode(roleCode);
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(role.getId());
		userRole.setDefaultValue();
		return this.insertSelective(userRole, loginUser);
	}


}
