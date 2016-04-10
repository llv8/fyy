package com.fy.fyy.back.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.CodeBean;
import com.fy.fyy.back.bean.Employee;
import com.fy.fyy.back.bean.Employee.Department;
import com.fy.fyy.back.bean.Employee.Position;
import com.fy.fyy.back.bean.Employee.Status;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.StrUtil;
import com.fy.fyy.back.service.EmployeeService;

@ActionAnnotation(name = ActionModel.Employee)
public class EmployeeAction extends BaseAction<Employee> {
	private static Logger logger = LoggerFactory.getLogger(EmployeeAction.class);
	private EmployeeService employeeService = new EmployeeService();

	public EmployeeAction() {
		bean = new Employee();
	}

	@ActionAnnotation(name = ActionModel.EmployeeQuery)
	public String list() {
		List<Employee> employeeList = employeeService.list(bean);
		ContextUtil.getReqAttrs().put("beanList", employeeList);
		ContextUtil.getReqAttrs().put("bean", bean);
		return "/employeelist.jsp";
	}

	@ActionAnnotation(name = ActionModel.EmployeeAdd)
	public String addUI() {
		if (StrUtil.isId(bean.getId())) {
			ContextUtil.getReqAttrs().put("bean", bean);
		}
		ContextUtil.getReqAttrs().put("departmentlist", CodeBean.list.get(Department.class));
		ContextUtil.getReqAttrs().put("positionlist", CodeBean.list.get(Position.class));
		ContextUtil.getReqAttrs().put("statuslist", CodeBean.list.get(Status.class));
		return "/addemployee.jsp";
	}

	@ActionAnnotation(name = ActionModel.EmployeeAdd)
	public String add() {
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		bean.setCreateDate(now);
		bean.setUpdateDate(now);
		employeeService.insert(bean);
		return list();
	}

	@ActionAnnotation(name = ActionModel.EmployeeUpdate)
	public String update() {
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		bean.setUpdateDate(now);
		employeeService.update(bean);
		return list();
	}

	@ActionAnnotation(name = ActionModel.EmployeeDel)
	public String del() {
		employeeService.delete(bean);
		return list();
	}
}
