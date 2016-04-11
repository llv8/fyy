package com.fy.fyy.back.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;

public class CustomerService extends BaseService<Customer> {

	private static Logger logger = LoggerFactory.getLogger(CustomerService.class);

	public Customer login(Customer customer) {
		Customer result = null;
		List<Customer> list = getList(customer, new QuerySqlStr<Customer>() {

			@Override
			public String get(Customer bean) {
				StringBuffer sb = new StringBuffer();
				sb.append(" and loginName=?");
				sb.append(" and password=?");
				bean.getQueryParams().clear();
				bean.getQueryParams().add(bean.getLoginName());
				bean.getQueryParams().add(bean.getPassword());
				return sb.toString();
			}
		});
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else if (list.size() == 1) {
			result = list.get(0);
		} else {
			logger.error("DB has same username and password more than one.");
		}
		return result;
	}

	public List<Customer> list(Customer customer) {
		List<Customer> list = getList(customer, new QuerySqlStr<Customer>() {

			@Override
			public String get(Customer bean) {
				StringBuffer sb = new StringBuffer();
				sb.append(" and id<>?");
				bean.getQueryParams().clear();
				bean.getQueryParams().add(((Customer) ContextUtil.getSessionAttr(Constraint.LOGIN_USER)).getId());
				return sb.toString();
			}
		});
		return list;
	}

	public List<Customer> getBeanByName(Customer customer) {
		List<Customer> list = getList(customer, new QuerySqlStr<Customer>() {

			@Override
			public String get(Customer bean) {
				StringBuffer sb = new StringBuffer();
				sb.append(" and loginName=?");
				bean.getQueryParams().clear();
				bean.getQueryParams().add(bean.getLoginName());
				return sb.toString();
			}
		});
		return list;
	}

	public List<Customer> getBeanByNameIgnonreSelf(Customer customer) {
		List<Customer> list = getList(customer, new QuerySqlStr<Customer>() {

			@Override
			public String get(Customer bean) {
				StringBuffer sb = new StringBuffer();
				sb.append(" and loginName=?");
				sb.append(" and id<>?");
				bean.getQueryParams().clear();
				bean.getQueryParams().add(bean.getLoginName());
				bean.getQueryParams().add(bean.getId());
				return sb.toString();
			}
		});
		return list;
	}

}
