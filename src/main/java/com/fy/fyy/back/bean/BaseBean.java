package com.fy.fyy.back.bean;

import java.util.ArrayList;
import java.util.List;


public class BaseBean {

  private Integer id;
  private List<Object> queryParams = new ArrayList<>();
  private PageInfo pageInfo = new PageInfo();

  public static <T extends BaseBean> T getInstance( Class<T> clazz, Integer id ) {
    T instance = null;
    try {
      instance = clazz.newInstance();
    }
    catch ( InstantiationException | IllegalAccessException e ) {
      
    }
    instance.setId( id );
    return instance;
  }

  public void BeseBean( Integer id ) {
    this.id = id;
  }

  public void BeseBean() {}

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
