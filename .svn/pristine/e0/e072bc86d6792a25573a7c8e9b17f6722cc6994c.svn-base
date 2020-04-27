package com.hcis.ipanther.common.dict.service;

import com.hcis.ipanther.common.dict.vo.DictType;
import com.hcis.ipanther.core.security.entity.Loginer;
import com.hcis.ipanther.core.web.vo.SearchParam;
import java.io.File;
import java.util.List;
import java.util.Map;

public abstract interface IDictTypeService
{
  public abstract List<Map<String, Object>> list(SearchParam paramSearchParam);

  public abstract List<Map<String, Object>> selectDictTypeOptions();

  public abstract int delete(DictType paramDictType);

  public abstract DictType getByPk(DictType paramDictType);

  public abstract boolean checkSameCode(DictType paramDictType);

  public abstract int addOrUpdate(DictType paramDictType, Loginer paramLoginer);

  public abstract int delete(String[] paramArrayOfString);

  public abstract Map<String, Object> importDictType(File paramFile);

  public abstract DictType queryDictTypeById(String paramString);
}