package com.hcis.ipanther.common.dict.vo;

import com.haoyu.ipanther.common.excel.annotations.ExcelEntity;
import com.haoyu.ipanther.common.excel.annotations.ImportField;
import com.haoyu.ipanther.common.excel.model.ImportModel;
import java.math.BigDecimal;

@ExcelEntity
public class ExcelDictEntry extends ImportModel
{

  @ImportField(colName="字典类型编码", validate={com.haoyu.ipanther.common.excel.DataType.REQURIED})
  private String dictTypeCode;

  @ImportField(colName="字典项值", validate={com.haoyu.ipanther.common.excel.DataType.REQURIED})
  private String dictValue;

  @ImportField(colName="字典项名称", validate={com.haoyu.ipanther.common.excel.DataType.REQURIED})
  private String dictName;

  @ImportField(colName="所有上级字典项")
  private String parentValue;

  @ImportField(colName="排序号", validate={com.haoyu.ipanther.common.excel.DataType.NUMERIC, com.haoyu.ipanther.common.excel.DataType.REQURIED})
  private BigDecimal sortNo;

  public String getDictTypeCode()
  {
    return this.dictTypeCode;
  }

  public void setDictTypeCode(String dictTypeCode) {
    this.dictTypeCode = dictTypeCode;
  }

  public String getDictValue() {
    return this.dictValue;
  }

  public void setDictValue(String dictValue) {
    this.dictValue = dictValue;
  }

  public String getDictName() {
    return this.dictName;
  }

  public void setDictName(String dictName) {
    this.dictName = dictName;
  }

  public String getParentValue() {
    return this.parentValue;
  }

  public void setParentValue(String parentValue) {
    this.parentValue = parentValue;
  }

  public BigDecimal getSortNo() {
    return this.sortNo;
  }

  public void setSortNo(BigDecimal sortNo) {
    this.sortNo = sortNo;
  }

  public String toString() {
    return "字典类型编码:" + this.dictTypeCode + ",字典项值:" + this.dictValue + ",字典项名称:" + this.dictName + ",所有上级字典项:" + this.parentValue + ",排序号:" + this.sortNo;
  }
}