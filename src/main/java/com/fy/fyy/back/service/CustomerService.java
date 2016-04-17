package com.fy.fyy.back.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;


public class CustomerService extends BaseService<Customer> {

  private static Logger logger = LoggerFactory.getLogger( CustomerService.class );

  public Customer login( Customer searchBean ) {
    Customer result = null;
    List<Customer> list = getList( searchBean, new QuerySqlStr<Customer>() {

      @Override
      public String get( Customer searchBean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and name=?" );
        sb.append( " and password=?" );
        searchBean.getQueryParams().clear();
        searchBean.getQueryParams().add( searchBean.getName() );
        searchBean.getQueryParams().add( searchBean.getPassword() );
        return sb.toString();
      }
    } );
    if ( CollectionUtils.isEmpty( list ) ) {
      return null;
    }
    else if ( list.size() == 1 ) {
      result = list.get( 0 );
    }
    else {
      logger.error( "DB has same username and password more than one." );
    }
    return result;
  }

  public List<Customer> list( Customer searchBean ) {
    List<Customer> list = getList( searchBean, new QuerySqlStr<Customer>() {

      @Override
      public String get( Customer searchBean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and id<>?" );
        sb.append( " and employeeId is not null" );
        searchBean.getQueryParams().clear();
        searchBean.getQueryParams().add( ( (Customer)ContextUtil.getSessionAttr( Constraint.LOGIN_USER ) ).getId() );
        return sb.toString();
      }
    } );
    return list;
  }

  public List<Customer> getCustomerList( int employeeId ) {
    Customer searchBean = new Customer();
    searchBean.setEmployeeId( employeeId );
    List<Customer> list = getList( searchBean, new QuerySqlStr<Customer>() {

      @Override
      public String get( Customer searchBean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and employeeId=?" );
        searchBean.getQueryParams().clear();
        searchBean.getQueryParams().add( searchBean.getEmployeeId() );
        return sb.toString();
      }
    } );
    return list;
  }

  public List<Customer> getBeanByRole( int roleId ) {
    Customer searchBean = new Customer();
    searchBean.setRoleId( roleId );
    List<Customer> list = getList( searchBean, new QuerySqlStr<Customer>() {

      @Override
      public String get( Customer searchBean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and roleId=?" );
        searchBean.getQueryParams().clear();
        searchBean.getQueryParams().add( searchBean.getRoleId() );
        return sb.toString();
      }
    } );
    return list;
  }

}
