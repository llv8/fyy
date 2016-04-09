package com.fy.fyy.back.bean;

import java.sql.Date;


public class Role extends BaseBean {

  private Date createDate;
  private Date updateDate;
  private String name;

  public static Role getInstance( Integer id ) {
    Role role = new Role();
    role.setId( id );
    return role;
  }
  
}
