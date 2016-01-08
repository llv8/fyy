package com.fy.fyy.back.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CodeBean {

  private int id;
  private String name;
  private static List<CodeBean> list = new ArrayList<>();
  private static Map<Integer, CodeBean> map = new HashMap<Integer, CodeBean>();

  public CodeBean( int id, String name ) {
    this.id = id;
    this.name = name;
    list.add( this );
    map.put( id, this );
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

  public static List<CodeBean> getList() {
    return list;
  }

  public static Map<Integer, CodeBean> getMap() {
    return map;
  }

}
