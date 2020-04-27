package com.hcis.ipanther.common.dict.vo;

import com.hcis.ipanther.core.entity.BaseEntity;
import java.math.BigDecimal;

public class DictEntry extends BaseEntity
  implements Comparable<DictEntry>
{
  private static final long serialVersionUID = 5260302474565270270L;
  private String id;
  private String dictTypeCode;
  private String dictValue;
  private String dictName;
  private String parentValue;
  private String parentName;
  private BigDecimal rank;
  private BigDecimal sortNo;
  private String parentCode;
  private String isHidden;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIsHidden() {
    return this.isHidden;
  }

  public void setIsHidden(String isHidden) {
    this.isHidden = isHidden;
  }

  public String getParentCode() {
    return this.parentCode;
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }

  public String getDictTypeCode() {
    return this.dictTypeCode;
  }

  public void setDictTypeCode(String dictTypeCode) {
    this.dictTypeCode = (dictTypeCode == null ? null : dictTypeCode.trim());
  }

  public String getDictValue() {
    return this.dictValue;
  }

  public void setDictValue(String dictValue) {
    this.dictValue = (dictValue == null ? null : dictValue.trim());
  }

  public String getDictName() {
    return this.dictName;
  }

  public void setDictName(String dictName) {
    this.dictName = (dictName == null ? null : dictName.trim());
  }

  public String getParentValue() {
    return this.parentValue;
  }

  public void setParentValue(String parentValue) {
    this.parentValue = (parentValue == null ? null : parentValue.trim());
  }

  public String getParentName() {
    return this.parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = (parentName == null ? null : parentName.trim());
  }

  public BigDecimal getRank() {
    return this.rank;
  }

  public void setRank(BigDecimal rank) {
    this.rank = rank;
  }

  public BigDecimal getSortNo() {
    return this.sortNo;
  }

  public void setSortNo(BigDecimal sortNo) {
    this.sortNo = sortNo;
  }

  public int compareTo(DictEntry dictEntry) {
    return Integer.parseInt(getDictValue()) - Integer.parseInt(dictEntry.getDictValue());
  }
}