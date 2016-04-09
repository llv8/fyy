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
			<span id="content_title">${bean.id!=null?"修改员工信息":"新增员工信息" }</span>
		</div>
		<div class="form">
			<form method="post" action="${bean.id!=null?"update":"add" }/${CUR_ACTION }">
				<input type="hidden" name="bean.id" value="${bean.id }" />
				<div>
					<span>员工名:</span> <input name="bean.userName"
						value="${bean.userName }" />
				</div>
				<div>
					<span>状态:</span> <select name="bean.statusId">
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
					<span>部门:</span> <select name="bean.departmentId">
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
					<span>职位:</span> <select name="bean.positionId">
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
					<span>电话:</span> <input name="bean.phone" value="${bean.phone }" />
				</div>
				<div>
					<span>邮箱:</span> <input name="bean.email" value="${bean.email }" />
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
