package com.hcis.ipanther.common.dict.vo;

import com.haoyu.ipanther.common.excel.annotations.ExcelEntity;
import com.haoyu.ipanther.common.excel.annotations.ImportField;
import com.haoyu.ipanther.common.excel.model.ImportModel;

@ExcelEntity
public class ExcelDictType extends ImportModel
{

  @ImportField(colName="字典编码", validate={com.haoyu.ipanther.common.excel.DataType.REQURIED})
  private String dictTypeCode;

  @ImportField(colName="字典名称", validate={com.haoyu.ipanther.common.excel.DataType.REQURIED})
  private String dictTypeName;

  @ImportField(colName="父字典编码")
  private String parentCode;

  public String getDictTypeCode()
  {
    return this.dictTypeCode;
  }

  public void setDictTypeCode(String dictTypeCode) {
    this.dictTypeCode = dictTypeCode;
  }

  public String getDictTypeName() {
    return this.dictTypeName;
  }

  public void setDictTypeName(String dictTypeName)
  {
    this.dictTypeName = dictTypeName;
  }

  public String getParentCode()
  {
    return this.parentCode;
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }
  public String toString() {
    return "字典类型：" + this.dictTypeCode + ",字典名字:" + this.dictTypeName + ",父字典编码" + this.parentCode;
  }
}