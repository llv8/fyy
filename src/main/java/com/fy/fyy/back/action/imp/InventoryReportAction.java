package com.fy.fyy.back.action.imp;

import java.util.List;

import com.fy.fyy.back.action.ActionAnnotation;
import com.fy.fyy.back.action.ActionModel;
import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.InventoryReport;
import com.fy.fyy.back.bean.Material.Category;
import com.fy.fyy.back.bean.Material.Unit;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.service.InventoryReportService;


@ActionAnnotation(name = ActionModel.InventoryReport)
public class InventoryReportAction extends BaseAction<InventoryReport> {

  private static Log logger = Log.getInstance( InventoryReportAction.class );
  InventoryReportService inventoryReportService = new InventoryReportService();

  public InventoryReportAction() {
    bean = new InventoryReport();
  }

  @ActionAnnotation(name = ActionModel.InventoryReportQuery)
  public String list() {
    List<InventoryReport> inventoryReportList = inventoryReportService.list();
    ContextUtil.getReqAttrs().put( "beanList", inventoryReportList );
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "categorymap", CodeBean.map.get( Category.class ) );
    ContextUtil.getReqAttrs().put( "unitmap", CodeBean.map.get( Unit.class ) );
    return "/inventoryreport.jsp";
  }

}
