package com.hcis.ipr.intellectual.cost.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.customers.entity.CustomersEmail;
import com.hcis.ipanther.common.customers.service.CustomersService;
import com.hcis.ipanther.common.dict.service.IDictEntryService;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.common.json.JsonForMap;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.common.utils.ApiCode;
import com.hcis.ipr.common.utils.BaseApi;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.intellectual.cost.dto.PatentCostDetailDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostListDto;
import com.hcis.ipr.intellectual.cost.entity.PatentCost;
import com.hcis.ipr.intellectual.cost.entity.PatentCostPayment;
import com.hcis.ipr.intellectual.cost.enumeration.CostType;
import com.hcis.ipr.intellectual.cost.service.PatentCostService;

import net.sf.json.JSONObject;

/**
 * @author zhw
 */
@RequestMapping("/intellectual/patent/cost")
@Controller
public class PatentCostController extends BaseController {

	@Autowired
	private PatentCostService patentCostService;

	@Autowired
	private IDictEntryService dictEntryService;
	@Autowired
	private CustomersService customersService;

	@RequestMapping("/list")
	public ModelAndView list(@ModelAttribute("searchParam") SearchParam searchParam, String type, Integer status) {
		if (StringUtils.isNotBlank(type)) {
			searchParam.getParamMap().put("type", type);
		}
		if (status != null) {
			searchParam.getParamMap().put("status", status);
		}
		ModelAndView modelAndView = new ModelAndView("/intellectual/cost/list");
		List<PatentCostListDto> list = patentCostService.selectList(searchParam);
		modelAndView.addObject("patentCostList", list);
		return modelAndView;
	}

