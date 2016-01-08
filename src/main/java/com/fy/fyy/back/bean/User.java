package com.fy.fyy.back.bean;

import java.sql.Date;




public class User extends BaseBean {

  private String userName;
  private String loginName;
  private String password;
  private Date createDate;
  private Date updateDate;
  private Status status;
  private Integer statusId;
  private String email;
  private String phone;
  private Department department;
  private Integer departmentId;

  public static User getInstance( Integer id ) {
    User usr = new User();
    usr.setId( id );
    return usr;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName( String userName ) {
    this.userName = userName;
  }

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

  public Status getStatus() {
    return status;
  }

  public void setStatus( Status status ) {
    this.status = status;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail( String email ) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone( String phone ) {
    this.phone = phone;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment( Department department ) {
    this.department = department;
  }

  public Integer getStatusId() {
    return statusId;
  }

  public void setStatusId( Integer statusId ) {
    this.statusId = statusId;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId( Integer departmentId ) {
    this.departmentId = departmentId;
  }

  public static class Status extends CodeBean {

    public static final Status active = new Status( 1, "可用" );
    public static final Status deactive = new Status( 2, "不可用" );

    private Status( int id, String name ) {
      super( id, name );
    }
  }

  public static class Department extends CodeBean {

    public static final Department product = new Department( 1, "生产部门" );
    public static final Department market = new Department( 2, "销售部门" );
    public static final Department finance = new Department( 3, "财务部门" );
    public static final Department inventory = new Department( 4, "库管部门" );

    private Department( int id, String name ) {
      super( id, name );
    }
  }

}
