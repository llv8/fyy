package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Inventory;


public class InventoryService extends BaseService<Inventory> {

  public List<Inventory> list( Inventory inventory ) {
    List<Inventory> list = getList( inventory, new QuerySqlStr<Inventory>() {

      @Override
      public String get( Inventory bean ) {
        return "";
      }
    } );
    return list;
  }
}
