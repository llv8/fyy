package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.Inventory;
import com.fy.fyy.back.bean.Material;


public class InventoryService extends BaseService<Inventory> {

  private MaterialService materialService = new MaterialService();
  private CustomerService customerService = new CustomerService();

  public List<Inventory> list( Inventory inventory ) {
    List<Inventory> list = getList( inventory, new QuerySqlStr<Inventory>() {

      @Override
      public String get( Inventory bean ) {
        return "";
      }
    } );

    materialService.loadBeans( list, Material.class );
    customerService.loadBeans( list, Customer.class );
    return list;
  }
}
