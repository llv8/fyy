package com.fy.fyy.back.service;

import com.fy.fyy.back.bean.BaseBean;

public class DefaultQuerySqlStr<T extends BaseBean> implements QuerySqlStr<T> {

	public String get(T bean) {
		return "";
	}

}
