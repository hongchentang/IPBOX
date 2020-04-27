package com.hcis.ipanther.common.user.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;

/**
 * @author z
 */
public class WechatUser extends BaseEntity {
    private static final long serialVersionUID = -8287470276368576645L;

    private String openId;

    private String nickName;

    private String phone;

    private String userId;

    private Integer status;

    private Date updatedBy;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Date updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public enum WeChatUserStatus{

        /**
         *
         */
        UN_REGISTER(0, "用户未注册"),
        REGISTER(1, "用户已注册但未绑定IPbox用户"),
        REGISTER_AND_BIND(2, "用户绑定IPbox用户");

        private Integer status;

        private String describe;

        WeChatUserStatus(Integer status, String describe) {
            this.status = status;
            this.describe = describe;
        }

        public Integer getStatus() {
            return status;
        }

        public String getDescribe() {
            return describe;
        }
    }
}