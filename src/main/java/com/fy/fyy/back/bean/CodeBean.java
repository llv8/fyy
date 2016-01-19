package com.fy.fyy.back.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.Employee.Department;
import com.fy.fyy.back.bean.Employee.Position;
import com.fy.fyy.back.bean.Employee.Status;
import com.fy.fyy.back.bean.Inventory.Type;
import com.fy.fyy.back.bean.Material.Category;
import com.fy.fyy.back.bean.Material.Unit;


public class CodeBean {

  private static Logger logger = LoggerFactory.getLogger( CodeBean.class );
  private int id;
  private String name;

  public static final Map<Class<?>, List<CodeBean>> list = new HashMap<>();
  public static final Map<Class<?>, Map<Integer, CodeBean>> map = new HashMap<>();

  static {
    loadCodeBeanClass( Department.class, Position.class, Type.class, Category.class, Unit.class, Status.class );
  }

  private static void loadCodeBeanClass( Class<?>... clazz ) {
    try {
      for ( int i = 0; i < clazz.length; i++ ) {
        Class.forName( clazz[i].getName() );
      }
    }
    catch ( ClassNotFoundException e ) {
      logger.error( "codebean class load failure!" );
    }

  }

  public CodeBean( int id, String name ) {
    this.id = id;
    this.name = name;
    if ( list.get( this.getClass() ) == null ) {
      list.put( this.getClass(), new ArrayList<CodeBean>() );
    }
    if ( map.get( this.getClass() ) == null ) {
      map.put( this.getClass(), new HashMap<Integer, CodeBean>() );
    }
    list.get( this.getClass() ).add( this );
    map.get( this.getClass() ).put( this.getId(), this );

  }

  @Override
  public String toString() {
    return getName();
  }

  public int getId() {
    return id;
  }

  public void setId( int id ) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public List<CodeBean> getList() {
    return list.get( this.getClass() );
  }

  public Map<Integer, CodeBean> getMap() {
    return map.get( this.getClass() );
  }

  public static <T extends CodeBean> T get( Class<T> clazz, Integer id ) {
    return (T)map.get( clazz ).get( id );
  }

  
  
}
