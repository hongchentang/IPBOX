package com.hcis.ipr.oauth.web;

import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.HttpPostClient;
import com.hcis.ipanther.core.utils.JSONUtils;
import com.hcis.ipr.cms.config.service.ICmsConfigService;
import com.hcis.ipr.common.utils.BaseApi;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.oauth.service.OauthPatentService;
import com.hcis.ipr.oauth.utils.HttpOauthClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhw
 * @date 2019/11/20
 **/
@RequestMapping("/oauth")
@Controller
public class OauthController extends BaseController {

    private static final String CLIENT_ID =  AppConfig.getProperty("common", "oauth.client.id");
    private static final String CLIENT_SECRET =  AppConfig.getProperty("common", "oauth.client.secret");
    private static final String REDIRECT_URL = AppConfig.getProperty("common", "oauth.redirect.url");
    private static final String RESPONSE_TYPE = AppConfig.getProperty("common", "oauth.response.type");
    private static final String GRANT_TYPE = AppConfig.getProperty("common", "oauth.grant.type");

    @Autowired
    private ICmsConfigService cmsConfigService;

    @Autowired
    private OauthPatentService oauthPatentService;

    /**
     * 获取Authorization Code
     */
    @RequestMapping("/authorize")
    @ResponseBody
    public void authorize(HttpServletResponse response){
        try {
        //请求code
        String url = "https://open.cnipr.com/gdip/authorize" + "?" +
                    "client_id=" + CLIENT_ID + "&" +
                    "redirect_uri=" + REDIRECT_URL + "&" +
                    "response_type=" + RESPONSE_TYPE + "&" +
                    "state=1c";

        response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/token")
    @ResponseBody
    public BaseApi getToken(@Param("code") String code, @Param("openId") String openId, @Param("openKey") String openKey){

        logger.debug("------> " + code + " =====> " + openId + " - - - - ->" + openKey);
        //请求access_token
        HttpOauthClient client = new HttpOauthClient();

        String url = "https://open.cnipr.com/gdip/access_token" + "?" +
                "client_id=" + CLIENT_ID + "&" + "client_secret=" + CLIENT_SECRET + "&" +
                "redirect_uri=" + REDIRECT_URL + "&" + "grant_type=" + GRANT_TYPE + "&" +
                "code=" + code + "&" + "state=1C"   ;
        String reJsonStr = client.get(url);
        //整理参数，保存在数据库里面
        cmsConfigService.saveOauth2Config(reJsonStr, openId, openKey);

         return new BaseApi();
    }

    @RequestMapping("/test")
    @ResponseBody
    public Object test(@RequestBody Map<String, Object> map ){
        //Map<String, Object> map = new HashMap<>();
        if((System.currentTimeMillis() % 2) == 0){

            map.put("status", 0);
            map.put("message", "message");
            map.put("expires_in", 60000);
            map.put("refresh_token", "refresh_token");
            map.put("access_token", "access_token");
        }else {
            map.put("code", 500);
            map.put("msg", 500);
        }

        return map;
    }

    @RequestMapping("/testData")
    @ResponseBody
    public Object testData(){

        String url = "https://open.cnipr.com/cnipr-api-ms/v1/api/search/sf1/" + CLIENT_ID;

        String resultData = oauthPatentService.test(url);

         return resultData;
    }

    @RequestMapping("/refreshToken")
    @ResponseBody
    public BaseApi refreshToken(){
        BaseApi api = new BaseApi();
        try {
            oauthPatentService.refreshToken(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL);
        }catch (Exception e){
            api.setError(e.getMessage());
        }
        return api;
    }
}
