package com.fy.fyy.back.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.BaseBean;
import com.fy.fyy.back.db.DBUtil;


public class BaseService<T extends BaseBean> {

  private static Logger logger = LoggerFactory.getLogger( BaseService.class );

  protected List<T> getList( T bean, QuerySqlStr<T> querySqlStr ) {
    List<T> result = null;
    StringBuffer sb = new StringBuffer( DBUtil.getSQL( bean.getClass() ) );
    if ( querySqlStr != null ) sb.append( querySqlStr.get( bean ) );
    result = DBUtil.queryBeanList( sb.toString(), bean );
    if ( bean.getPageInfo().isPageFlag() ) {
      bean.getPageInfo().setCountPage(
          bean.getPageInfo().getCountRecord() / bean.getPageInfo().getPageSize() + ( bean.getPageInfo().getCountRecord() % bean.getPageInfo().getPageSize() == 0 ? 0 : 1 ) );
      if ( bean.getPageInfo().getCountPage() == 0 ) bean.getPageInfo().setCountPage( 1 );
    }
    return result;
  }

  public T getBean( T bean ) {

    return DBUtil.getObjById( bean );

  }

  public int insert( T bean ) {
    return DBUtil.insert( bean );
  }

  public int delete( T bean ) {
    return DBUtil.delete( bean );
  }

  public int update( T bean, UpdateSqlStr<T> updateSqlStr, QuerySqlStr<T> querySqlStr ) {
    StringBuffer sb = new StringBuffer();
    sb.append( "update " + bean.getClass().getSimpleName() + " " );
    sb.append( " set" );
    if ( updateSqlStr != null ) sb.append( updateSqlStr.get( bean ) );
    sb.append( " where 1=1" );
    if ( querySqlStr != null ) sb.append( querySqlStr.get( bean ) );
    return 0;
  }

  public int update( T bean ) {
    return DBUtil.update( bean );
  }

  public static Logger getLogger() {
    return logger;
  }

}
