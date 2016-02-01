package com.fy.fyy.back.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fy.fyy.back.bean.Customer;


public class CustomerService extends BaseService<Customer> {

  public Customer login( Customer customer ) {
    Customer result = null;
    List<Customer> list = getList( customer, new QuerySqlStr<Customer>() {

      @Override
      public String get( Customer bean ) {
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

}
