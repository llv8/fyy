package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Material;
import com.fy.fyy.back.bean.Material.Category;
import com.fy.fyy.back.bean.Material.Unit;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.service.MaterialService;

public class PermissionAction extends BaseAction<Material> {
	private static Logger logger = LoggerFactory.getLogger(PermissionAction.class);
	private MaterialService materialService = new MaterialService();

	public PermissionAction() {
		bean = new Material();
	}

	public String list() {
		List<Material> materialList = materialService.list(bean);
		ContextUtil.getReqAttrs().put("beanList", materialList);
		ContextUtil.getReqAttrs().put("bean", bean);
		return "/materiallist.jsp";
	}

	public String addUI() {
		if (StrUtil.isId(bean.getId())) {
			ContextUtil.getReqAttrs().put("bean", bean);
		}
		ContextUtil.getReqAttrs().put("categorylist", CodeBean.list.get(Category.class));
		ContextUtil.getReqAttrs().put("unitlist", CodeBean.list.get(Unit.class));
		return "/addmaterial.jsp";
	}

	public String add() {
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		bean.setCreateDate(now);
		bean.setUpdateDate(now);
		materialService.insert(bean);
		return list();
	}

	public String update() {
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		bean.setUpdateDate(now);
		materialService.update(bean);
		return list();
	}

	public String del() {
		materialService.delete(bean);
		return list();
	}

}
