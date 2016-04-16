package com.fy.fyy.back.service;

import java.util.List;

import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.Inventory;
import com.fy.fyy.back.bean.Material;

public class InventoryService extends BaseService<Inventory> {

	private MaterialService materialService = new MaterialService();
	private CustomerService customerService = new CustomerService();

	public List<Inventory> list(Inventory searchBean) {
		List<Inventory> list = getList(searchBean, new DefaultQuerySqlStr<Inventory>());

		materialService.loadBeans(list, Material.class);
		customerService.loadBeans(list, Customer.class);
		return list;
	}
	
	  public List<Inventory> getBeanByMaterialId( Inventory searchBean ) {
	    List<Inventory> list = getList( searchBean, new QuerySqlStr<Inventory>() {

	      @Override
	      public String get( Inventory searchBean ) {
	        StringBuffer sb = new StringBuffer();
	        sb.append( " and materialId=?" );
	        searchBean.getQueryParams().clear();
	        searchBean.getQueryParams().add( searchBean.getMaterialId() );
	        return sb.toString();
	      }
	    } );
	    return list;
	  }
}
