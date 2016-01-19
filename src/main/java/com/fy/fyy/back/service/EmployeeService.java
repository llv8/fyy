package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Employee;


public class EmployeeService extends BaseService<Employee> {

  public List<Employee> list( Employee employee ) {
    List<Employee> list = getList( employee, new QuerySqlStr<Employee>() {

      @Override
      public String get( Employee bean ) {
        return "";
      }
    } );
    return list;
  }
  
}
