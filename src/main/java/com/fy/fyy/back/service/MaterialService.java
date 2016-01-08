package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Material;
import com.fy.fyy.back.db.DBUtil;


public class MaterialService {

  public List<Material> getList( Material material ) {
    List<Material> result = null;
    StringBuffer sb = new StringBuffer();
    sb.append( "selet * from Material where 1=1 " );
    DBUtil.queryBeanList( sb.toString(), material );
    return result;
  }
}
