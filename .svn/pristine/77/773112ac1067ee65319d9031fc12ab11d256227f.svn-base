package com.hcis.ipanther.common.dict.service;

import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.core.security.entity.Loginer;
import com.hcis.ipanther.core.web.vo.SearchParam;
import java.io.File;
import java.util.List;
import java.util.Map;

public abstract interface IDictEntryService
{
  public abstract List<DictEntry> listDictEntry(SearchParam paramSearchParam);

  public abstract List<Map<String, Object>> selectDictEntryOptions();

  public abstract int delete(DictEntry paramDictEntry);

  public abstract Map<String, Object> getByPk(DictEntry paramDictEntry);

  public abstract boolean checkSameValue(DictEntry paramDictEntry);

  public abstract int addOrUpdate(DictEntry paramDictEntry, Loginer paramLoginer);

  public abstract int delete(String[] paramArrayOfString1, String[] paramArrayOfString2);

  public abstract Map<String, Object> importDictEntry(File paramFile);

  public abstract List<DictEntry> selectDictLike(DictEntry paramDictEntry);

  /**
   * 根据类型获取子数据
   * @param typeCode
   * @return
   */
    List<DictEntry> getByTypeCode(String typeCode);
}