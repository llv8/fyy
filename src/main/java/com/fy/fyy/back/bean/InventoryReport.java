package com.fy.fyy.back.bean;

import java.sql.Date;


public class InventoryReport extends BaseBean {

  private String name;
  private Integer categoryId;
  private Integer unitId;
  private Integer inNum;
  private Integer outNum;
  private Integer leftNum;
  private Date date;

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId( Integer categoryId ) {
    this.categoryId = categoryId;
  }

  public Integer getUnitId() {
    return unitId;
  }

  public void setUnitId( Integer unitId ) {
    this.unitId = unitId;
  }

  public Integer getInNum() {
    return inNum;
  }

  public void setInNum( Integer inNum ) {
    this.inNum = inNum;
  }

  public Integer getOutNum() {
    return outNum;
  }

  public void setOutNum( Integer outNum ) {
    this.outNum = outNum;
  }

  public Integer getLeftNum() {
    return leftNum;
  }

  public void setLeftNum( Integer leftNum ) {
    this.leftNum = leftNum;
  }

  public Date getDate() {
    return date;
  }

  public void setDate( Date date ) {
    this.date = date;
  }

}
