package com.fy.fyy.back.bean;

import java.sql.Date;


public class CustPermission extends BaseBean {

  private Date createDate;
  private Date updateDate;
  private String name;

  public static CustPermission getInstance( Integer id ) {
    CustPermission role = new CustPermission();
    role.setId( id );
    return role;
  }
  
}
