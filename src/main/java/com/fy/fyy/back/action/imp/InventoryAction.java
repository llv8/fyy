package com.fy.fyy.back.action.imp;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.fy.fyy.back.action.ActionAnnotation;
import com.fy.fyy.back.action.ActionModel;
import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.Inventory;
import com.fy.fyy.back.bean.Inventory.Type;
import com.fy.fyy.back.bean.Material;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.service.BaseService;
import com.fy.fyy.back.service.InventoryService;
import com.fy.fyy.back.service.MaterialService;


@ActionAnnotation(name = ActionModel.Inventory)
public class InventoryAction extends BaseAction<Inventory> {

  private static Log logger = Log.getInstance( InventoryAction.class );
  InventoryService inventoryService = new InventoryService();

  public InventoryAction() {
    bean = new Inventory();
  }

  @ActionAnnotation(name = ActionModel.InventoryQuery)
  public String list() {
    List<Inventory> inventoryList = inventoryService.list( bean );

    ContextUtil.getReqAttrs().put( "beanList", inventoryList );
    ContextUtil.getReqAttrs().put( "bean", bean );
    return "/inventorylist.jsp";
  }

  @ActionAnnotation(name = ActionModel.InventoryAdd)
  public String addUI() {
    MaterialService marterialService = new MaterialService();
    Material material = new Material();
    material.getPageInfo().setPageFlag( false );
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "materiallist", marterialService.list( material ) );
    ContextUtil.getReqAttrs().put( "typelist", CodeBean.list.get( Type.class ) );
    return "/addinventory.jsp";
  }

  public String add() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setCreateDate( now );
    bean.setUpdateDate( now );
    bean.setCustomerId( ( (Customer)ContextUtil.getSessionAttr( Constraint.LOGIN_USER ) ).getId() );
    inventoryService.insert( bean );
    return list();
  }

  @ActionAnnotation(name = ActionModel.InventoryUpdate)
  public String updateUI() {
    bean = BaseService.getBean( bean );
    MaterialService marterialService = new MaterialService();
    Material material = new Material();
    material.getPageInfo().setPageFlag( false );
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "materiallist", marterialService.list( material ) );
    ContextUtil.getReqAttrs().put( "typelist", CodeBean.list.get( Type.class ) );
    return "/addinventory.jsp";
  }

  public String update() {
    
    Inventory persistInventory = BaseService.getBean( bean );
    persistInventory.setMaterialId( bean.getMaterialId() );
    persistInventory.setUpdateDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
    persistInventory.setTypeId( bean.getTypeId() );
    persistInventory.setNum( bean.getNum() );
    persistInventory.setNote( bean.getNote() );
    persistInventory.setCustomerId(  ( (Customer)ContextUtil.getSessionAttr( Constraint.LOGIN_USER ) ).getId() );

    inventoryService.update( persistInventory );
    return list();
  }

  @ActionAnnotation(name = ActionModel.InventoryDel)
  public String del() {
    inventoryService.delete( bean );
    return list();
  }

  public String validAdd() {
    return validAdd2Update() ? null : addUI();
  }

  public String validUpdate() {
    if ( !validAdd2Update() ) {
      MaterialService marterialService = new MaterialService();
      Material material = new Material();
      material.getPageInfo().setPageFlag( false );
      ContextUtil.getReqAttrs().put( "bean", bean );
      ContextUtil.getReqAttrs().put( "materiallist", marterialService.list( material ) );
      ContextUtil.getReqAttrs().put( "typelist", CodeBean.list.get( Type.class ) );
      return "/addinventory.jsp";
    }
    else {
      return null;
    }
  }

  public boolean validAdd2Update() {
    boolean result = true;
    Integer num = bean.getNum();

    if ( num == null || num==0 ) {
      error( "数量不合法" );
      logger.error( "inventorynum is not null" );
      result = false;
    }
    

    return result;
  }

}
