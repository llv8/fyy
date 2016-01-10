package com.fy.fyy.back.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.action.Redirect;
import com.fy.fyy.back.common.StrUtil;


public class ServletUtil extends HttpServlet {

  private static final String PKG_ACTION = "com.fy.fyy.back.action.";
  public static final String LOGIN_USER = "loginUser";
  public static final String LOGIN_UI = "/loginUI/User.Action";
  public static final String LOGIN = "/login/User.Action";
  public static final String INDEX_UI = "/indexUI/User.Action";

  public static String getURI( HttpServletRequest req ) {
    return req.getRequestURI().substring( req.getContextPath().length(), req.getRequestURI().length() );
  }

  public static Pair<String, String> parseURI( HttpServletRequest req ) {
    String uri = getURI( req );
    if ( StringUtils.isEmpty( uri ) ) return null;
    uri = StrUtil.trim( uri.trim(), StrUtil.C_SLASH );
    String[] uris = uri.split( StrUtil.SLASH );
    if ( StringUtils.countMatches( uris[uris.length - 1], StrUtil.DOT ) == 1 ) {
      String clazz = uris[uris.length - 1].replace( StrUtil.DOT, StrUtil.EMPTY );
      String method = ( uris.length == 2 ) ? uris[0] : uris.length == 1 ? "exec" : null;
      if ( method == null ) return null;
      return new MutablePair<String, String>( clazz, method );
    }
    else {
      return null;
    }
  }

  public static void go( String uri, Boolean isRedirect, HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    if ( isRedirect ) {
      resp.sendRedirect( req.getContextPath() + uri );
    }
    else {
      req.getRequestDispatcher( uri ).forward( req, resp );
    }
  }

  private static void copyAttrs( HttpServletRequest req, BaseAction action ) {
    Enumeration<String> sessionAttrNames = req.getSession().getAttributeNames();
    while ( sessionAttrNames.hasMoreElements() ) {
      String sessionAttrName = sessionAttrNames.nextElement();
      Object sessionAttrValue = req.getSession().getAttribute( sessionAttrName );
      action.getSessionAttrs().put( sessionAttrName, sessionAttrValue );
    }

    Enumeration<String> requestAttrNames = req.getAttributeNames();
    while ( requestAttrNames.hasMoreElements() ) {
      String requestAttrName = requestAttrNames.nextElement();
      Object requestAttrValue = req.getSession().getAttribute( requestAttrName );
      action.getSessionAttrs().put( requestAttrName, requestAttrValue );
    }
  }

  private static void copyAttrs( BaseAction action, HttpServletRequest req ) {
    Map<String, Object> sessionMap = action.getSessionAttrs();
    if ( !sessionMap.isEmpty() ) {
      for ( Map.Entry<String, Object> entry : sessionMap.entrySet() ) {
        req.getSession().setAttribute( entry.getKey(), entry.getValue() );
      }
    }

    Map<String, Object> requestMap = action.getRequestAttrs();
    if ( !requestMap.isEmpty() ) {
      for ( Map.Entry<String, Object> entry : requestMap.entrySet() ) {
        req.setAttribute( entry.getKey(), entry.getValue() );
      }
    }
  }

  public static Pair<String, Boolean> exec( HttpServletRequest req ) {
    Pair<String, String> class2Method = parseURI( req );

    try {
      BaseAction action = (BaseAction)Class.forName( PKG_ACTION + class2Method.getLeft() ).newInstance();

      copyAttrs( req, action );

      BeanUtils.populate( action, req.getParameterMap() );
      Method method = action.getClass().getMethod( class2Method.getRight() );
      Object uriObj = method.invoke( action );

      copyAttrs( action, req );

      return new MutablePair<String, Boolean>( uriObj.toString(), method.getAnnotation( Redirect.class ) != null );
    }
    catch ( Exception e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

}
