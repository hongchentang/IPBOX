package com.hcis.ipr.intellectual.application.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.application.entity.UsePatent;
import com.hcis.ipr.intellectual.application.service.IUsePatentService;

@RequestMapping("/application/usePatent")
@Controller
public class UsePatentController extends BaseController {

	@Autowired
	private IUsePatentService usePatentService;

	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IUserService userService;

	/**
	 * 专利列表
	 *
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("tabList")
	public ModelAndView listTab(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView("/application/usePatent/tabList");
		return modelAndView;
	}

	@RequestMapping("listAp")
	public ModelAndView listAp(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView("/application/usePatent/listAp");

		LoginUser loginUser=(LoginUser) SecurityUtils.getSubject().getPrincipal();
		/*String [] deptIds = departmentService.getDeptIds(loginUser.getDeptId());*/
		/*searchParam.getParamMap().put("deptIds", deptIds);*/
		searchParam.getParamMap().put("useType", "1");
		searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
		searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());
		List<UsePatent> list = usePatentService.list(searchParam);
		modelAndView.addObject("listUsePatent", list);
		return modelAndView;
	}

	@RequestMapping("listSp")
	public ModelAndView listSp(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView("/application/usePatent/listSp");

		// LoginUser
		// loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		// String [] deptIds =
		// departmentService.getDeptIds(loginUser.getDeptId());
		// searchParam.getParamMap().put("deptIds", deptIds);
		searchParam.getParamMap().put("useType", "4");
		searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
		searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());
		List<UsePatent> list = usePatentService.list(searchParam);
		modelAndView.addObject("listUsePatent", list);
		return modelAndView;
	}

	@RequestMapping("listWp")
	public ModelAndView listWp(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView("/application/usePatent/listWp");

		// LoginUser
		// loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		// String [] deptIds =
		// departmentService.getDeptIds(loginUser.getDeptId());
		// searchParam.getParamMap().put("deptIds", deptIds);
		searchParam.getParamMap().put("useType", "3");
		searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
		searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());
		List<UsePatent> list = usePatentService.list(searchParam);
		modelAndView.addObject("listUsePatent", list);
		return modelAndView;
	}

	@RequestMapping("listPlep")
	public ModelAndView listPlep(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView("/application/usePatent/listPlep");

		// LoginUser
		// loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		// String [] deptIds =
		// departmentService.getDeptIds(loginUser.getDeptId());
		// searchParam.getParamMap().put("deptIds", deptIds);
		searchParam.getParamMap().put("useType", "2");
		searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
		searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());
		List<UsePatent> list = usePatentService.list(searchParam);
		modelAndView.addObject("listUsePatent", list);
		return modelAndView;
	}

	/**
	 * 根据Id,对专利数据进行逻辑删除
	 */
	@RequestMapping("deleteUsePatent")
	@ResponseBody
	public AjaxReturnObject deleteUsePatent(UsePatent usePatent) {
		LoginUser loginUser = LoginUser.loginUser(request);
		int statusCode = 200;
		String msg = "操作成功！";
		usePatentService.delete(usePatent, loginUser.getId());
		return new AjaxReturnObject(statusCode, msg);
	}

	// 编辑、新增专利信息
	@RequestMapping("goAddUsePatent")
	public ModelAndView goAddUsePatent(UsePatent usePatent) {
		ModelAndView modelAndView = new ModelAndView();
		if (usePatent != null && "1".equals(usePatent.getUseType().toString())) {

			modelAndView.setViewName("/application/usePatent/addUsePatentAP");

		} else if (usePatent != null && "2".equals(usePatent.getUseType().toString())) {

			modelAndView.setViewName("/application/usePatent/addUsePatentPLEP");

		} else if (usePatent != null && "3".equals(usePatent.getUseType().toString())) {

			modelAndView.setViewName("/application/usePatent/addUsePatentWP");

		} else {
			modelAndView.setViewName("/application/usePatent/addUsePatentSP");
		}

		if (null != usePatent && StringUtils.isNotBlank(usePatent.getId())) {
			// usePatentService.update(usePatent,LoginUser.loginUser(request).getId());
			usePatent = usePatentService.read(usePatent.getId());
			modelAndView.addObject("usePatent", usePatent);
		}
		return modelAndView;
	}

	@RequestMapping("/saveUsePatent")
	@ResponseBody
	public AjaxReturnObject saveUsePatent(@ModelAttribute("usePatent") UsePatent usePatent,
										  DefaultMultipartHttpServletRequest request) {
		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String userId = user.getId();
		String companyId = user.getCompanyId();
		usePatent.setOrgDeptId(user.getFirstDeptId());

		int statusCode = 200;
		String msg = "操作成功！";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("uploadFile");
		// 附件
		if (file != null && !file.isEmpty()) {
			String fileInfo = usePatentService.uploadFile(usePatent, file);
			if (StringUtils.isNotBlank(fileInfo)) {
				return new AjaxReturnObject(300, fileInfo);
			}
		}
		if (StringUtils.isNotBlank(usePatent.getId())) {
			usePatentService.update(usePatent, LoginUser.loginUser(request).getId());
		} else {
			int createCount = usePatentService.create(usePatent, LoginUser.loginUser(request).getId());
		}
		return new AjaxReturnObject(statusCode, msg);
	}

	/**
	 * 专利详情左边列表
	 */
	@RequestMapping("/showDetail")
	public ModelAndView showDetail(String id) {
		ModelAndView modelAndView = new ModelAndView();
		UsePatent usePatent = usePatentService.read(id);
		if (usePatent != null && "1".equals(usePatent.getUseType().toString())) {

			modelAndView.setViewName("/application/usePatent/viewUsePatentAP");

		} else if (usePatent != null && "2".equals(usePatent.getUseType().toString())) {

			modelAndView.setViewName("/application/usePatent/viewUsePatentPLEP");

		} else if (usePatent != null && "3".equals(usePatent.getUseType().toString())) {

			modelAndView.setViewName("/application/usePatent/viewUsePatentWP");

		} else {
			modelAndView.setViewName("/application/usePatent/viewUsePatentSP");
		}
		modelAndView.addObject("usePatent", usePatent);
		return modelAndView;
	}

}
