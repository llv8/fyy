package com.fy.fyy.back.action;

import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.service.CustomerService;
import com.fy.fyy.back.servlet.ServletUtil;


public class CustomerAction extends BaseAction<Customer> {

  private CustomerService customerService = new CustomerService();

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

}
