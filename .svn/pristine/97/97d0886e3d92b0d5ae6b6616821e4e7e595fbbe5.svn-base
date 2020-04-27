package com.hcis.ipr.intellectual.patent.dao;

import com.hcis.datas.DataSource;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.patent.entity.Patent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("patentDao")
public class PatentDao extends MyBatisDao {

    public void deleteByPrimaryKeys(List<String> patentIds) {
		this.selectForList(namespace+".deleteByPrimaryKeys", patentIds);
    }

    public void updateBatch(List<Patent> list) {
        this.getSqlSession().update(namespace + ".updateBatch", list);
    }

    public List<Patent> listConcatMongoPatent() {
        return this.getSqlSession().selectList(namespace + ".listConcatMongoPatent");
    }

    public List<Patent> selectByPrimaryKeys(List<String> ids) {
        return this.getSqlSession().selectList(namespace + ".selectByPrimaryKeys", ids);
    }

    public void costedPatent(Patent patent) {
        this.getSqlSession().update(namespace + ".costedPatent", patent);
    }

    public void batchInsert(SearchParam param) {
        this.getSqlSession().insert(namespace + ".batchInsert", param);
    }

    public Map test(){
        return this.
            getSqlSession().
                selectOne(namespace + ".test", null);
    }

    public List<Patent> selectByAppNuber(List<String> list){

        return this.getSqlSession().selectList(namespace + ".selectByAppNuber",list);

    }
}