package com.fy.fyy.back.action;

import java.util.HashMap;
import java.util.Map;

import com.fy.fyy.back.bean.BaseBean;
import com.fy.fyy.back.servlet.ServletUtil;


public class BaseAction<T extends BaseBean> {

  protected T bean;

  private Map<String, Object> sessionAttrs = new HashMap<String, Object>();
  private Map<String, Object> requestAttrs = new HashMap<String, Object>();

  public Map<String, Object> getSessionAttrs() {
    return sessionAttrs;
  }

  public Map<String, Object> getRequestAttrs() {
    return requestAttrs;
  }

  /**
   * default action
   * @return
   */
  public String exec() {
    return ServletUtil.INDEX_UI;
  }

  public T getBean() {
    return bean;
  }

  public void setBean( T bean ) {
    this.bean = bean;
  }

}
