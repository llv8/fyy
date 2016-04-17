<%@page import="com.fy.fyy.back.bean.Role"%>
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
			<span id="content_title">用户权限</span>
		</div>
		<%@ include file="message.jsp"%>
		<div class="form">
			<form method="post" action="roleAssignCfm/${CUR_ACTION }">
				<input type="hidden" name="bean.id" value="${bean.id }" />
				<div>
					<span>用户名:</span> <span style="text-align: left;">${bean.name }</span>
					</select>
				</div>
				<div>
					<span>角色名:</span> <select name="bean.roleId">
						<option value=""></option>
						<%
						  List<Role> rolelist = (List<Role>)request.getAttribute( "rolelist" );
						  for ( Role role : rolelist ) {
						    pageContext.setAttribute( "role", role );
						%>
						<option value="${role.id }" ${role.id==bean.roleId?"selected":"" }>${role.name}</option>
						<%
						  }
						%>
					</select>
				</div>
				<div class="action">
					<input type="submit" value="分配" /><input type="button" value="取消"
						onclick="location.href='list/${CUR_ACTION }'" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
