package com.fy.fyy.back.bean;

import java.util.ArrayList;
import java.util.List;


public class BaseBean {

  private Integer id;
  private List<Object> queryParams = new ArrayList<>();
  private PageInfo pageInfo = new PageInfo();

  public Integer getId() {
    return id;
  }

  public void setId( Integer id ) {
    this.id = id;
  }

  public List<Object> getQueryParams() {
    return queryParams;
  }

  public PageInfo getPageInfo() {
    return pageInfo;
  }

}
