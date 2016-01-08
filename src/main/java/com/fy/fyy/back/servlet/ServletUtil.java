package com.fy.fyy.back.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.action.To;
import com.fy.fyy.back.action.To.TYPE;


public class ServletUtil extends HttpServlet {

  public static String getURI(String uri){
    if(StringUtils.isEmpty( uri ))return null;
    uri = StringUtils.trim( uri ).replaceAll( "/*$", "" ).replaceAll( "^/*", "" );
    if ( !(!uri.contains( "/" ) && StringUtils.countMatches( uri, "." ) == 1) )return null;
    return uri;
  }
  
  private static Pair<String, String> parseURI( String uri ) {
        uri = getURI(uri);
        if(StringUtils.isEmpty( uri ))return null;
        String[] class2Method = uri.split( "." );
        return new MutablePair<String, String>( class2Method[0], class2Method[1] );
  }
  
  public static void go(String url,TYPE type,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    if ( type == TYPE.forwrad ) {
      req.getRequestDispatcher( url ).forward( req, resp );
    }
    else if ( type == TYPE.redirect ) {
      resp.sendRedirect( url );
    }
  }

  public static Pair<String, TYPE> exec( HttpServletRequest req ) {
    Pair<String, String> class2Method = parseURI( req.getRequestURI() );
    
    try {
      Object action = Class.forName( class2Method.getLeft() + "Action" ).newInstance();
      BeanUtils.populate( action, req.getParameterMap() );
      Object url = action.getClass().getMethod( class2Method.getRight() ).invoke( action );
      String beforeUri = getURI( req.getRequestURI() );
      if("User.login".equals(beforeUri )&&!(beforeUri+"Page").equals( url )){
        req.getSession().setAttribute( "loginFlag", true );
      }
      
      TYPE type = action.getClass().getAnnotation( To.class ).value();
      return new MutablePair<String, TYPE>( url.toString(), type );
    }
    catch ( Exception e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

}
