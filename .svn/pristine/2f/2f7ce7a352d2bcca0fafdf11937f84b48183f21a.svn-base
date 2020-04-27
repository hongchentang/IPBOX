package com.hcis.ipr.oauth.service;

import com.hcis.ipanther.core.web.vo.SearchParam;

import java.util.Map;

/**
 * @author zhw
 * @date 2019/11/20
 **/
public interface OauthPatentService {

    /**
     *
     * @return
     * @param url
     */
    String test(String url);

    /**
     * 查询专利列表数据
     * @param searchParam 请求参数封装 参考 @PatentQueryParamEnum, 而且且需要设置分页器
     * @param fields 请求的数据字段，多字段用逗号（英文）分隔
     * @return 接口数据
     */
    Map<String, Object> list(SearchParam searchParam, String fields);

    /**
     * 更新授权数据
     * @param clientId
     * @param clientSecret
     * @param redirectUrl
     */
    void refreshToken(String clientId, String clientSecret, String redirectUrl);

    /**
     * 获取外观图片路径
     * @param id
     * @return
     */
    String getPatentPdf(String id);

    /**
     * 获取PDF全文路径
     * @param id
     * @return
     */
    String getPatentAppearance(String id);

    /**
     * 批量下载接口数据插入到本地数据库
     * @param param
     */
    void batchInsert(SearchParam param);
}
