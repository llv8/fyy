package com.fy.fyy.back.bean;

import java.util.List;


public class BaseBean {

  private Integer id;
  private List<Object> queryParams;

  public Integer getId() {
    return id;
  }

  public void setId( Integer id ) {
    this.id = id;
  }

  public List<Object> getQueryParams() {
    return queryParams;
  }

}
