package com.fy.fyy.back.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.Customer;

public class Log {

	private Logger logger = null;

	public Log(Class clazz) {
		logger = LoggerFactory.getLogger(clazz);
	}

	public static Log getInstance(Class clazz) {
		return new Log(clazz);
	}

	public void info(String msg) {
		logger.info(msg(msg));
	}

	public void warn(String msg) {
		logger.warn(msg(msg));
	}

	public void error(String msg) {
		logger.error(msg(msg));
	}

	public void error(String msg, Throwable t) {
		logger.error(msg(msg), t);
	}

	public void error(String format, Object... arguments) {
		logger.error(msg(format), arguments);
	}

	private static String msg(String msg) {
		Customer customer = (Customer) ContextUtil.getSessionAttr(Constraint.LOGIN_USER);
		return (customer == null ? "" : " [" + customer.getName() + "] ") + msg;
	}
}
