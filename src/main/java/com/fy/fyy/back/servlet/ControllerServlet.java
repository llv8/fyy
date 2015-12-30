package com.fy.fyy.back.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class ControllerServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//实例化action
		String [] class2Method = getClass2Method(req);
		if(class2Method==null){
			go404()
		}
		//收集参数
		collect(req);
		//调用action
		//跳转
		req.getParameter();
	}
	
	private String [] getClass2Method(HttpServletRequest req){
		String URI = req.getRequestURI();
		if(StringUtils.isNotEmpty(URI)){
			URI = StringUtils.trim("/");
			if(!URI.contains("/")&&StringUtils.countMatches(URI, ".")==1){
				String[] class2Method = URI.split(".");
				 return class2Method;
			}
		}
		req.redi
		req.getRequestDispatcher("").forward(arg0, arg1);
		return null;
	}
	
	public static void go404(HttpServletResponse resp){
		try {
			resp.sendRedirect("404.jsp");
			Logf
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void go500(HttpServletResponse resp){
		try {
			resp.sendRedirect("500.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
