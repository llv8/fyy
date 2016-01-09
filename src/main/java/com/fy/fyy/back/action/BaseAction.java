package com.fy.fyy.back.action;

import java.util.HashMap;
import java.util.Map;

import com.fy.fyy.back.servlet.ServletUtil;


public class BaseAction {

  private Map<String, Object> sessionAttrs = new HashMap<String, Object>();

  public Map<String, Object> getSessionAttrs() {
    return sessionAttrs;
  }

  /**
   * default action
   * @return
   */
  public String exec() {
    return ServletUtil.INDEX_UI;
  }

}
