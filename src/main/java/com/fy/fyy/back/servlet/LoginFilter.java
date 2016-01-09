package com.fy.fyy.back.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "loginFilter", urlPatterns = { "*.Action", "*.jsp" })
public class LoginFilter implements Filter {

  @Override
  public void destroy() {
    // TODO Auto-generated method stub

  }

  @Override
  public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException, ServletException {
    HttpServletRequest httpReq = (HttpServletRequest)req;
    HttpServletResponse httpResp = (HttpServletResponse)resp;

    if ( httpReq.getSession().getAttribute( ServletUtil.LOGIN_FLAG ) == null && !ServletUtil.LOGIN_UI.equals( ServletUtil.getURI( httpReq ) ) ) {
      ServletUtil.go( ServletUtil.LOGIN_UI, true, httpReq, httpResp );
    }
    else {
      chain.doFilter( req, resp );
    }

  }

  @Override
  public void init( FilterConfig arg0 ) throws ServletException {
    // TODO Auto-generated method stub

  }

}
