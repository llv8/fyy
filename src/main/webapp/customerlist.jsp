<%@page import="com.fy.fyy.back.action.ActionModel"%>
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
				<ex:a id="<%=ActionModel.CustomerAdd.getId() %>"
					href='addUI/${CUR_ACTION }'
					text='<%=ActionModel.CustomerAdd.getName() %>' />
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
					<th>操作</th>
				</tr>
				<%
				  List<Customer> list = (List<Customer>)request.getAttribute( "beanList" );
				  for ( Customer customer : list ) {
				    pageContext.setAttribute( "customer", customer );
				%>
				<tr>
					<td>${customer.name }</td>
					<td>${customer.employee.name }</td>
					<td>${customer.updateDate }</td>
					<td>${customer.createDate }</td>

					<td><div>
							<span> <ex:a id="<%=ActionModel.CustomerUpdate.getId() %>"
									href='updateUI/${CUR_ACTION }?bean.id=${customer.id }'
									text='<%="["+ActionModel.CustomerUpdate.getName()+"]" %>' />
							</span> <span> <ex:a id="<%=ActionModel.CustomerDel.getId() %>"
									href='javascript:;'
									onclick='delCfm("${CUR_ACTION }","${customer.id}")'
									text='<%="["+ActionModel.CustomerDel.getName()+"]" %>' /></span> <span>
								<ex:a id="<%=ActionModel.CustomerRoleAssign.getId() %>"
									href='roleAssign/${CUR_ACTION }?bean.id=${customer.id}'
									text='<%="["+ActionModel.CustomerRoleAssign.getName()+"]" %>' />
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
