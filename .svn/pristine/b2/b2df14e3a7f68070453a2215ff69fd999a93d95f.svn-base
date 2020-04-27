package com.hcis.ipr.intellectual.cost.web;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.core.utils.DateUtil;
import com.hcis.ipanther.core.utils.DateUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.common.utils.ApiCode;
import com.hcis.ipr.common.utils.BaseApi;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorListDto;
import com.hcis.ipr.intellectual.cost.entity.PatentCostMonitor;
import com.hcis.ipr.intellectual.cost.entity.WarnTimeLine;
import com.hcis.ipr.intellectual.cost.service.PatentCostMonitorService;
import com.hcis.ipr.intellectual.cost.service.WarnTimeLineService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 */
@RequestMapping("/intellectual/patent/cost/monitor")
@Controller
public class PatentCostMonitorController extends BaseController {

    @Autowired
    PatentCostMonitorService patentCostMonitorService;

    @Autowired
    WarnTimeLineService warnTimeLineService;

    @RequestMapping("/setMonitorFresh")
    @ResponseBody
    public BaseApi setMonitorFresh(String companyId){
        BaseApi api = new BaseApi();
        try {
            if(StringUtils.isBlank(companyId)){
                LoginUser loginUser = LoginUser.loginUser(request);
                if(ObjectUtils.notEqual(UserConstants.USER_ROLECODE_SUPER_ADMIN,loginUser.getRoleCode())){
                    companyId = loginUser.getCompanyId();
                }
            }
            patentCostMonitorService.monitorRefresh(companyId);
        }catch (Exception e){
            e.printStackTrace();
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/list")
    @ResponseBody
    public ModelAndView list(SearchParam searchParam, String type, Integer status){
        if(StringUtils.isNotBlank(type)){
            searchParam.getParamMap().put("type", type);
        }
        if(status != null){
            searchParam.getParamMap().put("status", status);
        }
        ModelAndView view = new ModelAndView("/intellectual/cost/monitorList");
        List<PatentCostMonitorListDto> list = patentCostMonitorService.getList(searchParam);
        view.addObject("list", list);
        view.addObject("type", type);
        return view;
    }

    @RequestMapping("/updateDeadlineWarn")
    @ResponseBody
    public BaseApi updateDeadlineWarn(WarnTimeLine warnTimeLine){
        BaseApi api = new BaseApi();
        try {
            LoginUser user = LoginUser.loginUser(request);
            if(StringUtils.isBlank(warnTimeLine.getCompanyId())){
                warnTimeLine.setCompanyId(user.getCompanyId());
            }
            warnTimeLineService.updateById(warnTimeLine, user.getId());
        }catch (Exception e){
            e.printStackTrace();
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/updateSelective")
    @ResponseBody
    public BaseApi updateStatus(PatentCostMonitor monitor){
        BaseApi api = new BaseApi();
        try {
            String userId = LoginUser.loginUser(request).getId();
            patentCostMonitorService.update(monitor, userId);
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/godSetMonitor")
    public ModelAndView godSetMonitor(Integer type, String companyId){
        ModelAndView view = new ModelAndView("/intellectual/cost/setWarnTimeLine");
        if(StringUtils.isBlank(companyId)){
            companyId = LoginUser.loginUser(request).getCompanyId();
        }
        WarnTimeLine warnTimeLine = warnTimeLineService.getByCompanyId(companyId);

        view.addObject("warnTimeLine", warnTimeLine);
        view.addObject("type" , type);
        return view;
    }
}
