package com.fy.fyy.back.action;

import java.util.List;

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
      getSessionAttrs().put( ServletUtil.LOGIN_USER, user );
      return ServletUtil.INDEX_UI;
    }
  }

  @Redirect
  public String unLogin() {
    getSessionAttrs().put( ServletUtil.LOGIN_USER, null );
    return ServletUtil.LOGIN_UI;
  }

  public String list() {
    List<User> userList = userService.list( user );
    getRequestAttrs().put( "userList", userList );
    getRequestAttrs().put( "user", user );
    return "/userlist.jsp";
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
