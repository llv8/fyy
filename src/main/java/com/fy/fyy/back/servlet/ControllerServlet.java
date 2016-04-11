package com.fy.fyy.back.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;

public class ControllerServlet extends HttpServlet {
	private static Log logger = Log.getInstance(ControllerServlet.class);
	private static final long serialVersionUID = -423807766712107605L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// invoke action
			Pair<String, Boolean> pair = ServletUtil.exec(req, ServletUtil.getURI(req, req.getRequestURL().toString()));
			// go forward or redirect
			ServletUtil.go(pair.getLeft(), pair.getRight(), req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ServletUtil.go("/500.jsp", true, req, resp);
		} finally {
			ContextUtil.getReqAttrs().clear();
			ContextUtil.getSessionAttrs().clear();
			ContextUtil.getReqParams().clear();
		}
	}

}