	@RequestMapping("goAddPatentCost")
	public ModelAndView goAdd(String id, Integer type) {
		ModelAndView modelAndView = new ModelAndView("/intellectual/cost/add");
		CostType costType = CostType.valueOf(type);
		modelAndView.addObject("type", type);
		modelAndView.addObject("typeName", costType.getTypeName());
		if (StringUtils.isNotBlank(id)) {
			PatentCostDetailDto cost = patentCostService.getPatentDetail(id);
			modelAndView.addObject("cost", cost);
		}
		return modelAndView;
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseApi addPatentCost(@Param("patentCost") PatentCost patentCost,
			DefaultMultipartHttpServletRequest request) {
		BaseApi api = new BaseApi();

		try {
			LoginUser user = LoginUser.loginUser(request);
			String userId = user.getId();
			String companyId = user.getCompanyId();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("invoiceFile");
			if (StringUtils.isBlank(patentCost.getId())) {
				patentCost.setCreator(userId);
				patentCost.setCompanyId(companyId);
				patentCostService.addCost(patentCost, file);
			} else {
				patentCost.setUpdatedby(userId);
				patentCostService.updateCost(patentCost, file);
			}
		} catch (Exception e) {
			api.setMsg(e.getMessage());
			api.setCode(ApiCode.ERROR);
			e.printStackTrace();
		}
		return api;
	}

	@RequestMapping("/deletePatentCost")
	@ResponseBody
	public BaseApi deletePatentCost(String id) {
		BaseApi api = new BaseApi();
		try {
			patentCostService.deleteByIds(id);
		} catch (Exception e) {

		}
		return api;
	}

	@RequestMapping("/detail")
	public ModelAndView detail(String id) {
		ModelAndView view = new ModelAndView("/intellectual/cost/detail");
		view.addObject("id", id);
		return view;
	}

	@RequestMapping("/detailInfo")
	public ModelAndView detailInfo(String id) {
		ModelAndView view = new ModelAndView("/intellectual/cost/detailInfo");
		view.addObject("cost", patentCostService.getPatentDetail(id));
		return view;
	}

	@RequestMapping("/attachment")
	public ModelAndView attachment(String id) {
		ModelAndView view = new ModelAndView("/intellectual/cost/attachment");
		view.addObject("cost", patentCostService.getPatentDetail(id));
		return view;
	}

	@RequestMapping("/goPayable")
	public ModelAndView goPayable(String id) {
		ModelAndView view = new ModelAndView("/intellectual/cost/payable");
		view.addObject("id", id);
		return view;
	}

	@RequestMapping("/payable")
	@ResponseBody
	public BaseApi payable(PatentCostPayment patentCostPayment) {
		BaseApi api = new BaseApi();
		try {
			String userId = LoginUser.loginUser(request).getId();
			patentCostPayment.setCreator(userId);
			patentCostService.payable(patentCostPayment);
		} catch (Exception e) {
			api.setCode(ApiCode.ERROR);
			api.setMsg(e.getMessage());
		}

		return api;
	}

	@RequestMapping("/tabs")
	public ModelAndView tabs(Integer type) {
		ModelAndView view = new ModelAndView("/intellectual/cost/costListTabs");
		view.addObject("type", type);
		String typeName = CostType.valueOf(type).getTypeName();
		if (type == CostType.ANNUAL_FEE.getType() || type == CostType.GOVERNMENT_FEE.getType()) {
			typeName = CostType.GOVERNMENT_FEE.getOtherName();
		}
		view.addObject("typeName", typeName);
		return view;
	}

	@RequestMapping("getCostAllType")
	@ResponseBody
	public Object getCostAllType() {
		List<DictEntry> list = new ArrayList<>();
		list.addAll(dictEntryService.getByTypeCode("IPBOX_ANNUAL_FEE"));
		List<DictEntry> dictDictEntryList = dictEntryService.getByTypeCode("IPBOX_GOVERNMENT_FEE");
		for (DictEntry dictEntry : dictDictEntryList) {
			Integer value = Integer.valueOf(dictEntry.getDictValue().toString());
			value = value + 20;
			dictEntry.setDictValue("" + value);
		}
		list.addAll(dictDictEntryList);
		return list;
	}

	@RequestMapping("/sendEmail")
	@ResponseBody
	public BaseApi sendEmail(String id, String email, String theme, String text) {
		BaseApi api = new BaseApi();
		LoginUser loginUser = LoginUser.loginUser(request);
		try {
			String[] emas = email.split("\\、");
			for (int i = 0; i < emas.length; i++) {
				patentCostService.sendEmail(id, emas[i], theme, text);
			}

			SearchParam searchParam = new SearchParam();

			searchParam.getParamMap().put("userId", loginUser.getId());
			email = email.substring(0, email.length() - 1);
			if (email.indexOf("、") != -1) {

			} else {
				searchParam.getParamMap().put("userEmail", email);
			}

			List<CustomersEmail> customersEmail = customersService.list(searchParam);
			api.setData(customersEmail);

		} catch (Exception e) {
			e.printStackTrace();
			api.setCode(ApiCode.ERROR);
			api.setMsg(e.getMessage());
		}
		return api;
	}

	@RequestMapping("gosendEmailpage")
	public ModelAndView gosendEmai(@ModelAttribute("searchParam") SearchParam searchParam, String id, String name) {
		ModelAndView modelAndView = new ModelAndView("/intellectual/cost/addsendEmailpage");

		/*
		 * SearchParam useremial = new SearchParam();
		 * 
		 * useremial.getParamMap().put("userName", id);
		 */

		if (name.equals("") || name == null) {
			modelAndView.addObject("listUser", customersService.list(searchParam));
		} else {
			SearchParam useremial = new SearchParam();

			useremial.getParamMap().put("userName", name);
			modelAndView.addObject("listUser", customersService.list(useremial));
		}

		modelAndView.addObject("paramMap", searchParam.getParamMap());
		List<CustomersEmail> customersEmail = customersService.selectcontacts(searchParam);

		if (customersEmail.size() > 4) {

			customersEmail.remove(customersEmail.size() - 1);
			customersEmail.remove(customersEmail.size() - 2);
			customersEmail.remove(customersEmail.size() - 3);
			customersEmail.remove(customersEmail.size() - 4);
		}
		modelAndView.addObject("contacts", customersEmail);

		modelAndView.addObject("id", id);

		return modelAndView;
	}

	@RequestMapping("gosendEmailpages")
	public ModelAndView gosendEmais(@ModelAttribute("searchParam") SearchParam searchParam, String id, String name) {
		ModelAndView modelAndView = new ModelAndView("/intellectual/cost/addsendEmailpage2");
		/*
		 * SearchParam useremial = new SearchParam();
		 * 
		 * useremial.getParamMap().put("userName", id);
		 */

		if (name.equals("") || name == null) {
			modelAndView.addObject("listUser", customersService.list(searchParam));
		} else {
			SearchParam useremial = new SearchParam();

			useremial.getParamMap().put("userName", name);
			modelAndView.addObject("listUser", customersService.list(useremial));
		}
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		List<CustomersEmail> customersEmail = customersService.selectcontacts(searchParam);
		customersEmail.remove(customersEmail.size() - 1);
		customersEmail.remove(customersEmail.size() - 2);
		customersEmail.remove(customersEmail.size() - 3);
		customersEmail.remove(customersEmail.size() - 4);
		modelAndView.addObject("contacts", customersEmail);

		modelAndView.addObject("id", id);
		System.out.println("id=" + id);
		return modelAndView;
	}

	@RequestMapping("/getcusData")
	@ResponseBody
	public BaseApi getcusData(@ModelAttribute("searchParam") SearchParam searchParam) {

		BaseApi api = new BaseApi();
		/*
		 * modelAndView.addObject("listUser",
		 * customersService.list(searchParam));
		 * modelAndView.addObject("paramMap", searchParam.getParamMap());
		 * modelAndView.addObject("contacts",
		 * customersService.selectcontacts(searchParam));
		 */
		List<CustomersEmail> list = customersService.list(searchParam);
		List<CustomersEmail> list2 = customersService.selectcontacts(searchParam);
		JSONObject object = JsonForMap.JSONArrayForMap(list);
		JSONObject object2 = JsonForMap.JSONArrayForMap(list2);

		Map<String, JSONObject> map = new HashMap<>(2);
		map.put("cous", object);
		map.put("clx", object2);
		api.setData(map);

		return api;

	}

	@RequestMapping("gocusomer")
	public ModelAndView gocusomer(String id) {
		ModelAndView modelAndView = new ModelAndView("/intellectual/cost/gocusomer");

		modelAndView.addObject("id", id);

		return modelAndView;
	}

	@RequestMapping("/emailView")
	public ModelAndView emailView(String id) {
		ModelAndView view = new ModelAndView("/intellectual/cost/emailView");

		return view;
	}

	@RequestMapping("/goPerfect")
	public ModelAndView goPerfect(String id) {
		ModelAndView view = new ModelAndView("/intellectual/cost/goPerfect");

		return view;
	}
}
