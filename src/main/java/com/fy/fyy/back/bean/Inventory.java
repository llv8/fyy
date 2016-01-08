package com.fy.fyy.back.bean;

import java.sql.Date;




public class Inventory extends BaseBean {

  private Date createDate;
  private Date updateDate;
  private Type type;
  private Integer typeId;
  private User user;
  private Integer userId;
  private Material material;
  private Integer materialId;
  private Integer num;
  private String note;

  public static Inventory getInstance( Integer id ) {
    Inventory inv = new Inventory();
    inv.setId( id );
    return inv;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate( Date updateDate ) {
    this.updateDate = updateDate;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId( Integer userId ) {
    this.userId = userId;
  }

  public Integer getMaterialId() {
    return materialId;
  }

  public void setMaterialId( Integer materialId ) {
    this.materialId = materialId;
    CachedBean.get().getValue( Material.getInstance( materialId ) );
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate( Date createDate ) {
    this.createDate = createDate;
  }

  public Type getType() {
    return type;
  }

  public void setType( Type type ) {
    this.type = type;
  }

  public User getUser() {
    return user;
  }

  public void setUser( User user ) {
    this.user = user;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial( Material material ) {
    this.material = material;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum( Integer num ) {
    this.num = num;
  }

  public String getNote() {
    return note;
  }

  public void setNote( String note ) {
    this.note = note;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId( Integer typeId ) {
    this.typeId = typeId;
  }

  public static class Type extends CodeBean {

    public static final Type in = new Type( 1, "入库" );
    public static final Type out = new Type( 2, "出库" );

    private Type( int id, String name ) {
      super( id, name );
    }
  }

}
