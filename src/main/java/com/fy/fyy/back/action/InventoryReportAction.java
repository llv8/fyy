package com.fy.fyy.back.action;

import java.util.List;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.InventoryReport;
import com.fy.fyy.back.bean.Material.Category;
import com.fy.fyy.back.bean.Material.Unit;
import com.fy.fyy.back.service.InventoryReportService;


public class InventoryReportAction extends BaseAction<InventoryReport> {

  InventoryReportService inventoryReportService = new InventoryReportService();

  public InventoryReportAction() {
    bean = new InventoryReport();
  }

  public String list() {
    List<InventoryReport> inventoryReportList = inventoryReportService.list();
    getRequestAttrs().put( "beanList", inventoryReportList );
    getRequestAttrs().put( "bean", bean );
    getRequestAttrs().put( "categorymap", CodeBean.map.get( Category.class ) );
    getRequestAttrs().put( "unitmap", CodeBean.map.get( Unit.class ) );
    return "/inventoryreport.jsp";
  }

}
