package com.hcis.ipr.oauth.web;

import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.common.utils.BaseApi;
import com.hcis.ipr.oauth.service.OauthPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @date 2019/11/21
 **/
@Controller
@RequestMapping("/oauth/patent")
public class OauthPatentController extends BaseController {

    @Autowired
    private OauthPatentService oauthPatentService;

    @RequestMapping("/list")
    public ModelAndView list(SearchParam searchParam){

        ModelAndView view = new ModelAndView("/oauth/patent/list");

        Pagination pagination = searchParam.getPagination();
        if(pagination == null){
            pagination = new Pagination();
            pagination.setPageSize(10);
            pagination.setCurrentPage(1);
            searchParam.setPagination(pagination);
        }

        Map<String, Object> data = oauthPatentService.list(searchParam, null);
        String status = (String) data.get("status");
        if(status.equals("0")){
            view.addObject("data", data);
            pagination.setRowCount((Integer) data.get("total"));
        }

        return view;
    }

    @RequestMapping("/list4Json")
    @ResponseBody
    public BaseApi list4Json(SearchParam searchParam){
        BaseApi api = new BaseApi();

        try {
            ModelAndView view = this.list(searchParam);
            api.setData(view.getModelMap().get("data"));
            String str = "";
        }catch (Exception e){
            e.printStackTrace();
        }

        return api;
    }

    @RequestMapping("/goBestSearch")
    public ModelAndView goBestSearch(){
        return new ModelAndView("/oauth/patent/bestSearch");
    }

    @RequestMapping("/goDetail")
    public ModelAndView goDetail(String appNumber,  String pid){

        ModelAndView view = new ModelAndView("/oauth/patent/detailTab");
        view.addObject("appNumber", appNumber);
        view.addObject("pid", pid);
        return view;
    }

    @RequestMapping("/basicDetail")
    public ModelAndView basicDetail(String appNumber, Integer type){
        ModelAndView view = new ModelAndView("/oauth/patent/basicDetail");

        SearchParam searchParam = new SearchParam();
        Pagination pagination = new Pagination();
        searchParam.getParamMap().put("appNumber", appNumber);
        searchParam.setPagination(pagination);
        Map<String, Object> map;

        if(type != null){
            view.setViewName("/oauth/patent/detailDraws");
            if(type == 1){
                map = oauthPatentService.list(searchParam, "draws");
            }else if(type == 2){
                map = oauthPatentService.list(searchParam, "drawsPic");
            }else {
                map = new HashMap<>();
            }
        }else {
            map = oauthPatentService.list(searchParam, null);
        }

        List<Object> results = (List<Object>) map.get("results");
        if(!CollectionUtils.isEmpty(results)){
            view.addObject("oauthPatent", results.get(0));
        }

        view.addObject("type", type);
        return view;
    }

    @RequestMapping("/appearance")
    public ModelAndView patentAppearance(String id){
        ModelAndView view = new ModelAndView("/oauth/patent/detailAppearance");
        view.addObject("url", oauthPatentService.getPatentAppearance(id));
        return view;
    }

    @RequestMapping("/PDF")
    public ModelAndView patentPdf(String id){
        ModelAndView view = new ModelAndView("/oauth/patent/detailPDF");
        view.addObject("url", oauthPatentService.getPatentPdf(id));
        return view;
    }

    @RequestMapping("/batchInsert")
    @ResponseBody
    public BaseApi batchInsert(SearchParam param){
        BaseApi api = new BaseApi();
        try {
            oauthPatentService.batchInsert(param);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }
}
