package com.hcis.ipanther.common.login.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

/**
 * @author lianghuahuang
 * 
 */
@Repository
public class LoginUserDao extends MyBatisDao {

	public LoginUser selectUserView(LoginUser loginUser) {
		return (LoginUser) this.selectForOneVO(namespace + ".selectUserView", loginUser);
	}

	public LoginUser selectUserViewById(String userId) {
		return (LoginUser) this.selectForOneVO(namespace + ".selectUserViewById", userId);
	}

	public LoginUser selectUserViewByPaperworkNo(String account) {
		return (LoginUser) this.selectForOneVO(namespace + ".selectUserViewByPaperworkNo", account);
	}

	public LoginUser selectUserViewByAccount(String account) {
		return (LoginUser) this.selectForOneVO(namespace + ".selectUserViewByAccount", account);
	}

	public LoginUser selectUserViewByWechatId(String wechatId) {
		return (LoginUser) this.selectForOneVO(namespace + ".selectUserViewByWechatId", wechatId);
	}

	public List<LoginUser> selectAllLoginUser() {
		return this.selectForList(namespace + ".selectAllLoginUser");
	}

	public LoginUser getUserByPhoneAndWechatId(LoginUser user) {
		return this.getSqlSession().selectOne(namespace + ".getUserByPhoneAndWechatId", user);
	}
}
