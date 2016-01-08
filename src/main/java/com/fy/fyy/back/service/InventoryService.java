package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Inventory;
import com.fy.fyy.back.db.DBUtil;


public class InventoryService extends BaseService<Inventory>{

  public List<Inventory> getList( Inventory inventory ) {
    List<Inventory> result = null;
    StringBuffer sb = new StringBuffer();
    sb.append( "selet * from Inventory where 1=1 " );
    DBUtil.queryBeanList( sb.toString(), inventory );
    return result;
  }
}
