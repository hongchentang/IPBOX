package com.hcis.ipanther.common.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.security.dao.ModuleDao;
import com.hcis.ipanther.common.security.dao.RoleDao;
import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IModuleService;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements IModuleService{

	@Autowired
	private ModuleDao moduleDao;
	
	@Resource
	private RoleDao roleDao;
	
	//角色新增或修改模块复选框
	public String getModuleCheck(String entityName,String roleId){
		JSONArray jsonArray=null;
		StringBuffer checkBoxStr=new StringBuffer();
		String checked="";//是否选中
		int br=0;//每行放4个checkbox
		checkBoxStr.append("<div class=\"form-group\">");
		SearchParam searchParam=new SearchParam();
		Pagination p=new Pagination();
		p.setAvailable(false);
		//所有模块
		 List<Module> moduleAll=moduleDao.selectBySearchParam(searchParam);
		 //模块所属角色
		 Role role=null;
		 if(null!=roleId){
			 role=roleDao.selectByPrimaryKey(roleId);
		 }else{
			 role=new Role();
		 }
		 String module=role.getModule();
		 for(Module r:moduleAll){
			 checked="";
			 if(StringUtils.isNotBlank(module)){
				 jsonArray= JSONArray.fromObject(module);
				 for(int i=0;i<jsonArray.size();i++){
					 String moduleId=(jsonArray.getJSONObject(i).get("module")==null?"":(String)jsonArray.getJSONObject(i).get("module"));
					 if(StringUtils.equals(r.getId(), moduleId)){
						 checked="checked=\"checked\"";
						 break;
					 }
				 }
			 }
			 if(br>0){
				 checkBoxStr.append(br%3==0?"</div><div class=\"form-group\">":"");
			 }
			 checkBoxStr.append("<label class=\"col-sm-2 control-label\">"+r.getName()+"</label>");
			 checkBoxStr.append("<span class=\"col-sm-1\"> <label class=\"control-label\">");
			 checkBoxStr.append("<input type=\"checkbox\" name=\""+entityName+"\" value=\""+r.getId()+"\" "+checked+"></label></span>");
			 br++;
		 }
		 checkBoxStr.append("</div>");
		return checkBoxStr.toString();
	}
	
	
	@Override
	public MyBatisDao getBaseDao() {
		return moduleDao;
	}


}
