package com.fy.fyy.back.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fy.fyy.back.action.To.TYPE;


public class LoginFilter implements Filter {

  private static final long serialVersionUID = -423807766712107605L;

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException, ServletException {
    HttpServletRequest httpReq = (HttpServletRequest) req;
    HttpServletResponse httpResp = (HttpServletResponse)resp;
    String uri = ServletUtil.getURI( httpReq.getRequestURI() );
    if(StringUtils.isEmpty( uri )){
      if(httpReq.getSession().getAttribute( "loginFlag" )==null){
        ServletUtil.go( "User.loginPage", TYPE.redirect, httpReq, httpResp );
     }else{
       ServletUtil.go( "User.indexPage", TYPE.redirect, httpReq, httpResp );
     }
    }
    chain.doFilter(req, resp);
    
  }

  @Override
  public void init( FilterConfig arg0 ) throws ServletException {
    // TODO Auto-generated method stub
    
  }

}
