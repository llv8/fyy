<%@page import="com.fy.fyy.back.bean.InventoryReport"%>
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
			<span id="content_title">总库存统计</span>
		</div>

		<%@ include file="message.jsp"%>
		<div class="tab">
			<table>
				<tr>
					<th>名称</th>
					<th>入库</th>
					<th>出库</th>
					<th>剩余</th>
				</tr>
				<%
					List<InventoryReport> list = (List<InventoryReport>) request.getAttribute("beanList");
					for (InventoryReport inventoryReport : list) {
						pageContext.setAttribute("inventoryReport", inventoryReport);
				%>
				<tr>
					<td>${inventoryReport.name }--${ categorymap[inventoryReport.categoryId] }</td>
					<td>${inventoryReport.inNum }(${unitmap[inventoryReport.unitId]})</td>
					<td>${inventoryReport.outNum }(${unitmap[inventoryReport.unitId]})</td>
					<td>${inventoryReport.leftNum }(${unitmap[inventoryReport.unitId]})</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>
