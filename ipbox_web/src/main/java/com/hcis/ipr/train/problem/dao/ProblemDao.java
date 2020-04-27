package com.hcis.ipr.train.problem.dao;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.problem.entity.Problem;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
@Repository("problemDao")
public class ProblemDao extends MyBatisDao
{
  public List<Map<String, Object>> selectAll(SearchParam searchParam)
  {
    return getSqlSession().selectList(this.namespace + ".selectAll", searchParam);
  }

  public List<Map<String, Object>> selectDictTypeOptions() {
    return selectForList(this.namespace + ".selectDictTypeOptions");
  }

  public int deleteByIdArray(String[] idArray) {
    return delete(this.namespace + ".deleteByIdArray", idArray);
  }
}