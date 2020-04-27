package com.hcis.ipanther.common.dict.dao;

import com.hcis.ipanther.common.dict.vo.DictType;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class DictTypeDao extends MyBatisDao
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

  public List<DictType> selectChildDictType(String parentCode) {
    return getSqlSession().selectList(this.namespace + ".selectChildDictType", parentCode);
  }
}