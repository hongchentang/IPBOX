package com.hcis.ipanther.common.useremail.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.useremail.entity.UserEmail;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository("UserEmailDao")
public class UserEmailDao extends MyBatisDao {
	public List<UserEmail> selectList(SearchParam searchParam) {
		return this.getSqlSession().selectList(namespace + ".selectList", searchParam);
	}

	public int updateById(String ID) {

		return this.getSqlSession().update(namespace + ".updateById", ID);
	}

	public int updateByAll(UserEmail UserEmail) {

		return this.getSqlSession().update(namespace + ".updateByAll", UserEmail);
	}

	public List<UserEmail> selectById(String ID) {
		return this.getSqlSession().selectList(namespace + ".selectById", ID);
	}
}
