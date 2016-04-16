package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Material;

public class MaterialService extends BaseService<Material> {

	public List<Material> list(Material searchBean) {
		List<Material> list = getList(searchBean, new DefaultQuerySqlStr<Material>());
		return list;
	}
	
}
