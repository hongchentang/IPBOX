package com.hcis.ipanther.common.dict.service.impl;

import com.hcis.ipanther.common.dict.dao.DictEntryDao;
import com.hcis.ipanther.common.dict.service.IDictEntryService;
import com.hcis.ipanther.common.dict.service.IDictTypeService;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.common.dict.vo.DictType;
import com.hcis.ipanther.common.dict.vo.ExcelDictEntry;
import com.hcis.ipanther.core.cache.local.CacheReloadInvoker;
import com.hcis.ipanther.core.security.entity.Loginer;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.haoyu.ipanther.common.excel.ExcelImport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("dictEntryService")
public class DictEntryServiceImpl extends CacheReloadInvoker
  implements IDictEntryService
{

  @Resource
  private DictEntryDao dictEntryDao;

  @Resource
  private IDictTypeService dictTypeService;

  public List<DictEntry> listDictEntry(SearchParam searchParam)
  {
    List<DictEntry> entryList = this.dictEntryDao.selectBySearchParam(searchParam);
    if ((entryList != null) && (!entryList.isEmpty())) {
      for (DictEntry dictEntry : entryList) {
        dictEntry.setParentName(DictionaryUtils.getAllParentName(dictEntry.getDictTypeCode(), dictEntry.getDictValue()));
      }
    }
    return entryList;
  }

  public List<Map<String, Object>> selectDictEntryOptions() {
    return this.dictEntryDao.selectDictEntryOptions();
  }

  public int delete(DictEntry t) {
    int count = this.dictEntryDao.deleteByPrimaryKey(t.getId());
    DictionaryUtils.init(t.getDictTypeCode());
    return count;
  }

  public Map<String, Object> getByPk(DictEntry t)
  {
    return (Map)this.dictEntryDao.selectByPrimaryKey(t);
  }

  public boolean checkSameValue(DictEntry dictEntry) {
    int count = this.dictEntryDao.checkSameValue(dictEntry);
    if (count > 0) {
      return false;
    }
    return true;
  }

  public int addOrUpdate(DictEntry t, Loginer loginer) {
    int count = 0;
    if (t.getVersion() == 0) {
      if (loginer != null) {
        t.setCreator(loginer.getId());
      }
      t.setDefaultValue();
      t.setId(Identities.uuid2());
      count = this.dictEntryDao.insertSelective(t);
    } else {
      if (loginer != null) {
        t.setUpdatedby(loginer.getId());
      }
      t.setUpdateTime(new Date());
      count = this.dictEntryDao.updateByPrimaryKeySelective(t);
    }
    DictionaryUtils.init(t.getDictTypeCode());
    return count;
  }

  public int delete(String[] idArray, String[] dictTypeCodes) {
    int count = this.dictEntryDao.deleteByIdArray(idArray);
    DictionaryUtils.init(dictTypeCodes);
    return count;
  }

  public Map<String, Object> importDictEntry(File file) {
    Map resultMap = new HashMap();
    List successList = new ArrayList();
    List failList = new ArrayList();
    ExcelImport ei = new ExcelImport(ExcelDictEntry.class);
    int headRow = 0;
    int dataRow = 1;
    Collection<ExcelDictEntry> list = ei.importExcel(file, headRow, dataRow);
    for (String str : (List<String>)ei.getErrorMsg()) {
      failList.add(str);
    }
    for (ExcelDictEntry dtm : list) {
      dataRow = dtm.getLineNumber();
      DictEntry dt = new DictEntry();
      dt.setDictTypeCode(dtm.getDictTypeCode());
      dt.setDictName(dtm.getDictName());
      dt.setDictValue(dtm.getDictValue());
      dt.setParentValue(dtm.getParentValue());
      dt.setSortNo(dtm.getSortNo());
      dt.setDefaultValue();
      dt.setId(Identities.uuid2());
      try {
        DictType d = this.dictTypeService.queryDictTypeById(dt.getDictTypeCode());
        if (d != null) {
          List dictEntryList = selectDictLike(dt);
          if (dictEntryList.size() > 0) {
            failList.add("第" + dataRow + "行字典已存在");
          } else if (StringUtils.isNotEmpty(dt.getParentValue())) {
            DictEntry de = new DictEntry();
            de.setDictTypeCode(dt.getDictTypeCode());
            de.setDictValue(dt.getParentValue());
            dictEntryList = selectDictLike(de);
            if (dictEntryList.size() == 0) {
              failList.add("第" + dataRow + "行字典的所有上级字典不存在(excel中，上级字典要排在下级的前面)");
            } else {
              this.dictEntryDao.insertSelective(dt);
              successList.add(dt);
            }
          } else {
            this.dictEntryDao.insertSelective(dt);
            successList.add(dt);
          }
        } else {
          failList.add("第" + dataRow + "行字典类型不存在");
        }
      } catch (Exception e) {
        failList.add("第" + dataRow + "插入数据库时异常（不包含主键唯一性错误）");
      }
    }

    resultMap.put("successList", successList);
    resultMap.put("failList", failList);
    return resultMap;
  }

  public List<DictEntry> selectDictLike(DictEntry t) {
    return this.dictEntryDao.selectDictLike(t);
  }

    @Override
    public List<DictEntry> getByTypeCode(String typeCode) {
        return dictEntryDao.getByTypeCode(typeCode);
    }
}