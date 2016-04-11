<%@page import="com.fy.fyy.back.bean.Customer"%>
<%@page import="java.util.List"%>
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
			<span id="content_title">用户列表</span>
			<div>
				<img src="images/tab/add.gif" /> <a href="addUI/${CUR_ACTION }">新增</a>

			</div>
		</div>


		<%@ include file="message.jsp"%>
		<div class="tab">
			<table>
				<tr>
					<th>登录名</th>
					<th>员工名</th>
					<th>更新日期</th>
					<th>创建日期</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
				<%
					List<Customer> list = (List<Customer>) request.getAttribute("beanList");
					for (Customer customer : list) {
						pageContext.setAttribute("customer", customer);
				%>
				<tr>
					<td>${customer.loginName }</td>
					<td>${customer.employee.userName }</td>
					<td>${customer.updateDate }</td>
					<td>${customer.createDate }</td>

					<td><div>
							<img src="images/tab/update.gif" /><span> [</span><a
								href="updateUI/${CUR_ACTION }?bean.id=${customer.id }">编辑</a><span>]</span>
						</div></td>
					<td><div>
							<span><img src="images/tab/del2.gif" /> </span><span>[</span><a
								href="javascript:;"
								onclick="if(confirm('确定要删除？')){location.href='del/${CUR_ACTION }?bean.id=${customer.id }'}">删除</a><span>]</span>
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
