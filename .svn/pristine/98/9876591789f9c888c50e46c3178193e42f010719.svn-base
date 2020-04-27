package com.hcis.ipanther.common.user.dao;

import com.hcis.ipanther.common.user.entity.WechatUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

/**
 * @author zhw
 * @date 2019/11/13
 **/
@Repository("wechatUserDao")
public class WechatUserDao extends MyBatisDao {
    public WechatUser getByOpenId(String openId) {
        return this.getSqlSession().selectOne(namespace + ".getByOpenId", openId);
    }
}
