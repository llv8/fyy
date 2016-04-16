package com.fy.fyy.back.action.imp;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fy.fyy.back.action.ActionAnnotation;
import com.fy.fyy.back.action.ActionModel;
import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Inventory;
import com.fy.fyy.back.bean.Material;
import com.fy.fyy.back.bean.Material.Category;
import com.fy.fyy.back.bean.Material.Unit;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.service.BaseService;
import com.fy.fyy.back.service.InventoryService;
import com.fy.fyy.back.service.MaterialService;


@ActionAnnotation(name = ActionModel.Material)
public class MaterialAction extends BaseAction<Material> {

  private static Log logger = Log.getInstance( MaterialAction.class );
  private MaterialService materialService = new MaterialService();

  public MaterialAction() {
    bean = new Material();
  }

  @ActionAnnotation(name = ActionModel.MaterialQuery)
  public String list() {
    List<Material> materialList = materialService.list( bean );
    ContextUtil.getReqAttrs().put( "beanList", materialList );
    ContextUtil.getReqAttrs().put( "bean", bean );
    return "/materiallist.jsp";
  }

  @ActionAnnotation(name = ActionModel.MaterialAdd)
  public String addUI() {

    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "categorylist", CodeBean.list.get( Category.class ) );
    ContextUtil.getReqAttrs().put( "unitlist", CodeBean.list.get( Unit.class ) );
    return "/addmaterial.jsp";

  }

  @ActionAnnotation(name = ActionModel.MaterialUpdate)
  public String updateUI() {
    bean = BaseService.getBean( bean );
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "categorylist", CodeBean.list.get( Category.class ) );
    ContextUtil.getReqAttrs().put( "unitlist", CodeBean.list.get( Unit.class ) );
    return "/addmaterial.jsp";

  }

  public String add() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setCreateDate( now );
    bean.setUpdateDate( now );
    materialService.insert( bean );
    return list();
  }

  public String update() {
    Material persistMaterial = BaseService.getBean( bean );
    persistMaterial.setUpdateDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
    persistMaterial.setName( bean.getName() );
    persistMaterial.setCategoryId( bean.getCategoryId() );
    persistMaterial.setUnitId( bean.getUnitId() );
    persistMaterial.setNote( bean.getNote() );
    materialService.update( persistMaterial );
    return list();
  }

  @ActionAnnotation(name = ActionModel.MaterialDel)
  public String del() {
    materialService.delete( bean );
    return list();
  }

  public String validAdd() {
    return validAdd2Update() ? null : addUI();
  }

  public String validUpdate() {
    if ( !validAdd2Update() ) {
      ContextUtil.getReqAttrs().put( "bean", bean );
      ContextUtil.getReqAttrs().put( "categorylist", CodeBean.list.get( Category.class ) );
      ContextUtil.getReqAttrs().put( "unitlist", CodeBean.list.get( Unit.class ) );
      return "/addmaterial.jsp";
    }
    else {
      return null;
    }
  }

  public boolean validAdd2Update() {
    boolean result = true;
    String name = bean.getName();
    if ( StringUtils.isEmpty( name ) ) {
      error( "物料名不能为空" );
      logger.error( "marterialname can not null" );
      result = false;
    }

    if ( !StringUtils.isEmpty( name ) && name.length() > Constraint.MAX_LEN ) {
      error( "物料名太长" );
      logger.error( "marterialname is more than " + Constraint.MAX_LEN );
      result = false;
    }

    if ( !StringUtils.isEmpty( name ) && name.length() <= Constraint.MAX_LEN ) {
      List<Material> list = null;
      if ( !bean.isId( bean.getId() ) ) {
        list = materialService.getBeanByName( bean );
      }
      else {
        list = materialService.getBeanByNameIgnonreSelf( bean );
      }
      if ( !CollectionUtils.isEmpty( list ) && list.size() > 0 ) {
        error( "该物料名已存在" );
        logger.error( name + " marterialname already exists" );
        result = false;
      }
    }

    return result;
  }

  public String validDel() {
    InventoryService inventoryService = new InventoryService();
    Inventory searchInventoryBean = new Inventory();
    searchInventoryBean.setMaterialId( bean.getId() );
    List<Inventory> inventoryList = inventoryService.getBeanByMaterialId( searchInventoryBean );
    if ( !CollectionUtils.isEmpty( inventoryList ) ) {
      error( "请先删除与该库存相关的物料！" );
      return list();
    }
    return null;
  }

}
