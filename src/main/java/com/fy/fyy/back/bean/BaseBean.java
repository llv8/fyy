package com.fy.fyy.back.bean;

import java.util.ArrayList;
import java.util.List;

public class BaseBean {

	private Integer id;
	private String name;
	private List<Object> queryParams = new ArrayList<>();
	private PageInfo pageInfo = new PageInfo();

	public static <T extends BaseBean> T getInstance(Class<T> clazz, Integer id) {
		T instance = null;
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {

		}
		instance.setId(id);
		return instance;
	}

	public boolean isId(Integer ID) {
		return ID != null && ID > 0;
	}

	public BaseBean(Integer id) {
		this.id = id;
	}

	public BaseBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
  public String getName() {
    return name;
  }

  
  public void setName( String name ) {
    this.name = name;
  }

  public List<Object> getQueryParams() {
		return queryParams;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

}
