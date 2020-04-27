package com.hcis.ipanther.common.privilege.entity;

import com.hcis.ipanther.core.entity.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

public class Role extends BaseEntity
{
  private static final long serialVersionUID = 4996633340880648731L;

  @NotEmpty
  private String id;
  private String code;

  @NotEmpty
  private String name;
  private String description;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}