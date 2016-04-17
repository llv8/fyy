package com.fy.fyy.back.common;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StrUtil {

  private static Logger logger = LoggerFactory.getLogger( StrUtil.class );
  public static final String ESCAPE_DOT = "\\.";
  public static final String DOT = ".";
  public static final String EMPTY = "";
  public static final String SLASH = "/";
  public static final Character C_SLASH = '/';

  public static String trim( String str, Character c ) {
    if ( StringUtils.isEmpty( str ) ) return null;
    return str.replaceAll( c + "*$", StrUtil.EMPTY ).replaceAll( "^" + c + "*", StrUtil.EMPTY );
  }

  public static <L, T> Set<Integer> getIds( List<L> beanList, Class<T> clazz ) {
    Set<Integer> result = new HashSet<Integer>();
    for ( int i = 0; i < beanList.size(); i++ ) {
      L bean = beanList.get( i );
      Integer id = (Integer)StrUtil.invokeGetAttrBeanId( bean, clazz );
      if ( id != null && id > 0 ) {
        result.add( id );
      }
    }

    return result;
  }

  public static String getIds( Set<Integer> ids ) {
    return StringUtils.join( ids, ',' );
  }

  public static <L, T> Object invokeGetAttrBeanId( L bean, Class<T> clazz ) {
    try {
      return bean.getClass().getMethod( "get" + clazz.getSimpleName() + "Id" ).invoke( bean );
    }
    catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e ) {
      logger.error( e.getMessage() );
      return null;
    }
  }

  public static <L, T> void invokeSetAttrBean( L bean, T attrBean, Class<T> clazz ) {
    try {
      bean.getClass().getMethod( "set" + clazz.getSimpleName(), clazz ).invoke( bean, attrBean );
    }
    catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e ) {
      logger.error( e.getMessage() );
    }
  }

  public static <T> T getInstance( Class<T> clazz ) {
    try {
      return clazz.newInstance();
    }
    catch ( InstantiationException | IllegalAccessException e ) {
      logger.error( e.getMessage() );
      return null;
    }
  }

}
