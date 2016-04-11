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
				<img src="images/tab/add.gif" /> <a href="addUI/${CUR_ACTION }">新增</a>

			</div>
		</div>

		<%@ include file="message.jsp"%>

		<div class="tab">
			<table>
				<tr>
					<th>用户名</th>
					<th>状态</th>
					<th>部门</th>
					<th>职位</th>
					<th>更新日期</th>
					<th>创建日期</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
				<%
					List<Employee> list = (List<Employee>) request.getAttribute("beanList");
					for (Employee employee : list) {
						pageContext.setAttribute("employee", employee);
				%>
				<tr>
					<td>${employee.userName }</td>
					<td>${employee.status.name }</td>
					<td>${employee.department.name }</td>
					<td>${employee.position.name }</td>
					<td>${employee.updateDate }</td>
					<td>${employee.createDate }</td>
					<td>${employee.phone }</td>
					<td>${employee.email }</td>
					<td><div>
							<img src="images/tab/update.gif" /><span> [</span><a
								href="addUI/${CUR_ACTION }?bean.id=${employee.id }">编辑</a><span>]</span>
						</div></td>
					<td><div>
							<span><img src="images/tab/del2.gif" /> </span><span>[</span><a
								href="javascript:;"
								onclick="if(confirm('确定要删除？')){location.href='del/${CUR_ACTION }?bean.id=${employee.id }'}">删除</a><span>]</span>
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
