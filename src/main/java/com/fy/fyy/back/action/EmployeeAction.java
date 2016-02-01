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
public class EmployeeAction extends BaseAction {

  private Employee employee = new Employee();
  private EmployeeService employeeService = new EmployeeService();

  @ActionMethodAnnotation(name = "职员列表")
  public String list() {
    List<Employee> employeeList = employeeService.list( employee );
    getRequestAttrs().put( "employeeList", employeeList );
    getRequestAttrs().put( "employee", employee );
    return "/employeelist.jsp";
  }

  public String addUI() {
    if ( StrUtil.isId( employee.getId() ) ) {
      getRequestAttrs().put( "employee", employeeService.getBean( employee ) );
    }
    getRequestAttrs().put( "departmentlist", CodeBean.list.get( Department.class ) );
    getRequestAttrs().put( "positionlist", CodeBean.list.get( Position.class ) );
    getRequestAttrs().put( "statuslist", CodeBean.list.get( Status.class ) );
    return "/addemployee.jsp";
  }

  public String add() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    employee.setCreateDate( now );
    employee.setUpdateDate( now );
    employeeService.insert( employee );
    return list();
  }

  public String update() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    employee.setUpdateDate( now );
    employeeService.update( employee );
    return list();
  }

  public String del() {
    employeeService.delete( employee );
    return list();
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee( Employee employee ) {
    this.employee = employee;
  }

  public EmployeeService getEmployeeService() {
    return employeeService;
  }

  public void setEmployeeService( EmployeeService employeeService ) {
    this.employeeService = employeeService;
  }

}
