package com.fy.fyy.back.action;

import com.fy.fyy.back.bean.User;
import com.fy.fyy.back.service.UserService;
import com.fy.fyy.back.servlet.ServletUtil;


public class UserAction extends BaseAction {

  private User user = new User();
  private UserService userService = new UserService();

  @Redirect
  public String login() {
    User user = userService.login( this.user );
    if ( user == null ) {
      return ServletUtil.LOGIN_UI;
    }
    else {
      getSessionAttrs().put( ServletUtil.LOGIN_FLAG, true );
      return ServletUtil.INDEX_UI;
    }
  }

  public String loginUI() {
    return "/login.jsp";
  }

  public String indexUI() {
    return "/index.jsp";
  }

  public User getUser() {
    return user;
  }

  public void setUser( User user ) {
    this.user = user;
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService( UserService userService ) {
    this.userService = userService;
  }

}
