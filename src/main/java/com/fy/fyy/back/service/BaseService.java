package com.fy.fyy.back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.BaseBean;
import com.fy.fyy.back.common.StrUtil;
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

  public <L> void loadBeans( List<L> beanList, Class<T> clazz ) {
    if ( CollectionUtils.isEmpty( beanList ) ) return;
    Set<Integer> ids = StrUtil.getIds( beanList, clazz );
    final String idStrs = StrUtil.getIds( ids );
    List<T> attrBeans = null;
    attrBeans = getList( StrUtil.getInstance( clazz ), new QuerySqlStr<T>() {

      @Override
      public String get( T bean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and id in (" + idStrs + ")" );
        bean.getQueryParams().clear();
        return sb.toString();
      }
    } );
    Map<Integer, T> attrBeanMap = new HashMap<Integer, T>();
    for ( T attrBean : attrBeans ) {
      attrBeanMap.put( attrBean.getId(), attrBean );
    }

    for ( L bean : beanList ) {
      T attrBean = attrBeanMap.get( StrUtil.invokeGetAttrBeanId( bean, clazz ) );
      StrUtil.invokeSetAttrBean( bean, attrBean, clazz );
    }

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
