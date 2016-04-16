package com.fy.fyy.back.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class ContextUtil {

  private static ThreadLocal<Map<String, Object>> sessionAttrs = new ThreadLocal<>();

  private static ThreadLocal<Map<String, Object>> reqtAttrs = new ThreadLocal<>();

  private static ThreadLocal<Map<String, Object>> reqParams = new ThreadLocal<>();

  private static void init( ThreadLocal<Map<String, Object>> tl ) {
    if ( tl.get() == null ) {
      tl.set( new HashMap<String, Object>() );
    }
  }

  public static Map<String, Object> getSessionAttrs() {
    init( sessionAttrs );
    return sessionAttrs.get();
  }

  public static Object getSessionAttr( String key ) {
    init( sessionAttrs );
    return sessionAttrs.get().get( key );
  }

  public static Map<String, Object> getReqAttrs() {
    init( reqtAttrs );
    return reqtAttrs.get();
  }

  public static Object getReqAttr( String key ) {
    init( reqtAttrs );
    return reqtAttrs.get().get( key );
  }

  public static Object getReqParam( String key ) {
    init( reqParams );
    return reqParams.get().get( key );
  }

  public static Map<String, Object> getReqParams() {
    init( reqParams );
    return reqParams.get();
  }

  public static void setReqParams( HttpServletRequest req ) {
    init( reqParams );
    Enumeration<String> requestParameterNames = req.getParameterNames();
    while ( requestParameterNames.hasMoreElements() ) {
      String key = requestParameterNames.nextElement();
      reqParams.get().put( key, req.getParameter( key ) );
    }
  }

  public static void copyToAttrs( HttpServletRequest req ) {
    Enumeration<String> sessionAttrNames = req.getSession().getAttributeNames();
    while ( sessionAttrNames.hasMoreElements() ) {
      String sessionAttrName = sessionAttrNames.nextElement();
      Object sessionAttrValue = req.getSession().getAttribute( sessionAttrName );
      ContextUtil.getSessionAttrs().put( sessionAttrName, sessionAttrValue );
    }

    Enumeration<String> requestAttrNames = req.getAttributeNames();
    while ( requestAttrNames.hasMoreElements() ) {
      String requestAttrName = requestAttrNames.nextElement();
      Object requestAttrValue = req.getSession().getAttribute( requestAttrName );
      ContextUtil.getSessionAttrs().put( requestAttrName, requestAttrValue );
    }
    ContextUtil.setReqParams( req );
  }

  public static void copyFromAttrs( HttpServletRequest req ) {
    Map<String, Object> sessionMap = ContextUtil.getSessionAttrs();
    if ( !sessionMap.isEmpty() ) {
      for ( Map.Entry<String, Object> entry : sessionMap.entrySet() ) {
        req.getSession().setAttribute( entry.getKey(), entry.getValue() );
      }
    }

    Map<String, Object> requestMap = ContextUtil.getReqAttrs();
    if ( !requestMap.isEmpty() ) {
      for ( Map.Entry<String, Object> entry : requestMap.entrySet() ) {
        req.setAttribute( entry.getKey(), entry.getValue() );
      }
    }
  }

  public static void destroyAttrs() {
    ContextUtil.getReqAttrs().clear();
    ContextUtil.getSessionAttrs().clear();
    ContextUtil.getReqParams().clear();
  }
}
