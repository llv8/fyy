package com.fy.fyy.back.common;

public class Constraint {

  public static final String ADMIN = "admin";
  public static final String PKG_ACTION = "com.fy.fyy.back.action.imp.";
  public static final String LOGIN_USER = "loginCustomer";
  public static final String LOGIN_PERM = "loginPermission";
  public static final String LOGIN_PERM_ROOT = "loginPermissionRoot";
  public static final String LOGIN_UI = "/loginUI/Customer.Action";
  public static final String LOGIN = "/login/Customer.Action";
  public static final String INDEX_UI = "/indexUI/Customer.Action";
  public static final String EMAIL_REG = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
  public static final String PHONE_REG = "1[3|5|7|8|][0-9]{9}";
  public static final Integer MAX_LEN = 20;
  public static final String STYLE_ADD = "add";
  public static final String STYLE_UPDATE = "update";
  public static final String STYLE_DEL = "del";
}
