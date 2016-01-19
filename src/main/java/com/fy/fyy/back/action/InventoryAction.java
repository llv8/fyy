package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Employee.Department;
import com.fy.fyy.back.bean.Employee.Position;
import com.fy.fyy.back.bean.Employee.Status;
import com.fy.fyy.back.bean.Inventory;
import com.fy.fyy.back.service.InventoryService;


public class InventoryAction extends BaseAction {

  Inventory inventory = new Inventory();
  InventoryService inventoryService = new InventoryService();

  public String list() {
    List<Inventory> inventoryList = inventoryService.list( inventory );
    getRequestAttrs().put( "inventoryList", inventoryList );
    getRequestAttrs().put( "inventory", inventory );
    return "/inventorylist.jsp";
  }

  public String addUI() {
    getRequestAttrs().put( "departmentlist", CodeBean.list.get( Department.class ) );
    getRequestAttrs().put( "positionlist", CodeBean.list.get( Position.class ) );
    getRequestAttrs().put( "statuslist", CodeBean.list.get( Status.class ) );
    return "/addinventory.jsp";
  }

  public String add() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    inventory.setCreateDate( now );
    inventory.setUpdateDate( now );
    inventoryService.insert( inventory );
    return list();
  }

  public String update() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    inventory.setUpdateDate( now );
    inventoryService.update( inventory );
    return list();
  }

  public String del() {
    inventoryService.delete( inventory );
    return list();
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory( Inventory inventory ) {
    this.inventory = inventory;
  }

  public InventoryService getInventoryService() {
    return inventoryService;
  }

  public void setInventoryService( InventoryService inventoryService ) {
    this.inventoryService = inventoryService;
  }

}
