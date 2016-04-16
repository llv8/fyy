package com.fy.fyy.back.action.imp;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fy.fyy.back.action.ActionAnnotation;
import com.fy.fyy.back.action.ActionModel;
import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.Employee;
import com.fy.fyy.back.bean.Employee.Department;
import com.fy.fyy.back.bean.Employee.Position;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.service.BaseService;
import com.fy.fyy.back.service.CustomerService;
import com.fy.fyy.back.service.EmployeeService;


@ActionAnnotation(name = ActionModel.Employee)
public class EmployeeAction extends BaseAction<Employee> {

  private static Log logger = Log.getInstance( EmployeeAction.class );
  private EmployeeService employeeService = new EmployeeService();

  public EmployeeAction() {
    bean = new Employee();
  }

  @ActionAnnotation(name = ActionModel.EmployeeQuery)
  public String list() {
    List<Employee> employeeList = employeeService.list( bean );
    ContextUtil.getReqAttrs().put( "beanList", employeeList );
    ContextUtil.getReqAttrs().put( "bean", bean );
    return "/employeelist.jsp";
  }

  @ActionAnnotation(name = ActionModel.EmployeeAdd)
  public String addUI() {
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "departmentlist", CodeBean.list.get( Department.class ) );
    ContextUtil.getReqAttrs().put( "positionlist", CodeBean.list.get( Position.class ) );
    return "/addemployee.jsp";
  }

  public String add() {

    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setCreateDate( now );
    bean.setUpdateDate( now );
    bean.setStatusId( Employee.Status.active.getId() );
    employeeService.insert( bean );
    return list();
  }

  @ActionAnnotation(name = ActionModel.EmployeeUpdate)
  public String updateUI() {
    bean = BaseService.getBean( bean );
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "departmentlist", CodeBean.list.get( Department.class ) );
    ContextUtil.getReqAttrs().put( "positionlist", CodeBean.list.get( Position.class ) );
    return "/addemployee.jsp";
  }

  public String update() {

    Employee persistEmployee = BaseService.getBean( bean );
    persistEmployee.setName( bean.getName() );
    persistEmployee.setUpdateDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
    persistEmployee.setDepartmentId( bean.getDepartmentId() );
    persistEmployee.setEmail( bean.getEmail() );
    persistEmployee.setPhone( bean.getPhone() );
    persistEmployee.setPositionId( bean.getPositionId() );

    employeeService.update( persistEmployee );
    return list();
  }

  @ActionAnnotation(name = ActionModel.EmployeeDel)
  public String del() {
    employeeService.delete( bean );
    return list();
  }

  public String validAdd() {
    return validAdd2Update() ? null : addUI();
  }

  public String validUpdate() {
    if ( !validAdd2Update() ) {
      ContextUtil.getReqAttrs().put( "bean", bean );
      ContextUtil.getReqAttrs().put( "departmentlist", CodeBean.list.get( Department.class ) );
      ContextUtil.getReqAttrs().put( "positionlist", CodeBean.list.get( Position.class ) );
      return "/addemployee.jsp";
    }
    else {
      return null;
    }
  }

  public boolean validAdd2Update() {
    boolean result = true;
    String name = bean.getName();

    if ( StringUtils.isEmpty( name ) ) {
      error( "用户名不能为空" );
      logger.error( "username is not null" );
      result = false;
    }

    if ( !StringUtils.isEmpty( name ) && name.length() > Constraint.MAX_LEN ) {
      error( "用户名太长" );
      logger.error( "username'length is more than " + Constraint.MAX_LEN );
      result = false;
    }

    Matcher emailMatcher = Pattern.compile( Constraint.EMAIL_REG ).matcher( bean.getEmail() );

    if ( StringUtils.isEmpty( bean.getEmail() ) || !emailMatcher.matches() ) {
      error( "邮箱不合法" );
      result = false;
    }

    Matcher phoneMatcher = Pattern.compile( Constraint.PHONE_REG ).matcher( bean.getPhone() );

    if ( StringUtils.isEmpty( bean.getPhone() ) || !phoneMatcher.matches() ) {
      error( "手机号不合法" );
      result = false;
    }

    if ( !bean.isId( bean.getDepartmentId() ) ) {
      error( "请选择部门" );
      result = false;
    }

    if ( !bean.isId( bean.getPositionId() ) ) {
      error( "请选择职位" );
      result = false;
    }
    return result;
  }

  public String validDel() {
    CustomerService customerService = new CustomerService();
    List<Customer> customerList = customerService.getCustomerList( bean.getId() );
    if ( !CollectionUtils.isEmpty( customerList ) ) {
      error( "请先在'" + ActionModel.Customer.getName() + "'中删除与本员工相关联的用户信息！" );
      return list();
    }
    return null;
  }
}
