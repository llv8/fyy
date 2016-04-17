package com.fy.fyy.back.bean;

import java.sql.Date;


public class Customer extends BaseBean {

  private String name;
  private String password;
  private Date createDate;
  private Date updateDate;
  private Employee employee;
  private Integer employeeId;
  private Role role;
  private Integer roleId;

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword( String password ) {
    this.password = password;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate( Date createDate ) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate( Date updateDate ) {
    this.updateDate = updateDate;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee( Employee employee ) {
    this.employee = employee;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId( Integer employeeId ) {
    this.employeeId = employeeId;
    this.employee = CachedBean.get().getValue( BaseBean.getInstance( Employee.class, employeeId ) );
  }

  public Role getRole() {
    return role;
  }

  public void setRole( Role role ) {
    this.role = role;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId( Integer roleId ) {
    this.roleId = roleId;
  }

}
