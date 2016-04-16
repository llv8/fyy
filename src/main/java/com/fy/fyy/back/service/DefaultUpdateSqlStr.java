package com.fy.fyy.back.service;

import com.fy.fyy.back.bean.BaseBean;

public class DefaultUpdateSqlStr<T extends BaseBean> implements UpdateSqlStr<T> {

	public String get(T bean) {
		return "";
	}

}
