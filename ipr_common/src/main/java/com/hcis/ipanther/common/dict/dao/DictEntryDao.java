package com.hcis.ipanther.common.dict.dao;

import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class DictEntryDao extends MyBatisDao
{
  public List<DictEntry> selectAll(SearchParam searchParam) {
    return getSqlSession().selectList(this.namespace + ".selectAll", searchParam);
  }

  public List<Map<String, Object>> selectDictEntryOptions() {
    return selectForList(this.namespace + ".selectDictEntryOptions");
  }

  public int checkSameValue(DictEntry dictEntry) {
    return selectForInt(this.namespace + ".checkSameValue", dictEntry);
  }

  public int deleteByIdArray(String[] idArray) {
    return delete(this.namespace + ".deleteByIdArray", idArray);
  }

  public Map<String, DictEntry> selectByObjectForMap(DictEntry dictEntry) {
    return selectForMap(this.namespace + ".selectByObjectForMap", dictEntry, "dictValue");
  }

  public List<DictEntry> selectDictLike(DictEntry t) {
    return selectForList(this.namespace + ".selectDictLike", t);
  }

  public List<DictEntry> getByTypeCode(String typeCode) {
    return selectForList(namespace + ".getByTypeCode", typeCode);
  }
}