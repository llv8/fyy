package com.fy.fyy.back.bean;

import java.sql.Date;


public class Permission extends BaseBean {

  private Date createDate;
  private Date updateDate;
  private String name;

  public static Permission getInstance( Integer id ) {
    Permission perm = new Permission();
    perm.setId( id );
    return perm;
  }
  
}
