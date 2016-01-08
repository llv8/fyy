package com.fy.fyy.back.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.action.To;
import com.fy.fyy.back.action.To.TYPE;


public class ServletUtil extends HttpServlet {

  private static Pair<String, String> parseURI( String uri ) {
    if ( StringUtils.isNotEmpty( uri ) ) {
      uri = StringUtils.trim( "/" );
      if ( !uri.contains( "/" ) && StringUtils.countMatches( uri, "." ) == 1 ) {
        String[] class2Method = uri.split( "." );
        return new MutablePair<String, String>( class2Method[0], class2Method[1] );
      }
      else {
        //go index page
      }
    }
    else {
      //go index page
    }
    return null;
  }

  public static Pair<String, TYPE> exec( HttpServletRequest req ) {
    Pair<String, String> class2Method = parseURI( req.getRequestURI() );
    try {
      Object action = Class.forName( class2Method.getLeft() + "Action" ).newInstance();
      BeanUtils.populate( action, req.getParameterMap() );
      Object url = action.getClass().getMethod( class2Method.getRight() ).invoke( action );
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
