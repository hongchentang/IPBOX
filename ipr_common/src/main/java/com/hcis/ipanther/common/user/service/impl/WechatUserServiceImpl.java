package com.hcis.ipanther.common.user.service.impl;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.dao.WechatUserDao;
import com.hcis.ipanther.common.user.entity.WechatUser;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.WechatUserService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhw
 * @date 2019/11/13
 **/
@Service
public class WechatUserServiceImpl extends BaseServiceImpl<WechatUser> implements WechatUserService {

    @Autowired
    private WechatUserDao wechatUserDao;

    @Autowired
    private IUserService userService;

    @Override
    public MyBatisDao getBaseDao() {
        return wechatUserDao;
    }

    @Override
    public WechatUser getByOpenId(String openId) {
        return wechatUserDao.getByOpenId(openId);
    }

    @Override
    public void save(WechatUser user) {
        wechatUserDao.insert(user);
    }

    @Override
    public void register(Map<String, Object> map) {

        String userId = (String) map.get("id");
        String phone =(String) map.get("phone");
        String nickName =(String) map.get("nickName");
        WechatUser user = new WechatUser();
        user.setId(userId);
        user.setPhone(phone);
        user.setStatus(1);
        user.setNickName(nickName);
        user.setStatus(WechatUser.WeChatUserStatus.REGISTER.getStatus());
        this.update(user, userId);
    }

    @Override
    public void bindOrUnBind(String id) {
        WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(id);
        if(wechatUser == null){
            throw new IllegalArgumentException("找不到所属微信用户！");
        }

        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        Integer status = wechatUser.getStatus();
        if(status.equals(WechatUser.WeChatUserStatus.UN_REGISTER.getStatus())){
            throw new IllegalArgumentException("请先绑定手机号！");
        }else if(status.equals(WechatUser.WeChatUserStatus.REGISTER.getStatus())){
            //通过
            wechatUser.setStatus(WechatUser.WeChatUserStatus.REGISTER_AND_BIND.getStatus());
            wechatUser.setUserId(loginUser.getId());
            wechatUserDao.updateByPrimaryKeySelective(wechatUser);
        }else if(status.equals(WechatUser.WeChatUserStatus.REGISTER_AND_BIND.getStatus())){
            //借绑
            wechatUser.setStatus(WechatUser.WeChatUserStatus.REGISTER.getStatus());
            wechatUser.setUserId(StringUtils.EMPTY);
            wechatUserDao.updateByPrimaryKeySelective(wechatUser);
        }else {
            //未知
            throw new IllegalArgumentException("未知状态！");
        }

    }
}
