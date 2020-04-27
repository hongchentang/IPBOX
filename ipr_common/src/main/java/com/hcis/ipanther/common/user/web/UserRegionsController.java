package com.hcis.ipanther.common.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.common.regions.utils.RegionsConstants;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserRegions;
import com.hcis.ipanther.common.user.service.IUserRegionsService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.common.user.utils.UserRegionsUtils;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Controller("common_userRegionsController")
@RequestMapping("/common/user")
public class UserRegionsController extends BaseController {
	
	@Resource(name="userRegionsService")
	private IUserRegionsService userRegionsService;
	@Autowired
	private IUserService userService;
	
	//配置区域权限，读取
	@RequestMapping("/regionsConfig")
	public ModelAndView userRegionsConfig(@FormModel("user")User user) {
		LoginUser loginUser=LoginUser.loginUser(request);
		ModelAndView mv = this.newModelAndView();
		List<UserRegions> userRegionsList=userRegionsService.listByUserId(user.getId());
		List<Regions> regionsList=UserRegionsUtils.getRegionsByUser(loginUser.getRegionsCode(), userRegionsList);
		Map<String,String> checkedMap=UserRegionsUtils.getUserRegionsCheckedMap(userRegionsList);
		String regionsTreeData=UserRegionsUtils.getRegionsJson(regionsList, checkedMap);
		mv.addObject("regionsTreeData", regionsTreeData);
		mv.addObject("userRegionsList", userRegionsList);
		mv.addObject("map", userService.read(user!=null?user.getId():null));
		return mv;
	}
	
	//保存
	@RequestMapping("/regionsConfigSave")
	public @ResponseBody AjaxReturnObject save(@FormModel("userRegions")UserRegions userRegions,HttpServletResponse response) {
		LoginUser loginUser=LoginUser.loginUser(request);
		//页面传递的数据是多数据用“;”分隔
		String[] regionsCodeArray=StringUtils.split(userRegions.getRegionsCode(),";");
		String[] regionsHasChildArray=StringUtils.split(userRegions.getHasChild(),";");
		String userId=userRegions.getUserId();
		int count= 0;
		//1.删除原有全部授权
		count=userRegionsService.batchDeleteByUserId(userId, loginUser);
		//2.添加新授权
		if(ArrayUtils.isNotEmpty(regionsCodeArray)){
			for(String regions:regionsCodeArray){
				UserRegions dat=new UserRegions();
				dat.setUserId(userId);
				dat.setRegionsCode(regions);
				if(ArrayUtils.contains(regionsHasChildArray, regions)){
					dat.setHasChild(UserConstants.USER_REGIONS_HASCHILD);
				}
				else{
					dat.setHasChild(UserConstants.USER_REGIONS_NOCHILD);
				}
				count+=userRegionsService.create(dat,loginUser.getId());
			}
		}
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}

}
