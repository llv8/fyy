<%@page import="com.fy.fyy.back.action.ActionModel"%>
<%@page import="com.fy.fyy.back.bean.Inventory"%>
<%@page import="com.fy.fyy.back.bean.Material"%>
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
			<span id="content_title">库存列表</span>
			<div>
				<ex:a id="<%=ActionModel.InventoryAdd.getId() %>"
					href='addUI/${CUR_ACTION }'
					text='<%=ActionModel.InventoryAdd.getName() %>' />
			</div>
		</div>
		<%@ include file="message.jsp"%>


		<div class="tab">
			<table>
				<tr>
					<th>名称</th>
					<th>类型</th>
					<th>数量</th>
					<th>操作员</th>
					<th>更新日期</th>
					<th>创建日期</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
				<%
				  List<Inventory> list = (List<Inventory>)request.getAttribute( "beanList" );
				  for ( Inventory inventory : list ) {
				    pageContext.setAttribute( "inventory", inventory );
				%>
				<tr>
					<td>${inventory.material.name }</td>
					<td>${inventory.type.name }</td>
					<td>${inventory.num }</td>
					<td>${inventory.customer.name }</td>
					<td>${inventory.updateDate }</td>
					<td>${inventory.createDate }</td>
					<td>${inventory.note }</td>
					<td><div>
							<span> <ex:a
									id="<%=ActionModel.InventoryUpdate.getId() %>"
									href='updateUI/${CUR_ACTION }?bean.id=${inventory.id }'
									text='<%="["+ActionModel.InventoryUpdate.getName()+"]" %>' />
							</span> <span> <ex:a id="<%=ActionModel.InventoryDel.getId() %>"
									href='javascript:;'
									onclick='delCfm("${CUR_ACTION }","${inventory.id}")'
									text='<%="["+ActionModel.InventoryDel.getName()+"]" %>' /></span>

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
