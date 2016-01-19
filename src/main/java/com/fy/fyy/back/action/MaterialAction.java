package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Employee.Department;
import com.fy.fyy.back.bean.Employee.Position;
import com.fy.fyy.back.bean.Employee.Status;
import com.fy.fyy.back.bean.Material;
import com.fy.fyy.back.service.MaterialService;


public class MaterialAction extends BaseAction {

  private Material material = new Material();
  private MaterialService materialService = new MaterialService();

  public String list() {
    List<Material> materialList = materialService.list( material );
    getRequestAttrs().put( "materialList", materialList );
    getRequestAttrs().put( "material", material );
    return "/materiallist.jsp";
  }

  public String addUI() {
    getRequestAttrs().put( "departmentlist", CodeBean.list.get( Department.class ) );
    getRequestAttrs().put( "positionlist", CodeBean.list.get( Position.class ) );
    getRequestAttrs().put( "statuslist", CodeBean.list.get( Status.class ) );
    return "/addmaterial.jsp";
  }

  public String add() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    material.setCreateDate( now );
    material.setUpdateDate( now );
    materialService.insert( material );
    return list();
  }

  public String update() {
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    material.setUpdateDate( now );
    materialService.update( material );
    return list();
  }

  public String del() {
    materialService.delete( material );
    return list();
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial( Material material ) {
    this.material = material;
  }

  public MaterialService getMaterialService() {
    return materialService;
  }

  public void setMaterialService( MaterialService materialService ) {
    this.materialService = materialService;
  }

}
