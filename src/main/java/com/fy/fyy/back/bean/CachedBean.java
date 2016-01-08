package com.fy.fyy.back.bean;

import java.util.HashMap;
import java.util.Map;

import com.fy.fyy.back.db.DBUtil;


public class CachedBean {

  private static CachedBean instance;

  private static Map<String, ? super BaseBean> map = new HashMap<>();

  public static CachedBean get() {
    if ( instance == null ) {
      instance = new CachedBean();
    }
    return instance;
  }

  public static <T extends BaseBean> T getValue( T obj ) {
    String key = obj.getClass().getName() + "_" + obj.getId();
    T result = null;
    if ( !map.containsKey( key ) ) {
      result = DBUtil.<T> getObjById( obj );
      map.put( key, result );
    }
    else {
      result = (T)map.get( key );
    }
    return result;
  }
}
