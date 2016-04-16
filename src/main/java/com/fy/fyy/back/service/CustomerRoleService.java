package com.fy.fyy.back.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.CustomerRole;


public class CustomerRoleService extends BaseService<CustomerRole> {

  private static Logger logger = LoggerFactory.getLogger( CustomerRoleService.class );

  public List<CustomerRole> list( CustomerRole searchBean ) {
    List<CustomerRole> list = getList( searchBean, new DefaultQuerySqlStr<CustomerRole>() );
    return list;
  }

  public int insert2Update( CustomerRole bean ) {
    //query
    List<CustomerRole> list = getList( bean, new QuerySqlStr<CustomerRole>() {

      @Override
      public String get( CustomerRole searchBean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and customerId=?" );
        searchBean.getQueryParams().clear();
        searchBean.getQueryParams().add( searchBean.getCustomerId() );
        return sb.toString();
      }
    } );

    if ( CollectionUtils.isEmpty( list ) ) {
      insert( bean );
    }
    else {
      CustomerRole persistCustomerRole = list.get( 0 );
      persistCustomerRole.setRoleId( bean.getRoleId() );;
      update( bean );
    }
    return 0;
  }

  public List<CustomerRole> getBeanByCustomerId( CustomerRole searchBean ) {
    List<CustomerRole> list = getList( searchBean, new QuerySqlStr<CustomerRole>() {

      @Override
      public String get( CustomerRole searchBean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and customerId=?" );
        searchBean.getQueryParams().clear();
        searchBean.getQueryParams().add( searchBean.getCustomerId() );
        return sb.toString();
      }
    } );
    return list;
  }
}
