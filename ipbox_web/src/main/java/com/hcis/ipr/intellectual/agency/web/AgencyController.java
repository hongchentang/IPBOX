package com.hcis.ipr.intellectual.agency.web;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.common.utils.ApiCode;
import com.hcis.ipr.common.utils.BaseApi;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.intellectual.agency.entity.Agency;
import com.hcis.ipr.intellectual.agency.service.AgencyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhw
 * @date 2019/08/20
 */
@RequestMapping("/intellectual/agency")
@Controller
public class AgencyController extends BaseController {

    @Autowired
    private AgencyService agencyService;

    @RequestMapping("/add")
    @ResponseBody
    public BaseApi add(Agency agency){
        BaseApi api = new BaseApi();
        String userId = LoginUser.loginUser(request).getId();

        try {
            if(StringUtils.isBlank(agency.getId())){
                agency.setId(UUIDUtils.getUUId());
                agency.setCreator(userId);
                agency.setUpdatedby(userId);
                agency.setDefaultValue();
                agencyService.insert(agency);
            }else {
                agencyService.updateAgency(agency, userId);
            }
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(String id){
        ModelAndView view = new ModelAndView("/intellectual/agency/detail");
        view.addObject("agency", agencyService.read(id));

        return view;
    }

    @RequestMapping("delete")
    @ResponseBody
    public BaseApi delete(String id){
        BaseApi api = new BaseApi();
        try {
            String userId = LoginUser.loginUser(request).getId();
            agencyService.deleteById(id, userId);
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/list")
    public ModelAndView list(SearchParam searchParam){

        ModelAndView view = new ModelAndView("/intellectual/agency/list");
        view.addObject("list", agencyService.list(searchParam));
        return view;
    }

    @RequestMapping("/goAddAgency")
    public ModelAndView goAddAgency(String id){
        ModelAndView view = new ModelAndView("/intellectual/agency/add");
        if(StringUtils.isNotBlank(id)){
            view.addObject("agency", agencyService.read(id));
        }
        return view;
    }

    @RequestMapping("/xx")
    @ResponseBody
    public BaseApi xx(){
        BaseApi api = new BaseApi();
        try {
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
        }
        return api;
    }
}
