package com.hcis.ipanther.common.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TaskDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public int deleteIpantherAdminRegions() {
		
		//sqlSessionTemplate.delete(statement);
		
		String sqlStr = "drop TABLE IPANTHER_ADMIN_REGIONS";
		return jdbcTemplate.update(sqlStr);
	}

	@Transactional
	public int deleteIpantherAuthPrivilege() {
		String sqlStr = "drop TABLE IPANTHER_AUTH_PRIVILEGE";
		return jdbcTemplate.update(sqlStr);
	}

	@Transactional
	public int deleteIpantherAuthRole() {
		String sqlStr = "drop TABLE IPANTHER_AUTH_ROLE";
		return jdbcTemplate.update(sqlStr);
	}

	@Transactional
	public int deleteIpantherAuthRolepri() {
		String sqlStr = "drop TABLE IPANTHER_AUTH_ROLEPRI";
		return jdbcTemplate.update(sqlStr);
	}

	@Transactional
	public int deleteIpantherDictEntry() {
		String sqlStr = "drop TABLE IPANTHER_DICT_ENTRY";
		return jdbcTemplate.update(sqlStr);
	}

	@Transactional
	public int deleteIpantherDictType() {
		String sqlStr = "drop TABLE IPANTHER_DICT_TYPE";
		return jdbcTemplate.update(sqlStr);
	}

	@Transactional
	public int deleteIpantherSeq() {
		String sqlStr = "drop TABLE IPANTHER_SEQ";
		return jdbcTemplate.update(sqlStr);
	}

	@Transactional
	public int deleteIpantherAuthModule() {
		String sqlStr = "drop TABLE IPANTHER_AUTH_MODULE";
		return jdbcTemplate.update(sqlStr);
	}

	@Transactional
	public int deleteUserview() {
		String sqlStr = "drop  VIEW USERVIEW";
		return jdbcTemplate.update(sqlStr);
	}

	/*
	 * public int deleteIpantherAdminRegions() { return
	 * this.delete(namespace+".deleteIpantherAdminRegions"); }
	 */

}
