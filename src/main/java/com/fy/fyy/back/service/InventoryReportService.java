package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.InventoryReport;
import com.fy.fyy.back.db.DBUtil;


public class InventoryReportService extends BaseService<InventoryReport> {

  public List<InventoryReport> list() {
    StringBuffer sql = new StringBuffer();
    sql.append( "SELECT base.*," );
    sql.append( " (base.inNum-base.outNum) AS leftNum" );
    sql.append( " FROM" );
    sql.append( "  (SELECT m.name," );
    sql.append( "  m.unitId," );
    sql.append( "  m.categoryId," );
    sql.append( "   SUM(" );
    sql.append( "    CASE" );
    sql.append( "      WHEN i.typeId=1" );
    sql.append( "     THEN i.num" );
    sql.append( "     ELSE 0" );
    sql.append( "    END) AS inNum," );
    sql.append( "    SUM(" );
    sql.append( "   CASE" );
    sql.append( "    WHEN i.typeId=2" );
    sql.append( "     THEN i.num" );
    sql.append( "     ELSE 0" );
    sql.append( "    END) AS outNum" );
    sql.append( "  FROM Inventory i," );
    sql.append( "    Material m" );
    sql.append( "  WHERE i.materialId=m.id" );
    sql.append( "  GROUP BY m.name,m.categoryId,m.unitId" );
    sql.append( "  ) base " );
    return DBUtil.queryBeanList( sql.toString(), new InventoryReport() );
  }

}
