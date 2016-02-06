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
				<img src="images/tab/add.gif" /> <a href="addUI/${CUR_ACTION }">新增</a>

			</div>
		</div>



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
					<th>编辑</th>
					<th>删除</th>
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
					<td>${inventory.customer.loginName }</td>
					<td>${inventory.updateDate }</td>
					<td>${inventory.createDate }</td>
					<td>${inventory.note }</td>
					<td><div>
							<img src="images/tab/update.gif" /><span> [</span><a
								href="addUI/${CUR_ACTION }?bean.id=${inventory.id }">编辑</a><span>]</span>
						</div></td>
					<td><div>
							<span><img src="images/tab/del2.gif" /> </span><span>[</span><a
								href="del/${CUR_ACTION }?bean.id=${inventory.id }">删除</a><span>]</span>
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
