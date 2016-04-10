package com.fy.fyy.back.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.InventoryReport;
import com.fy.fyy.back.bean.Material.Category;
import com.fy.fyy.back.bean.Material.Unit;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.service.InventoryReportService;

public class InventoryReportAction extends BaseAction<InventoryReport> {
	private static Logger logger = LoggerFactory.getLogger(InventoryReportAction.class);
	InventoryReportService inventoryReportService = new InventoryReportService();

	public InventoryReportAction() {
		bean = new InventoryReport();
	}

	public String list() {
		List<InventoryReport> inventoryReportList = inventoryReportService.list();
		ContextUtil.getReqAttrs().put("beanList", inventoryReportList);
		ContextUtil.getReqAttrs().put("bean", bean);
		ContextUtil.getReqAttrs().put("categorymap", CodeBean.map.get(Category.class));
		ContextUtil.getReqAttrs().put("unitmap", CodeBean.map.get(Unit.class));
		return "/inventoryreport.jsp";
	}

}
