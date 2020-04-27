package com.hcis.ipanther.common.user.service;

import com.hcis.ipanther.common.user.entity.WechatUser;
import com.hcis.ipanther.core.service.IBaseService;

import java.util.Map;

/**
 * @author zhw
 * @date 2019/11/13
 **/
public interface WechatUserService extends IBaseService<WechatUser> {

    /**
     * 查询微信用户
     * @param openId
     * @return
     */
    WechatUser getByOpenId(String openId);

    /**
     *
     * @param user
     */
    void save(WechatUser user);

    /**
     * 微信用户注册
     * @param map
     */
    void register(Map<String, Object> map);

    /**
     * 绑定和取消绑定用户
     * @param id
     */
    void bindOrUnBind(String id);
}
