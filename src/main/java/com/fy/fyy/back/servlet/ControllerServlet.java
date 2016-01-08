package com.fy.fyy.back.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.action.To.TYPE;


public class ControllerServlet extends HttpServlet {

  private static final long serialVersionUID = -423807766712107605L;

  @Override
  protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    this.doPost( req, resp );
  }

  @Override
  protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    //invoke action
    Pair<String, TYPE> pair = ServletUtil.exec( req );
    //forward or redirect
    ServletUtil.go(pair.getLeft(),pair.getRight(),req,resp);

  }

  
  public static void go404( HttpServletResponse resp ) {
    try {
      resp.sendRedirect( "404.jsp" );
    }
    catch ( IOException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void go500( HttpServletResponse resp ) {
    try {
      resp.sendRedirect( "500.jsp" );
    }
    catch ( IOException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
