package com.fy.fyy.back.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.common.Constraint;
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
			Pair<String, Boolean> pair = ServletUtil.exec(req);
			// forward or redirect
			ServletUtil.go(pair.getLeft(), pair.getRight(), req, resp);
		} catch (Exception e) {
			List<String> list = new ArrayList<>();
			list.add("系统错误,请与管理员联系");
			req.setAttribute("error", list);
			logger.error(e.getMessage(), e);
			String referrer = req.getHeader("referer");
			String host = req.getHeader("Host");
			if (StringUtils.isEmpty(referrer) || !referrer.contains(host)) {
				ServletUtil.go(Constraint.LOGIN_UI, true, req, resp);
			} else {
				ServletUtil.go(referrer.substring(req.getContextPath().length(), referrer.length()), false, req, resp);
			}
		} finally {
			ContextUtil.getReqAttrs().clear();
			ContextUtil.getSessionAttrs().clear();
			ContextUtil.getReqParams().clear();
		}
	}

}
