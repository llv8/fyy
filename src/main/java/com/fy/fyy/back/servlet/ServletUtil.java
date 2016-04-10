package com.fy.fyy.back.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.action.RedirectAnnotation;
import com.fy.fyy.back.bean.BaseBean;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.db.DBUtil;

public class ServletUtil {
	private static Log logger = Log.getInstance(ServletUtil.class);

	public static String getURI(HttpServletRequest req) {
		return req.getRequestURI().substring(req.getContextPath().length(), req.getRequestURI().length());
	}

	public static Pair<String, String> parseURI(HttpServletRequest req) {
		String uri = getURI(req);
		if (StringUtils.isEmpty(uri))
			return null;
		uri = StrUtil.trim(uri.trim(), StrUtil.C_SLASH);
		String[] uris = uri.split(StrUtil.SLASH);
		if (StringUtils.countMatches(uris[uris.length - 1], StrUtil.DOT) == 1) {
			req.setAttribute("CUR_ACTION", uris[uris.length - 1]);
			String clazz = uris[uris.length - 1].replace(StrUtil.DOT, StrUtil.EMPTY);
			String method = (uris.length == 2) ? uris[0] : uris.length == 1 ? "exec" : null;
			if (method == null)
				return null;
			return new MutablePair<String, String>(clazz, method);
		} else {
			return null;
		}
	}

	public static void go(String uri, Boolean isRedirect, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (isRedirect) {
			resp.sendRedirect(req.getContextPath() + uri);
		} else {
			req.getRequestDispatcher(uri).forward(req, resp);
		}
	}

	private static void loadBean(HttpServletRequest req, BaseAction action) {
		String strId = req.getParameter("bean.id");
		if (StringUtils.isNotEmpty(strId)) {
			action.getBean().setId(Integer.valueOf(strId));
			BaseBean bean = DBUtil.getObjById(action.getBean());
			action.setBean(bean);
		}
	}

	public static Pair<String, Boolean> exec(HttpServletRequest req)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Pair<String, String> class2Method = parseURI(req);

		BaseAction action = (BaseAction) Class.forName(Constraint.PKG_ACTION + class2Method.getLeft()).newInstance();
		loadBean(req, action);
		ContextUtil.copyAttrs(req, action);

		BeanUtils.populate(action, req.getParameterMap());
		Method method = action.getClass().getMethod(class2Method.getRight());
		Object uriObj = method.invoke(action);

		ContextUtil.copyAttrs(action, req);

		return new MutablePair<String, Boolean>(uriObj.toString(),
				method.getAnnotation(RedirectAnnotation.class) != null);
	}

}
