package com.hcis.ipanther.common.dict.vo;

import com.hcis.ipanther.core.entity.BaseEntity;
import java.math.BigDecimal;

public class DictType extends BaseEntity
{
  private static final long serialVersionUID = 1206236508826730676L;
  private String dictTypeCode;
  private String dictTypeName;
  private BigDecimal rank;
  private String parentCode;

  public String getDictTypeCode()
  {
    return this.dictTypeCode;
  }

  public void setDictTypeCode(String dictTypeCode) {
    this.dictTypeCode = (dictTypeCode == null ? null : dictTypeCode.trim());
  }

  public String getDictTypeName() {
    return this.dictTypeName;
  }

  public void setDictTypeName(String dictTypeName) {
    this.dictTypeName = (dictTypeName == null ? null : dictTypeName.trim());
  }

  public BigDecimal getRank() {
    return this.rank;
  }

  public void setRank(BigDecimal rank) {
    this.rank = rank;
  }

  public String getParentCode() {
    return this.parentCode;
  }

  public void setParentCode(String parentCode) {
    this.parentCode = (parentCode == null ? null : parentCode.trim());
  }
}