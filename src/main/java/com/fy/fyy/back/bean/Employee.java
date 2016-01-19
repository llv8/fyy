package com.fy.fyy.back.bean;

import java.sql.Date;


public class Employee extends BaseBean {

  private String userName;
  private Date createDate;
  private Date updateDate;
  private Status status;
  private Integer statusId;
  private String email;
  private String phone;
  private Position position;
  private Integer positionId;
  private Department department;
  private Integer departmentId;

  public static Employee getInstance( Integer id ) {
    Employee usr = new Employee();
    usr.setId( id );
    return usr;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName( String userName ) {
    this.userName = userName;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition( Position position ) {
    this.position = position;
  }

  public Integer getPositionId() {
    return positionId;
  }

  public void setPositionId( Integer positionId ) {
    this.positionId = positionId;
    this.setPosition( CodeBean.get( Position.class, positionId ) );
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
    this.setStatus( CodeBean.get( Status.class, statusId ) );
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId( Integer departmentId ) {
    this.departmentId = departmentId;
    this.setDepartment( CodeBean.get( Department.class, departmentId ) );
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

  public static class Position extends CodeBean {

    public static final Position manager = new Position( 1, "部门经理" );
    public static final Position staff = new Position( 2, "职员" );
    public static final Position admin = new Position( 3, "管理员" );

    private Position( int id, String name ) {
      super( id, name );
    }
  }

}
