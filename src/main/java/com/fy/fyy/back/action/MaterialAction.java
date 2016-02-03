package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Material;
import com.fy.fyy.back.bean.Material.Category;
import com.fy.fyy.back.bean.Material.Unit;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.service.MaterialService;


public class MaterialAction extends BaseAction<Material> {

  private MaterialService materialService = new MaterialService();

  public MaterialAction() {
    bean = new Material();
  }

  public String list() {
    List<Material> materialList = materialService.list( bean );
    getRequestAttrs().put( "beanList", materialList );
    getRequestAttrs().put( "bean", bean );
    return "/materiallist.jsp";
  }

  public String addUI() {
    if ( StrUtil.isId( bean.getId() ) ) {
      getRequestAttrs().put( "bean", bean );
    }
    getRequestAttrs().put( "categorylist", CodeBean.list.get( Category.class ) );
    getRequestAttrs().put( "unitlist", CodeBean.list.get( Unit.class ) );
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
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setUpdateDate( now );
    materialService.update( bean );
    return list();
  }

  public String del() {
    materialService.delete( bean );
    return list();
  }

}
