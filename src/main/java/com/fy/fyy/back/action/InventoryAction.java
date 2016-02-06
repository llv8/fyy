package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.Inventory;
import com.fy.fyy.back.bean.Inventory.Type;
import com.fy.fyy.back.bean.Material;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.service.InventoryService;
import com.fy.fyy.back.service.MaterialService;
import com.fy.fyy.back.servlet.ServletUtil;


public class InventoryAction extends BaseAction<Inventory> {

  InventoryService inventoryService = new InventoryService();

  public InventoryAction() {
    bean = new Inventory();
  }

  public String list() {
    List<Inventory> inventoryList = inventoryService.list( bean );

    getRequestAttrs().put( "beanList", inventoryList );
    getRequestAttrs().put( "bean", bean );
    return "/inventorylist.jsp";
  }

  public String addUI() {
    if ( StrUtil.isId( bean.getId() ) ) {
      getRequestAttrs().put( "bean", bean );
    }
    MaterialService marterialService = new MaterialService();
    Material material = new Material();
    material.getPageInfo().setPageFlag( false );
    getRequestAttrs().put( "materiallist", marterialService.list( material ) );
    getRequestAttrs().put( "typelist", CodeBean.list.get( Type.class ) );
    return "/addinventory.jsp";
  }

  public String add() {

    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setCreateDate( now );
    bean.setUpdateDate( now );
    bean.setCustomerId( ( (Customer)getSessionAttrs().get( ServletUtil.LOGIN_USER ) ).getId() );
    inventoryService.insert( bean );
    return list();
  }

  public String update() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setUpdateDate( now );
    bean.setCustomerId( ( (Customer)getSessionAttrs().get( ServletUtil.LOGIN_USER ) ).getId() );
    inventoryService.update( bean );
    return list();
  }

  public String del() {
    inventoryService.delete( bean );
    return list();
  }

}
