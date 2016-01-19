package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Material;


public class MaterialService extends BaseService<Material>{

  public List<Material> list( Material material ) {
    List<Material> list = getList( material, new QuerySqlStr<Material>() {

      @Override
      public String get( Material bean ) {
        return "";
      }
    } );
    return list;
  }
}
