package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Employee;

public class EmployeeService extends BaseService<Employee> {

	public List<Employee> list(Employee searchBean) {
		List<Employee> list = getList(searchBean, new DefaultQuerySqlStr<Employee>());
		return list;
	}

}
