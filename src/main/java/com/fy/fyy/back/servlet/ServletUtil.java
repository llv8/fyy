package com.fy.fyy.back.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.action.RedirectAnnotation;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.exception.NullActionException;


public class ServletUtil {

  private static Log logger = Log.getInstance( ServletUtil.class );

  public static Pair<String, String> getCalledActionAndMethod( HttpServletRequest req, String uri ) throws NullActionException {
    if ( StringUtils.isEmpty( uri ) ) throw new NullActionException( "the uri is null!" );
    uri = StrUtil.trim( uri.trim(), StrUtil.C_SLASH );
    // if the uri is loginUI/Customer.Action, then parse the uri to
    // class="CustomerAction",method="loginUI"
    String[] uris = uri.split( StrUtil.SLASH );
    if ( StringUtils.countMatches( uris[uris.length - 1], StrUtil.DOT ) == 1 ) {
      req.setAttribute( "CUR_ACTION", uris[uris.length - 1] );
      String clazz = uris[uris.length - 1].replace( StrUtil.DOT, StrUtil.EMPTY );
      String method = ( uris.length == 2 ) ? uris[0] : uris.length == 1 ? "exec" : null;
      if ( method == null ) throw new NullActionException( uri + " uri is invalid" );
      return new MutablePair<String, String>( clazz, method );
    }
    else {
      throw new NullActionException( uri + " uri is invalid" );
    }
  }

  public static void go( String uri, Boolean isRedirect, HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    if ( isRedirect ) {
      redirect( uri, req, resp );
    }
    else {
      forward( uri, req, resp );
    }
  }

  public static void redirect( String uri, HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    resp.sendRedirect( req.getContextPath() + uri );
  }

  public static void forward( String uri, HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    req.getRequestDispatcher( uri ).forward( req, resp );
  }

  public static void exec( HttpServletRequest req, HttpServletResponse resp, String uri ) {
    Pair<String, String> class2Method = null;
    try {
      try {
        class2Method = getCalledActionAndMethod( req, uri );
      }
      catch ( NullActionException e ) {
        // if the url is not exists,then go to 404.jsp
        logger.error( "action is not exists", e );
        throw new RuntimeException();
      }

      String actionStr = class2Method.getLeft();
      String methodStr = class2Method.getRight();
      BaseAction action = null;
      try {
        action = (BaseAction)Class.forName( Constraint.PKG_ACTION + actionStr ).newInstance();
      }
      catch ( InstantiationException | IllegalAccessException | ClassNotFoundException e ) {
        // if the request action throw exception, then go to the refer
        // action.
        logger.error( "Class.forName error", e );
        throw new RuntimeException();
      }

      try {
        BeanUtils.populate( action, req.getParameterMap() );
      }
      catch ( IllegalAccessException | InvocationTargetException e ) {
        logger.error( "BeanUtils.populate error", e );
        throw new RuntimeException();
      }

      ContextUtil.copyToAttrs( req );

      // main section
      Method actionMothed = getMethod( action, methodStr );
      Method validMethod = getMethod( action, "valid" + StringUtils.capitalize( methodStr ) );
      String fowardUri;
      try {
        fowardUri = execAction( action, actionMothed, validMethod );
      }
      catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) {
        logger.error( "action method invoke error", e );
        throw new RuntimeException();
      }

      ContextUtil.copyFromAttrs( req );

      go( fowardUri, actionMothed.getAnnotation( RedirectAnnotation.class ) != null, req, resp );

    }
    catch ( ServletException | IOException e1 ) {
      logger.error( "servlet rediert or forward error" );
    }
  }

  private static Method getMethod( BaseAction action, String method ) {
    try {
      return action.getClass().getMethod( method );
    }
    catch ( NoSuchMethodException e ) {}
    catch ( SecurityException e ) {}
    return null;
  }

  private static String execAction( BaseAction action, Method actionMethod, Method validMethod )
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    String forwardUri = null;
    Object validObj = null;
    if ( validMethod != null ) {
      validObj = validMethod.invoke( action );
    }

    if ( validObj == null ) {
      forwardUri = actionMethod.invoke( action ).toString();
    }
    else {
      forwardUri = validObj.toString();
    }
    return forwardUri;
  }

  public static String getURI( HttpServletRequest req, String url ) {
    return url.replace( getBasePath( req ), "" );
  }

  public static String getHost( HttpServletRequest req ) {
    return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
  }

  public static String getBasePath( HttpServletRequest req ) {
    return getHost( req ) + req.getContextPath();
  }

  public static void setErrorAttribute( HttpServletRequest req ) {
    List<String> list = new ArrayList<>();
    list.add( "系统错误,请与管理员联系" );
    req.setAttribute( "error", list );
  }

}
