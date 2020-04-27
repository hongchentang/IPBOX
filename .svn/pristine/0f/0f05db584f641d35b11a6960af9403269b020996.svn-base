package com.hcis.ipr.oauth.service.impl;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.*;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.config.entity.CmsConfig;
import com.hcis.ipr.cms.config.service.ICmsConfigService;
import com.hcis.ipr.intellectual.patent.dao.PatentDao;
import com.hcis.ipr.intellectual.patent.entity.Patent;
import com.hcis.ipr.intellectual.patent.enumeration.LegalStatusEnum;
import com.hcis.ipr.oauth.enumDto.PatentQueryParamEnum;
import com.hcis.ipr.oauth.service.OauthPatentService;
import com.hcis.ipr.oauth.utils.HttpOauthClient;
import com.hcis.ipr.oauth.utils.ResolveUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhw
 * @date 2019/11/20
 **/
@Service
public class OauthPatentImpl implements OauthPatentService {

    private static final String SUCCESS = "0";

    private static final  String CLIENT_ID = AppConfig.getProperty("common", "oauth.client.id");

    private static final String PATENT_LIST_URL = "https://open.cnipr.com/cnipr-api/v1/api/search/sf1/" + CLIENT_ID;

    private static final String REFRESH_TOKEN_URL = "https://open.cnipr.com/gdip/access_token";
    private static final String APPEARANCE_URL = "https://open.cnipr.com/cnipr-api/v1/api/picture/pi14/" + CLIENT_ID;
    private static final String PDF_URL = "https://open.cnipr.com/cnipr-api/v1/api/picture/pi11/" + CLIENT_ID;

    private static final Pattern P = Pattern.compile("paramMap\\[(.*?)\\]");

    @Autowired
    private ICmsConfigService cmsConfigService;

    @Autowired
    private PatentDao patentDao;

    @Override
    public String test(String url){

        HttpOauthClient client = new HttpOauthClient();

       /* Map<String, Object> map = new HashMap<>();
        //openid
        map.put("openid", "1f562f54175c1984bbc06c33328e1c1a");
        //accessToken
        map.put("access_token", "99B6D84A33E80434D261D5D327B6474E");
        //检索表达式
        map.put("exp", "申请号=CN201621347999.8");
        //检索数据库
        map.put("dbs", "SYXX");
        //检索起始位置
        map.put("from", 0);
        //检索数量
        map.put("size", 10);*/

       String requestParam = "exp=申请号=(CN201621347999.8)&openid=1f562f54175c1984bbc06c33328e1c1a&access_token=99B6D84A33E80434D261D5D327B6474E&" +
                "dbs=SYXX&from=0&size=10";

        //return client.post(url, JSONUtils.getJSONString(map));

        return client.post(url, requestParam);
    }

    @Override
    public Map<String, Object> list(SearchParam searchParam, String fields) {

        String exp = (String) searchParam.getParamMap().get("exp");
        if(StringUtils.isEmpty(exp)){
            //构建检索式
            exp = buildQueryParam(searchParam);
        }

        if(StringUtils.isBlank(exp)){
            //默认查全部中国的
            exp = "国家名=(中国)";
        }

        //专业页码
        Pagination pagination = searchParam.getPagination();
        int pageSize = pagination.getPageSize();
        int currentPage = pagination.getCurrentPage();
        currentPage = (currentPage - 1) * pageSize;
        if(currentPage < 0){
            currentPage = 0;
        }

        //标准参数
        Map<String, Object> configMap = getAccessInfo();
        String openid = (String) configMap.get("openid");
        String accessToken = (String) configMap.get("access_token");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("openid", openid));
        params.add(new BasicNameValuePair("access_token", accessToken));
        params.add(new BasicNameValuePair("exp", exp));
        params.add(new BasicNameValuePair("dbs", "FMZL"));
        params.add(new BasicNameValuePair("dbs", "SYXX"));
        params.add(new BasicNameValuePair("dbs", "WGZL"));
        params.add(new BasicNameValuePair("dbs", "FMSQ"));
        params.add(new BasicNameValuePair("option", "2"));
        params.add(new BasicNameValuePair("order", "-appDate"));
        params.add(new BasicNameValuePair("from", "" + currentPage));
        params.add(new BasicNameValuePair("size", "" + pageSize));

