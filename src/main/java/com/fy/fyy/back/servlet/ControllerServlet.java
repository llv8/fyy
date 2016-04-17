package com.fy.fyy.back.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fy.fyy.back.common.Log;


public class ControllerServlet extends HttpServlet {

  private static Log logger = Log.getInstance( ControllerServlet.class );
  private static final long serialVersionUID = -423807766712107605L;

  @Override
  protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    this.doPost( req, resp );
  }

  @Override
  protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    // invoke action
    ServletUtil.exec( req, resp, ServletUtil.getURI( req, req.getRequestURL().toString() ) );
  }
}
