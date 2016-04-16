<%@page import="com.fy.fyy.back.action.ActionModel"%>
<%@page import="java.util.List"%>
<%@page import="com.fy.fyy.back.bean.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

</head>

<body>
	<div id="content">

		<div class="bt">
			<span id="content_title">职员列表</span>
			<div>
				<ex:a id="<%=ActionModel.EmployeeAdd.getId() %>"
					href='addUI/${CUR_ACTION }'
					text='<%=ActionModel.EmployeeAdd.getName() %>' />
			</div>
		</div>

		<%@ include file="message.jsp"%>

		<div class="tab">
			<table>
				<tr>
					<th>姓名</th>
					<th>状态</th>
					<th>部门</th>
					<th>职位</th>
					<th>更新日期</th>
					<th>创建日期</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>操作</th>
				</tr>
				<%
				  List<Employee> list = (List<Employee>)request.getAttribute( "beanList" );
				  for ( Employee employee : list ) {
				    pageContext.setAttribute( "employee", employee );
				%>
				<tr>
					<td>${employee.name }</td>
					<td>${employee.status.name }</td>
					<td>${employee.department.name }</td>
					<td>${employee.position.name }</td>
					<td>${employee.updateDate }</td>
					<td>${employee.createDate }</td>
					<td>${employee.phone }</td>
					<td>${employee.email }</td>
					<td><div>

							<span> <ex:a id="<%=ActionModel.EmployeeUpdate.getId() %>"
									href='updateUI/${CUR_ACTION }?bean.id=${employee.id }'
									text='<%="["+ActionModel.EmployeeUpdate.getName()+"]" %>' />
							</span> <span> <ex:a id="<%=ActionModel.EmployeeDel.getId() %>"
									href='javascript:;'
									onclick='delCfm("${CUR_ACTION }","${employee.id}")'
									text='<%="["+ActionModel.EmployeeDel.getName()+"]" %>' /></span>

						</div></td>
				</tr>
				<%
				  }
				%>
			</table>
		</div>

		<%@include file="pagebar.jsp"%>
	</div>
</body>
</html>
