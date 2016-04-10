package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.Inventory;
import com.fy.fyy.back.bean.Inventory.Type;
import com.fy.fyy.back.bean.Material;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.service.InventoryService;
import com.fy.fyy.back.service.MaterialService;

public class InventoryAction extends BaseAction<Inventory> {
	private static Logger logger = LoggerFactory.getLogger(InventoryAction.class);
	InventoryService inventoryService = new InventoryService();

	public InventoryAction() {
		bean = new Inventory();
	}

	public String list() {
		List<Inventory> inventoryList = inventoryService.list(bean);

		ContextUtil.getReqAttrs().put("beanList", inventoryList);
		ContextUtil.getReqAttrs().put("bean", bean);
		return "/inventorylist.jsp";
	}

	public String addUI() {
		if (StrUtil.isId(bean.getId())) {
			ContextUtil.getReqAttrs().put("bean", bean);
		}
		MaterialService marterialService = new MaterialService();
		Material material = new Material();
		material.getPageInfo().setPageFlag(false);
		ContextUtil.getReqAttrs().put("materiallist", marterialService.list(material));
		ContextUtil.getReqAttrs().put("typelist", CodeBean.list.get(Type.class));
		return "/addinventory.jsp";
	}

	public String add() {

		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		bean.setCreateDate(now);
		bean.setUpdateDate(now);
		bean.setCustomerId(((Customer) ContextUtil.getSessionAttr(Constraint.LOGIN_USER)).getId());
		inventoryService.insert(bean);
		return list();
	}

	public String update() {
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		bean.setUpdateDate(now);
		bean.setCustomerId(((Customer) ContextUtil.getSessionAttr(Constraint.LOGIN_USER)).getId());
		inventoryService.update(bean);
		return list();
	}

	public String del() {
		inventoryService.delete(bean);
		return list();
	}

}
