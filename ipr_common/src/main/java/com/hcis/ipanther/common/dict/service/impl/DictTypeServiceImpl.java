package com.hcis.ipanther.common.dict.service.impl;

import com.hcis.ipanther.common.dict.dao.DictTypeDao;
import com.hcis.ipanther.common.dict.service.IDictTypeService;
import com.hcis.ipanther.common.dict.vo.DictType;
import com.hcis.ipanther.common.dict.vo.ExcelDictType;
import com.hcis.ipanther.core.security.entity.Loginer;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.haoyu.ipanther.common.excel.ExcelImport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dictTypeService")
public class DictTypeServiceImpl
  implements IDictTypeService
{

  @Autowired
  private DictTypeDao dictTypeDao;

  public List<Map<String, Object>> list(SearchParam searchParam)
  {
    return this.dictTypeDao.selectBySearchParam(searchParam);
  }

  public List<Map<String, Object>> selectDictTypeOptions() {
    return this.dictTypeDao.selectDictTypeOptions();
  }

  public int delete(DictType t)
  {
    return this.dictTypeDao.deleteByPrimaryKey(t.getDictTypeCode());
  }

  public DictType getByPk(DictType t)
  {
    return (DictType)this.dictTypeDao.selectByPrimaryKey(t);
  }

  public boolean checkSameCode(DictType dictType)
  {
    dictType = (DictType)this.dictTypeDao.selectByPrimaryKey(dictType);
    if (dictType != null) {
      return false;
    }
    return true;
  }

  public int addOrUpdate(DictType t, Loginer loginer)
  {
    if (t.getVersion() == 0) {
      if (loginer != null) {
        t.setCreator(loginer.getId());
      }
      t.setDefaultValue();
      return this.dictTypeDao.insertSelective(t);
    }
    if (loginer != null) {
      t.setUpdatedby(loginer.getId());
    }
    t.setUpdateTime(new Date());
    return this.dictTypeDao.updateByPrimaryKeySelective(t);
  }

  public int delete(String[] idArray)
  {
    int count = this.dictTypeDao.deleteByIdArray(idArray);
    return count;
  }

  public Map<String, Object> importDictType(File file)
  {
    Map resultMap = new HashMap();
    List successList = new ArrayList();
    List failList = new ArrayList();
    ExcelImport ei = new ExcelImport(ExcelDictType.class);
    int headRow = 0;
    int dataRow = 1;
    Collection<ExcelDictType> list = ei.importExcel(file, headRow, dataRow);
    for (String str : (List<String>)ei.getErrorMsg()) {
      failList.add(str);
    }

    for (ExcelDictType dtm : list) {
      dataRow = dtm.getLineNumber();
      DictType dt = new DictType();
      dt.setDictTypeName(dtm.getDictTypeName());
      dt.setDictTypeCode(dtm.getDictTypeCode());
      dt.setParentCode(dtm.getParentCode());
      dt.setDefaultValue();
      try
      {
        if (StringUtils.isNotEmpty(dt.getParentCode())) {
          DictType d = (DictType)this.dictTypeDao.selectByPrimaryKey(dt.getParentCode());
          if (d == null) {
            failList.add("第" + dataRow + "行字典类型的所有上级字典不存在(excel中，上级字典要排在下级的前面)");
          } else {
            this.dictTypeDao.insertSelective(dt);
            successList.add(dt);
          }
        } else {
          this.dictTypeDao.insertSelective(dt);
          successList.add(dt);
        }
      } catch (Exception ex) {
        failList.add("第" + dataRow + "行字典类型已存在");
      }
    }
    resultMap.put("successList", successList);
    resultMap.put("failList", failList);
    return resultMap;
  }

  public DictType queryDictTypeById(String code) {
    return (DictType)this.dictTypeDao.selectByPrimaryKey(code);
  }
}