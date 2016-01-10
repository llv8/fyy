package com.fy.fyy.back.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fy.fyy.back.bean.User;


public class UserService extends BaseService<User> {

  public User login( User user ) {
    User result = null;
    List<User> list = getList( user, new QuerySqlStr<User>() {

      @Override
      public String get( User bean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and loginName=?" );
        sb.append( " and password=?" );
        bean.getQueryParams().clear();
        bean.getQueryParams().add( bean.getLoginName() );
        bean.getQueryParams().add( bean.getPassword() );
        return sb.toString();
      }
    } );
    if ( CollectionUtils.isEmpty( list ) ) {

    }
    else if ( list.size() == 1 ) {
      result = list.get( 0 );
    }
    else {

    }
    return result;
  }

  public List<User> list( User user ) {
    List<User> list = getList( user, new QuerySqlStr<User>() {

      @Override
      public String get( User bean ) {
        return "";
      }
    } );
    return list;
  }

}
