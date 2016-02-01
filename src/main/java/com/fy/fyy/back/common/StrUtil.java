package com.fy.fyy.back.common;

import org.apache.commons.lang3.StringUtils;


public class StrUtil {

  public static final String ESCAPE_DOT = "\\.";
  public static final String DOT = ".";
  public static final String EMPTY = "";
  public static final String SLASH = "/";
  public static final Character C_SLASH = '/';

  public static String trim( String str, Character c ) {
    if ( StringUtils.isEmpty( str ) ) return null;
    return str.replaceAll( c + "*$", StrUtil.EMPTY ).replaceAll( "^" + c + "*", StrUtil.EMPTY );
  }

  public static boolean isId( String id ) {
    return id != null && Integer.valueOf( id.trim() ) > 0;
  }

  public static boolean isId( Integer id ) {
    return id != null && id > 0;
  }

}
