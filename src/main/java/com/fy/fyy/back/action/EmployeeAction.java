package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Employee;
import com.fy.fyy.back.bean.Employee.Department;
import com.fy.fyy.back.bean.Employee.Position;
import com.fy.fyy.back.bean.Employee.Status;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.service.EmployeeService;


@ActionAnnotation(name = "职员管理")
public class EmployeeAction extends BaseAction<Employee> {

  private EmployeeService employeeService = new EmployeeService();

  public EmployeeAction() {
    bean = new Employee();
  }

  @ActionMethodAnnotation(name = "职员列表")
  public String list() {
    List<Employee> employeeList = employeeService.list( bean );
    getRequestAttrs().put( "beanList", employeeList );
    getRequestAttrs().put( "bean", bean );
    return "/employeelist.jsp";
  }

  public String addUI() {
    if ( StrUtil.isId( bean.getId() ) ) {
      getRequestAttrs().put( "bean", bean );
    }
    getRequestAttrs().put( "departmentlist", CodeBean.list.get( Department.class ) );
    getRequestAttrs().put( "positionlist", CodeBean.list.get( Position.class ) );
    getRequestAttrs().put( "statuslist", CodeBean.list.get( Status.class ) );
    return "/addemployee.jsp";
  }

  public String add() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setCreateDate( now );
    bean.setUpdateDate( now );
    employeeService.insert( bean );
    return list();
  }

  public String update() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setUpdateDate( now );
    employeeService.update( bean );
    return list();
  }

  public String del() {
    employeeService.delete( bean );
    return list();
  }
}