        if(StringUtils.isNotBlank(fields)){
            params.add(new BasicNameValuePair("displayCols", fields));
        }else {
            params.add(new BasicNameValuePair("displayCols", "basic"));
        }

        HttpOauthClient client = new HttpOauthClient();
        String jsonResult = client.postFormUrlencoded(PATENT_LIST_URL, params);

        return JSONUtils.getJSONMap(jsonResult);
    }

    @Override
    public void refreshToken(String clientId, String clientSecret, String redirectUrl) {
        Map<String, Object> map = this.getAccessInfo();

        String refreshToken = (String) map.get("refresh_token");
        HttpOauthClient client = new HttpOauthClient();

        String requestUrl = REFRESH_TOKEN_URL + "?client_id=" + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUrl + "&grant_type=refresh_token" +
                "&refresh_token=" + refreshToken + "&state=123";

        String reStr = client.get(requestUrl);
        Map<String, Object> reMap = JSONUtils.getJSONMap(reStr);
        Integer status = (Integer) reMap.get("status");
        if (status == 0) {
            CmsConfig cmsConfig = cmsConfigService.read("OAUTH2_ACCESS_TOKEN");
            cmsConfig.setConfigValue(reStr);
            cmsConfigService.update(cmsConfig, null);
        } else {
            throw new  IllegalArgumentException((String) reMap.get("message"));
        }
    }

    @Override
    public String getPatentPdf(String id) {
        Map<String, Object> map = this.getAccessInfo();

        String accessToken = (String) map.get("access_token");
        String openId = (String) map.get("openid");
        HttpOauthClient client = new HttpOauthClient();

        String requestUrl = PDF_URL + "?" + "&access_token=" + accessToken + "&openid=" + openId + "&pid=" + id;

        String reStr = client.get(requestUrl);
        Map<String, Object> reMap = JSONUtils.getJSONMap(reStr);
        Integer status = (Integer) reMap.get("status");
        if (status == 0) {
            return (String) reMap.get("message");
        } else {
            return "";
        }
    }

    @Override
    public String getPatentAppearance(String id) {
        Map<String, Object> map = this.getAccessInfo();

        String accessToken = (String) map.get("access_token");
        String openId = (String) map.get("openid");
        HttpOauthClient client = new HttpOauthClient();

        String requestUrl = APPEARANCE_URL + "?" + "&access_token=" + accessToken + "&openid=" + openId + "&pid=" + id + "&target=1";

        String reStr = client.get(requestUrl);
        Map<String, Object> reMap = JSONUtils.getJSONMap(reStr);
        Integer status = (Integer) reMap.get("status");
        if (status == 0) {
            return (String) reMap.get("message");
        } else {
            return "";
        }
    }

    @Override
    public void batchInsert(SearchParam param) {

        String files ="appNumber,title,patType,appDate,applicantName,appCoun,legalStatus,inventorName,pubNumber,pubDate,grantDate"+
                ",priorityNo,priorityInfo,agentName,agencyName";

        Map<String, Object> map = list(param, files);
        if(map.get("status").equals(SUCCESS)){

            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            List<Map<String, Object>> results = (List<Map<String, Object>>) map.get("results");
            List<Patent> patents = new ArrayList<>();
            for(Map<String, Object> query: results){

                Patent patent = new Patent();
                patent.setDefaultValue();
                patent.setId(UUIDUtils.getUUId());
                patent.setCreator(loginUser.getId());
                patent.setCompanyId(loginUser.getCompanyId());
                patent.setCompleteUnit(loginUser.getFirstDeptId());
                patent.setDepId(loginUser.getFirstDeptId());

                //专利号/申请号
                patent.setAppNumber(((List<String>) query.get("appNumber")).get(0));
                //专利名称
                patent.setPatentName((String) query.get("title"));
                //专利类型
                Integer patentType = Integer.valueOf(query.get("patType").toString());
                if(patentType > 4){
                    //pct类型暂时做跳过
                    // TODO: 2019/12/20
                    continue;
                }
                patent.setPatentType(patentType+"");
                //申请日
                patent.setAppDate(DateUtils.parseDate((String) query.get("appDate"), "yyyy.MM.dd"));
                //申请人/专利权人
                List<String> applicantNames = (List<String>) query.get("applicantName");
                if(!CollectionUtils.isEmpty(applicantNames)){
                    String applicantNameStr = "";
                    for(String applicantName: applicantNames){
                        if(StringUtils.isBlank(applicantNameStr)){
                            applicantNameStr += applicantName;
                        }else {
                            applicantNameStr += "," + applicantName;
                        }
                    }
                    patent.setApplyer(applicantNameStr);
                }
                // 申请国
                String appCountry = (String) query.get("appCoun");
                if(appCountry.equals("CN")){
                    patent.setAuthorCountry("0");
                }else if(appCountry.equals("US")){
                    patent.setAuthorCountry("1");
                }else if(appCountry.equals("EU")){
                    patent.setAuthorCountry("4");
                }else if(appCountry.equals("JP")){
                    patent.setAuthorCountry("2");
                }else {
                    patent.setAuthorCountry("5");
                }

                //法律状态
                String legalStatus = (String) query.get("legalStatus");
                String[] legalStatusList = legalStatus.split(";");
                if(legalStatusList.length > 0){
                    legalStatus = legalStatusList[legalStatusList.length -1];
                    String reLegalStatus = ResolveUtils.resolve(legalStatus, "#" ,1);
                    if(reLegalStatus.equals(LegalStatusEnum.OPEN.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.OPEN.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.DEEMED_WIDTH_DRAW.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.DEEMED_WIDTH_DRAW.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.ACTIVE_WIDTH_DRAW.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.ACTIVE_WIDTH_DRAW.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.REJECT.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.REGISTER.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.INVALID.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.INVALID.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.DEEMED_GIVE_UP.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.DEEMED_GIVE_UP.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.ACTIVE_GIVE_UP.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.ACTIVE_GIVE_UP.getVal()+"");
                    }else if(reLegalStatus.equals(LegalStatusEnum.AUTHORIZE.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.AUTHORIZE.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.KEEP.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.KEEP.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.END.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.END.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.EXPIRATION.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.EXPIRATION.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.INTERRUPT.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.INTERRUPT.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.PRESERVATION.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.PRESERVATION.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.NOTICE.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.NOTICE.getVal()+"");
                    }else if(reLegalStatus.contains(LegalStatusEnum.PLEDGE.getAliasName())){
                        patent.setLegalStatus(LegalStatusEnum.PLEDGE.getVal()+"");
                    }


                }

                //发明人， 第一发明人
                List<String> inventors = (List<String>)query.get("inventorName");
                if(!CollectionUtils.isEmpty(inventors)){
                    String inventorStr = "";
                    for(String inventor: inventors){
                        if(StringUtils.isBlank(inventorStr)){
                            inventorStr += inventor;
                            //第一发明人
                            patent.setFirstInventor(inventor);
                        }else {
                            inventorStr += "," + inventor;
                        }
                    }
                    patent.setInventor(inventorStr);
                }

                //公开(公告)号
                List<String> pubNumberList = (List<String>) query.get("pubNumber");
                if(!CollectionUtils.isEmpty(pubNumberList)){
                    patent.setPublicationNumber(pubNumberList.get(0));
                    patent.setAnnouncementNumber(pubNumberList.get(0));
                }

                //公开（公告）日
                Date pubDate = DateUtils.parseDate((String) query.get("pubDate"), "yyyy.MM.dd");
                patent.setPublicationDate(pubDate);
                patent.setAnnouncementDate(pubDate);

                //是否授权，授权日
                Date grantDate = DateUtils.parseDate((String) query.get("grantDate"), "yyyy.MM.dd");
                if(grantDate != null){
                    patent.setIsAuthorize("0");
                    patent.setAuthorizeDate(grantDate);
                }

                //优先权号
                List<List<String>> priorityNoListList = (List<List<String>>) query.get("priorityNo");
                if(!CollectionUtils.isEmpty(priorityNoListList)){
                    List<String> priorityNoList = priorityNoListList.get(0);
                    if(!CollectionUtils.isEmpty(priorityNoList)){
                        patent.setPriorityNumber(priorityNoList.get(0));
                    }
                }

                //优先权日
                Map<String, Object> priorityObj = (Map<String, Object>) query.get("PriorityInfoPojo");
                if(priorityObj != null){
                    Date priorityDate = DateUtils.parseDate((String) priorityObj.get("priorityDate"), "yyyy.MM.dd");
                    patent.setPriorityDate(priorityDate);
                }

                //代理人
                List<String> agentNameList = (List<String>) query.get("agentName");
                if(!CollectionUtils.isEmpty(agentNameList)){
                    String agentNameStr = "";
                    for(String agentName : agentNameList){
                        if(StringUtils.isBlank(agentNameStr)){
                            agentNameStr += agentName;
                        }else {
                            agentNameStr += "," + agentName;
                        }
                    }
                }

                //代理公司
                String agencyName = (String) query.get("agencyName");
                patent.setAgency(agencyName);

                patents.add(patent);
            }

            param.getParamMap().put("patents", patents);
            patentDao.batchInsert(param);
        }else {
            throw new IllegalArgumentException("没有找到专利信息!");
        }
    }

    private String buildQueryParam(SearchParam searchParam) {
        StringBuilder exp = new StringBuilder();
        Map<String, PatentQueryParamEnum> enumMap = PatentQueryParamEnum.toMap();
        Map<String, Object> requestParamMap = searchParam.getParamMap();
        Set<String> requestParamKeySet = requestParamMap.keySet();
        for(String requestParamKey : requestParamKeySet){
            Object param = requestParamMap.get(requestParamKey);
            if(param instanceof String || param instanceof  Integer){
                String paramStr = String.valueOf(param);
                if(StringUtils.isNotBlank(paramStr)){
                    getQueryParamStr(requestParamKey);
                    PatentQueryParamEnum paramEnum = enumMap.get(requestParamKey);
                    if(paramEnum == null){ continue; }
                    //如果是时间筛选的话，要去找有没有时间段筛选的
                    String endValue = "";
                    if(requestParamKey.contains("DateEnd")){
                        continue;
                    } else  if(requestParamKey.contains("Date")){
                        //构建时间段参数
                        String temp = requestParamKey.replace("Start", "End");
                        endValue = (String) requestParamMap.get(temp);
                    }

                    if(StringUtils.isBlank(exp.toString())){
                        exp.append(paramEnum.getName()).append("=(").append(paramStr);

                        //拼接时间段参数
                        if(StringUtils.isNotBlank(endValue)){
                            exp.append(" to ").append(endValue);
                        }

                        exp.append(")");
                    }else {
                        exp.append(" AND ").append(paramEnum.getName()).append("=(").append(paramStr);

                        //拼接时间段参数
                        if(StringUtils.isNotBlank(endValue)){
                            exp.append(" to ").append(endValue);
                        }
                        exp.append(")");
                    }
                }
            }
        }

        return exp.toString();
    }

    private Map<String, Object> getAccessInfo(){

        CmsConfig cmsConfig = cmsConfigService.read("OAUTH2_ACCESS_TOKEN");
        if(cmsConfig != null){
            String jsonValue = cmsConfig.getConfigValue();
            return JSONUtils.getJSONMap(jsonValue);
        }
        return null;
    }

    private static void getQueryParamStr(String source){
        Matcher m = P.matcher(source);
        while(m.find()) {
            source = m.group(1);
        }
    }
}
