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
			<span id="content_title">${bean.id!=null?"修改用户信息":"新增用户信息" }</span>
		</div>
		<%@ include file="message.jsp" %>
		<div class="form">
			<form method="post" action="${bean.id!=null?"update":"add" }/${CUR_ACTION }">
				<input type="hidden" name="bean.id" value="${bean.id }" />
				<div>
					<span>登录名:</span> <input name="bean.loginName"
						value="${bean.loginName }" />
				</div>
				<div>
					<span>员工:</span> <select name="bean.employeeId">
						<%
						  List<Employee> employeelist = (List<Employee>)request.getAttribute( "employeelist" );
						  for ( Employee employee : employeelist ) {
						    pageContext.setAttribute( "employee", employee );
						%>
						<option value="${employee.id }">${employee.userName }</option>
						<%
						  }
						%>
					</select>
				</div>
				<div class="action">
					<input type="submit" value="${bean.id!=null?" 修改":"添加" }" /><input
						type="button" value="取消"
						onclick="location.href='list/${CUR_ACTION }'" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
