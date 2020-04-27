package com.hcis.ipr.wechat.web;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.common.utils.BaseApi;
import com.hcis.ipr.intellectual.cost.dto.PatentCostListDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorListDto;
import com.hcis.ipr.intellectual.cost.entity.PatentCost;
import com.hcis.ipr.intellectual.cost.entity.PatentCostMonitor;
import com.hcis.ipr.intellectual.cost.service.PatentCostMonitorService;
import com.hcis.ipr.intellectual.cost.service.PatentCostService;
import com.hcis.ipr.intellectual.patent.entity.Patent;
import com.hcis.ipr.intellectual.patent.service.PatentService;
import com.hcis.ipr.oauth.service.OauthPatentService;
import com.hcis.ipr.wechat.service.IWechatService;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.rngom.parse.host.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @date 2019/11/18
 **/
@Controller
@RequestMapping("/wechat/data")
public class WeChatPatentDataController extends WeixinControllerSupport {

    private static final Logger LOG = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private PatentCostMonitorService patentCostMonitorService;

    @Autowired
    private PatentService patentService;

    @Autowired
    private PatentCostService patentCostService;

    @Autowired
    private IWechatService wechatService;

    @Override
    protected String getToken() {
        return apiConfig.getToken();
    }

    @Override
    protected String getAppId() {
        return apiConfig.getAppid();
    }

    @Override
    protected String getAESKey() {
        return apiConfig.getAESKey();
    }

    @RequestMapping("/monitor/list")
    @ResponseBody
    public BaseApi monitorList(@RequestBody Map<String, Object> map) {
        BaseApi api = new BaseApi();
        try {

            /**
             * 整理数据
             */
            SearchParam searchParam = convertParam(map, false);

            List<PatentCostMonitorListDto> list = patentCostMonitorService.getList(searchParam);

            int totalPage = searchParam.getPagination().getRowCount() / searchParam.getPagination().getPageSize();
            totalPage += searchParam.getPagination().getRowCount() % searchParam.getPagination().getPageSize() > 0 ? 1 : 0;

            Map<String, Object> reData = new HashMap<>(2);
            reData.put("list", list);
            reData.put("totalPage", totalPage);
            api.setData(reData);
        } catch (Exception e) {
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/patent/list")
    @ResponseBody
    public BaseApi patentList(@RequestBody Map<String, Object> map){
        BaseApi api = new BaseApi();
        try {
            /**
             * 整理数据
             */
            SearchParam searchParam = convertParam(map, false);

            List<Patent> list = patentService.patentList(searchParam);

            int totalPage = searchParam.getPagination().getRowCount() / searchParam.getPagination().getPageSize();
            totalPage += searchParam.getPagination().getRowCount() % searchParam.getPagination().getPageSize() > 0 ? 1 : 0;

            Map<String, Object> reData = new HashMap<>(2);
            reData.put("list", list);
            reData.put("totalPage", totalPage);
            api.setData(reData);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/patent/detail")
    @ResponseBody
    public BaseApi patentDetail(String id){
        BaseApi api = new BaseApi();
        try {
            Patent patent = patentService.getPatentInfo(id);
            //转换一下结果
            patent.setPatentType(DictionaryUtils.getEntry("IPR_PATENT_TYPE", patent.getPatentType()).getDictName());
            patent.setAuthorCountry(DictionaryUtils.getEntry("IPBOX_PATENT_AUTHOR_COUNTRY", patent.getAuthorCountry()).getDictName());
            api.setData(patent);
        }catch (Exception e){
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/cost/list")
    @ResponseBody
    public BaseApi costList(@RequestBody Map<String, Object> map){
        BaseApi api = new BaseApi();
        try {
            /**
             * 整理数据
             */
            SearchParam searchParam = convertParam(map, false);

            List<PatentCostListDto> list = patentCostService.selectList(searchParam);

            int totalPage = searchParam.getPagination().getRowCount() / searchParam.getPagination().getPageSize();
            totalPage += searchParam.getPagination().getRowCount() % searchParam.getPagination().getPageSize() > 0 ? 1 : 0;

            Map<String, Object> reData = new HashMap<>(2);
            reData.put("list", list);
            reData.put("totalPage", totalPage);
            api.setData(reData);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/cost/detail")
    @ResponseBody
    public BaseApi costDetail(String id){
        BaseApi api = new BaseApi();
        try {
            api.setData(patentCostService.getPatentDetail(id));
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/search/patents")
    @ResponseBody
    public BaseApi searchPatents(@RequestBody Map<String, Object> map){

        BaseApi api = new BaseApi();
        try {
            /**
             * 整理数据
             */
            SearchParam searchParam = convertParam(map, true);

            List<Patent> list = wechatService.searchPatents(searchParam);

            int totalPage = searchParam.getPagination().getRowCount() / searchParam.getPagination().getPageSize();
            totalPage += searchParam.getPagination().getRowCount() % searchParam.getPagination().getPageSize() > 0 ? 1 : 0;

            Map<String, Object> reData = new HashMap<>(2);
            reData.put("list", list);
            reData.put("totalPage", totalPage);
            api.setData(reData);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/search/detail")
    @ResponseBody
    public BaseApi searchPatentDetail(String appNumber){
        BaseApi api = new BaseApi();
        try {
            //参数
            SearchParam searchParam = new SearchParam();
            searchParam.getParamMap().put("appNumber", appNumber);
            Pagination pagination = new Pagination();
            pagination.setPageSize(10);
            pagination.setCurrentPage(1);
            searchParam.setPagination(pagination);

            List<Patent> list = wechatService.searchPatents(searchParam);
            if(!CollectionUtils.isEmpty(list)){
                //
                Patent patent = list.get(0);
                patent.setPatentType(DictionaryUtils.getEntry("IPBOX_IPR_PATENT_TYPE", patent.getPatentType()).getDictName());
                api.setData(patent);
            }
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }

    private SearchParam convertParam(Map<String, Object> map, boolean isSearchPatent){
        //整理参数
        SearchParam searchParam = new SearchParam();
        String type = String.valueOf(map.get("type"));
        Integer status = Integer.valueOf(map.get("status").toString());
        Integer page = (Integer) map.get("currentPage");
        Integer size = (Integer) map.get("pageSize");
        searchParam.getParamMap().put("type", type);
        searchParam.getParamMap().put("status", status);
        searchParam.getParamMap().put("patentName", map.get("patentName"));
        searchParam.getParamMap().put("appNumber", map.get("appNumber"));
        searchParam.getParamMap().put("applyer", map.get("applyer"));

        if(isSearchPatent){
            searchParam.getParamMap().put("category", map.get("category"));
            searchParam.getParamMap().put("inventor", map.get("inventor"));
            searchParam.getParamMap().put("agents", map.get("agents"));
            searchParam.getParamMap().put("patentType", map.get("patentType"));
            searchParam.getParamMap().put("appDateStart", map.get("appDateStart"));
            searchParam.getParamMap().put("appDateEnd", map.get("appDateEnd"));
        }

        Pagination pagination = new Pagination();
        pagination.setPageSize(size);
        pagination.setCurrentPage(page);
        searchParam.setPagination(pagination);

        return searchParam;
    }
}
