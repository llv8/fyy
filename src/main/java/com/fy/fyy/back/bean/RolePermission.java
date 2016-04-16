package com.fy.fyy.back.bean;

public class RolePermission extends BaseBean {

  private Integer modelId;
  private Integer roleId;
  private Boolean isPerm;

  public Integer getModelId() {
    return modelId;
  }

  public void setModelId( Integer modelId ) {
    this.modelId = modelId;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId( Integer roleId ) {
    this.roleId = roleId;
  }

  public Boolean getIsPerm() {
    return isPerm;
  }

  public void setIsPerm( Boolean isPerm ) {
    this.isPerm = isPerm;
  }

}
