package com.fy.fyy.back.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.BaseBean;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DBUtil {

  private static Logger logger = LoggerFactory.getLogger( DBUtil.class );
  private static final String QUERY = "select * from %s where 1=1";
  private static final String QUERY_COUNT = "select count(0) from (%s) abc";
  private static ComboPooledDataSource dataSource;
  static {
    try {
      dataSource = new ComboPooledDataSource();
      dataSource.setUser( "root" );
      dataSource.setPassword( "root" );
      dataSource.setJdbcUrl( "jdbc:mysql://localhost:3306/fyy" );
      dataSource.setDriverClass( "com.mysql.jdbc.Driver" );
      dataSource.setInitialPoolSize( 10 );
      dataSource.setMinPoolSize( 5 );
      dataSource.setMaxPoolSize( 50 );
      dataSource.setMaxStatements( 100 );
      dataSource.setMaxIdleTime( 60 );
    }
    catch ( Exception e ) {
      logger.error( "data source init failure!" );
    }
  }

  public static Connection getConn() {
    Connection conn = null;
    if ( null != dataSource ) {
      try {
        conn = dataSource.getConnection();
      }
      catch ( SQLException e ) {
        logger.error( "get connection failure!" );
      }
    }
    return conn;
  }

  public static String getSQL( Class<?> clazz ) {
    return String.format( QUERY, clazz.getSimpleName() );
  }

  public static <T extends BaseBean> List<T> queryBeanList( String sql, T bean ) {
    Connection conn = getConn();
    try {
      if ( bean.getPageInfo().isPageFlag() ) {
        Long count = new QueryRunner().query( getConn(), String.format( QUERY_COUNT, sql ), new ScalarHandler<Long>(), bean.getQueryParams().toArray() );
        bean.getPageInfo().setCountRecord( count.intValue() );
      }
      if ( bean.getPageInfo().isPageFlag() ) {
        sql = sql + " limit  " + bean.getPageInfo().getPageSize() * ( bean.getPageInfo().getCurrentPageNum() - 1 ) + " , " + bean.getPageInfo().getPageSize() + " ";
      }
      return new QueryRunner().query( getConn(), sql, getBeanListHandler( bean ), bean.getQueryParams().toArray() );
    }
    catch ( Exception e ) {
      e.printStackTrace();
      logger.error( "query failure!" );
      return null;
    }
    finally {
      if ( conn != null ) {
        try {
          conn.close();
        }
        catch ( SQLException e ) {}
      }
    }
  }

  private static <T> BeanListHandler<T> getBeanListHandler( T bean ) {
    return new BeanListHandler<T>( (Class<T>)bean.getClass() );
  }

  public static int update( String sql, Object... objs ) {
    Connection conn = getConn();
    try {
      return new QueryRunner().update( conn, sql, objs );
    }
    catch ( Exception e ) {
      logger.error( "update failure!" );
      return 0;
    }
    finally {

      if ( conn != null ) {
        try {
          conn.close();
        }
        catch ( SQLException e ) {}
      }

    }

  }

  public static <T extends BaseBean> T getObjById( T bean ) {
    String tableName = bean.getClass().getSimpleName();
    String sql = getSQL( bean.getClass() ) + " and id=?";
    List<T> list = queryBeanList( sql, bean );
    if ( CollectionUtils.isEmpty( list ) ) {
      return null;
    }
    else {
      return queryBeanList( sql, bean ).get( 0 );
    }

  }

  public static <T extends BaseBean> int insert( T obj ) {
    Pair<String, Object[]> pair = getInsertSql( obj );
    return update( pair.getLeft(), pair.getRight() );
  }

  public static <T extends BaseBean> int delete( T obj ) {
    String tableName = obj.getClass().getSimpleName();
    return update( "delete from " + tableName + " where id = ?", obj.getId() );
  }

  private static <T extends BaseBean> Pair<String, Object[]> getInsertSql( T obj ) {
    String tableName = obj.getClass().getSimpleName();
    Field[] fields = obj.getClass().getDeclaredFields();
    List<String> keys = new ArrayList<>();
    List<String> placeHolds = new ArrayList<>();
    List<Object> values = new ArrayList<>();
    for ( int i = 0; i < fields.length; i++ ) {
      if ( isConvertField( fields[i].getType() ) ) {
        String fieldName = fields[i].getName();
        String methodName = "get" + StringUtils.capitalize( fieldName );
        keys.add( fieldName );
        placeHolds.add( "?" );
        try {
          values.add( obj.getClass().getMethod( methodName ).invoke( obj ) );
        }
        catch ( Exception e ) {
          logger.error( "getInsertSql reflect method invoke error." );
        }

      }
    }
    String sql = String.format( "insert into %s (%s) values (%s)", tableName, StringUtils.join( keys.toArray(), "," ), StringUtils.join( values.toArray(), "," ) );
    Object[] objs = values.toArray();
    Pair<String, Object[]> pair = new ImmutablePair<String, Object[]>( sql, objs );
    return pair;
  }

  private static boolean isConvertField( Class<?> clazz ) {
    return Long.class.equals( clazz ) || Integer.class.equals( clazz ) || Short.class.equals( clazz ) || Float.class.equals( clazz ) || Double.class.equals( clazz )
        || Boolean.class.equals( clazz ) || String.class.equals( clazz ) || Date.class.equals( clazz );
  }

}
