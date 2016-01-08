package com.fy.fyy.back.action;

import com.fy.fyy.back.action.To.TYPE;
import com.fy.fyy.back.bean.User;
import com.fy.fyy.back.service.UserService;


public class UserAction {

  private User user = new User();
  private UserService userService = new UserService();

  @To(TYPE.redirect)
  public String login() {
    User user = userService.login( this.user );
    if ( user == null ) {

    }
    else {

    }
    return "";
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
