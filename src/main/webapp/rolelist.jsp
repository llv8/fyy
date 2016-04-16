<%@page import="com.fy.fyy.back.action.ActionModel"%>
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
			<span id="content_title">角色列表</span>
			<div>
				<ex:a id="<%=ActionModel.RoleAdd.getId() %>"
					href='addUI/${CUR_ACTION }'
					text='<%=ActionModel.RoleAdd.getName() %>' />
			</div>
		</div>


		<%@ include file="message.jsp"%>
		<div class="tab">
			<table>
				<tr>
					<th>角色名</th>
					<th>操作</th>
				</tr>
				<%
				  List<Role> list = (List<Role>)request.getAttribute( "beanList" );
				  for ( Role role : list ) {
				    pageContext.setAttribute( "role", role );
				%>
				<tr>
					<td>${role.name }</td>

					<td><div>
							<span> <ex:a id="<%=ActionModel.RoleUpdate.getId() %>"
									href='updateUI/${CUR_ACTION }?bean.id=${role.id }'
									text='<%="["+ActionModel.RoleUpdate.getName()+"]" %>' />
							</span> <span> <ex:a id="<%=ActionModel.RoleDel.getId() %>"
									href='javascript:;'
									onclick='delCfm("${CUR_ACTION }","${role.id}")'
									text='<%="["+ActionModel.RoleDel.getName()+"]" %>' /></span> <span>
								<ex:a id="<%=ActionModel.RolePermission.getId() %>"
									href='perm/RolePermission.Action?bean.roleId=${role.id}'
									text='<%="["+ActionModel.RolePermission.getName()+"]" %>' />
							</span>
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
