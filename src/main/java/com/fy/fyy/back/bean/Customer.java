package com.fy.fyy.back.bean;

import java.sql.Date;


public class Customer extends BaseBean {

  private String loginName;
  private String password;
  private Date createDate;
  private Date updateDate;
  private Employee employee;
  private Integer employeeId;

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName( String loginName ) {
    this.loginName = loginName;
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
    CachedBean.get().getValue( BaseBean.getInstance( Employee.class, employeeId ) );
  }

}
