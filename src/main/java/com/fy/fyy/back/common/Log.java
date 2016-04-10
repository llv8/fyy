package com.fy.fyy.back.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

	private Logger logger = null;

	public Log(Class clazz) {
		logger = LoggerFactory.getLogger(clazz);
	}

	public static Log getInstance(Class clazz) {
		return new Log(clazz);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void warn(String msg) {
		logger.warn(msg);
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	public void error(String format, Object... arguments) {
		logger.error(format, arguments);
	}

	private static String msg(String msg) {
		String customerName = (String) ContextUtil.getSessionAttr(Constraint.LOGIN_USER);
		return " [" + customerName + "] " + msg;
	}
}
