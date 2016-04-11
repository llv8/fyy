package com.fy.fyy.back.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fy.fyy.back.common.Constraint;

@WebFilter(filterName = "loginFilter", urlPatterns = { "*.Action", "*.jsp" })
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// if the user unlogin and the url isn't "/loginUI/Customer.Action" and
		// "/login/Customer.Action" , jump to login page.
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		httpReq.setCharacterEncoding("UTF-8");
		String url = httpReq.getRequestURL().toString();
		String uri = ServletUtil.getURI(httpReq, url);
		if (httpReq.getSession().getAttribute(Constraint.LOGIN_USER) == null && !Constraint.LOGIN_UI.equals(uri)
				&& !Constraint.LOGIN.equals(uri)) {
			String str = "<script type='text/javascript'>top.location.href='/fyy" + Constraint.LOGIN_UI + "'</script>";
			httpResp.setHeader("Content-type", "text/html;charset=UTF-8");
			OutputStream stream = httpResp.getOutputStream();
			stream.write(str.getBytes("UTF-8"));
			stream.close();
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
