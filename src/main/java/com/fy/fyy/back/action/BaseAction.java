package com.fy.fyy.back.action;

import java.util.ArrayList;
import java.util.List;

import com.fy.fyy.back.bean.BaseBean;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;

public class BaseAction<T extends BaseBean> {

	protected T bean;

	/**
	 * default action
	 * 
	 * @return
	 */
	public String exec() {
		return Constraint.INDEX_UI;
	}

	public T getBean() {
		return bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
	}

	public void info(String message) {
		message("info", message);
	}

	public void warn(String message) {
		message("warn", message);
	}

	public void error(String message) {
		message("error", message);
	}

	private void message(String key, String value) {
		Object obj = ContextUtil.getReqAttr(key);
		if (obj == null) {
			List<String> list = new ArrayList<>();
			list.add(value);
			ContextUtil.getReqAttrs().put(key, list);
		} else {
			((List) obj).add(value);
		}
	}
}
