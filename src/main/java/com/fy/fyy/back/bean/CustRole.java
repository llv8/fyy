package com.fy.fyy.back.bean;

import java.sql.Date;


public class CustRole extends BaseBean {

  private Date createDate;
  private Date updateDate;
  private String name;

  public static CustRole getInstance( Integer id ) {
    CustRole role = new CustRole();
    role.setId( id );
    return role;
  }
  
}
