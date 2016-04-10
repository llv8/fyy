package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.Employee;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.service.CustomerService;
import com.fy.fyy.back.service.EmployeeService;

@ActionAnnotation(name = ActionModel.Customer)
public class CustomerAction extends BaseAction<Customer> {
	private static Log logger = Log.getInstance(CustomerAction.class);
	private static final String PASSWORD = "fyy888";
	private CustomerService customerService = new CustomerService();
	private EmployeeService employeeService = new EmployeeService();

	public CustomerAction() {
		bean = new Customer();
	}

	@RedirectAnnotation
	public String login() {
		Customer customer = customerService.login(bean);
		if (customer == null) {
			error("用户名或者密码错误");
			logger.error("user name or password is invalid -- {},{}", bean.getLoginName(), bean.getPassword());
			return Constraint.LOGIN_UI;
		} else {
			ContextUtil.getSessionAttrs().put(Constraint.LOGIN_USER, customer);
			return Constraint.INDEX_UI;
		}
	}

	@RedirectAnnotation
	public String unLogin() {
		ContextUtil.getSessionAttrs().put(Constraint.LOGIN_USER, null);
		return Constraint.LOGIN_UI;
	}

	public String loginUI() {
		return "/login.jsp";
	}

	public String indexUI() {
		return "/index.jsp";
	}

	@ActionAnnotation(name = ActionModel.CustomerQuery)
	public String list() {
		List<Customer> customerList = customerService.list(bean);
		ContextUtil.getReqAttrs().put("beanList", customerList);
		ContextUtil.getReqAttrs().put("bean", bean);
		return "/customerlist.jsp";
	}

	@ActionAnnotation(name = ActionModel.CustomerAdd)
	public String addUI() {
		ContextUtil.getReqAttrs().put("bean", bean);
		ContextUtil.getReqAttrs().put("employeelist", employeeService.list(new Employee()));
		return "/addcustomer.jsp";
	}

	@ActionAnnotation(name = ActionModel.CustomerAdd)
	public String add() {

		String loginName = bean.getLoginName();

		if (StringUtils.isEmpty(loginName)) {
			error("用户名不能为空");
			return addUI();
		}

		if (loginName.length() > 50) {
			error("用户名太长");
			return addUI();
		}

		List<Customer> list = customerService.getBeanByName(bean);
		if (!CollectionUtils.isEmpty(list) && list.size() > 0) {
			error("该用户名已存在");
			return addUI();
		}

		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		bean.setCreateDate(now);
		bean.setUpdateDate(now);
		bean.setPassword(PASSWORD);
		customerService.insert(bean);
		return list();
	}

	@ActionAnnotation(name = ActionModel.CustomerUpdate)
	public String update() {
		String loginName = bean.getLoginName();

		if (StringUtils.isEmpty(loginName)) {
			error("用户名不能为空");
			return addUI();
		}

		if (loginName.length() > 50) {
			error("用户名太长");
			return addUI();
		}

		List<Customer> list = customerService.getBeanByNameIgnonreSelf(bean);
		if (!CollectionUtils.isEmpty(list) && list.size() > 0) {
			error("该用户名已存在");
			return addUI();
		}

		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		bean.setUpdateDate(now);
		customerService.update(bean);
		return list();
	}

	public String updatePassword() {
		Customer loginUser = (Customer) ContextUtil.getSessionAttr(Constraint.LOGIN_USER);
		if (loginUser != null) {
			ContextUtil.getReqAttrs().put("bean", loginUser);
			return "/updatepassword.jsp";
		} else {
			return list();
		}
	}

	public String updatePasswordCommit() {
		Customer loginUser = (Customer) ContextUtil.getSessionAttr(Constraint.LOGIN_USER);
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		String oldPassword = (String) ContextUtil.getReqParam("oldpassword");
		String newPassword = (String) ContextUtil.getReqParam("newpassword");
		String newPassword2 = (String) ContextUtil.getReqParam("newpassword2");
		if (!loginUser.getPassword().equals(oldPassword)) {
			error("旧密码不正确");
			return updatePassword();
		}

		if (StringUtils.isEmpty(newPassword) || !newPassword.equals(newPassword2)) {
			error("新密码为空或两次密码不一致");
			return updatePassword();
		}
		loginUser.setUpdateDate(now);
		loginUser.setPassword(newPassword);
		customerService.update(bean);
		return list();
	}

	@ActionAnnotation(name = ActionModel.CustomerDel)
	public String del() {
		customerService.delete(bean);
		return list();
	}

}
