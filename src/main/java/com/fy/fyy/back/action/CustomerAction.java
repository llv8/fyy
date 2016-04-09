package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.Employee;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.service.CustomerService;
import com.fy.fyy.back.service.EmployeeService;
import com.fy.fyy.back.servlet.ServletUtil;


@ActionAnnotation(name = ActionModel.Customer)
public class CustomerAction extends BaseAction<Customer> {

  private CustomerService customerService = new CustomerService();
  private EmployeeService employeeService = new EmployeeService();

  public CustomerAction() {
    bean = new Customer();
  }

  @RedirectAnnotation
  public String login() {
    Customer customer = customerService.login( bean );
    if ( customer == null ) {
      return ServletUtil.LOGIN_UI;
    }
    else {
      getSessionAttrs().put( ServletUtil.LOGIN_USER, customer );
      return ServletUtil.INDEX_UI;
    }
  }

  @RedirectAnnotation
  public String unLogin() {
    getSessionAttrs().put( ServletUtil.LOGIN_USER, null );
    return ServletUtil.LOGIN_UI;
  }

  public String loginUI() {
    return "/login.jsp";
  }

  public String indexUI() {
    return "/index.jsp";
  }

  @ActionAnnotation(name = ActionModel.CustomerQuery)
  public String list() {
    List<Customer> customerList = customerService.list( bean );
    getRequestAttrs().put( "beanList", customerList );
    getRequestAttrs().put( "bean", bean );
    return "/customerlist.jsp";
  }

  @ActionAnnotation(name = ActionModel.CustomerAdd)
  public String addUI() {
    if ( StrUtil.isId( bean.getId() ) ) {
      getRequestAttrs().put( "bean", bean );
    }
    getRequestAttrs().put( "employeelist", employeeService.list( new Employee() ) );
    return "/addcustomer.jsp";
  }

  @ActionAnnotation(name = ActionModel.CustomerAdd)
  public String add() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setCreateDate( now );
    bean.setUpdateDate( now );
    customerService.insert( bean );
    return list();
  }

  @ActionAnnotation(name = ActionModel.CustomerUpdate)
  public String update() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setUpdateDate( now );
    customerService.update( bean );
    return list();
  }

  @ActionAnnotation(name = ActionModel.CustomerDel)
  public String del() {
    customerService.delete( bean );
    return list();
  }

}
