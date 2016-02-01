<%@page import="com.fy.fyy.back.bean.Employee.Position"%>
<%@page import="com.fy.fyy.back.bean.Employee.Department"%>
<%@page import="com.fy.fyy.back.bean.Employee.Status"%>
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
			<span id="content_title">${employee.id!=null?"修改用户":"新增用户" }</span>
		</div>
		<div class="form">
			<form method="post" action="${employee.id!=null?"update":"add" }/Employee.Action">
				<input type="hidden" name="employee.id" value="${employee.id }" />
				<div>
					<span>用户名:</span> <input name="employee.userName"
						value="${employee.userName }" />
				</div>
				<div>
					<span>状态:</span> <select name="employee.statusId">
						<%
						  List<Status> statuslist = (List<Status>)request.getAttribute( "statuslist" );
						  for ( Status status : statuslist ) {
						    pageContext.setAttribute( "status", status );
						%>
						<option value="${status.id }">${status.name }</option>
						<%
						  }
						%>
					</select>
				</div>
				<div>
					<span>部门:</span> <select name="employee.departmentId">
						<%
						  List<Department> departmentlist = (List<Department>)request.getAttribute( "departmentlist" );
						  for ( Department department : departmentlist ) {
						    pageContext.setAttribute( "department", department );
						%>
						<option value="${department.id }">${department.name }</option>
						<%
						  }
						%>
					</select>
				</div>
				<div>
					<span>职位:</span> <select name="employee.positionId">
						<%
						  List<Position> positionlist = (List<Position>)request.getAttribute( "positionlist" );
						  for ( Position position : positionlist ) {
						    pageContext.setAttribute( "position", position );
						%>
						<option value="${position.id }">${position.name }</option>
						<%
						  }
						%>
					</select>
				</div>
				<div>
					<span>电话:</span> <input name="employee.phone"
						value="${employee.phone }" />
				</div>
				<div>
					<span>邮箱:</span> <input name="employee.email"
						value="${employee.email }" />
				</div>
				<div class="action">
					<input type="submit" value="${employee.id!=null?" 修改":"添加" }" /><input
						type="button" value="取消" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
