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
	<div class="left_tree">
		<img src="images/left/top.gif">
			<ul>

				<li><a href="javascript:;"
					onclick="top.frames['mainFrame'].frames[1].location.href='list/Employee.Action'">员工管理</a></li>
				<li><a href="javascript:;"
					onclick="top.frames['mainFrame'].frames[1].location.href='list/Material.Action'">物料管理</a></li>
				<li><a href="javascript:;"
					onclick="top.frames['mainFrame'].frames[1].location.href='list/Inventory.Action'">库存管理</a></li>
				<li><a href="javascript:;"
					onclick="top.frames['mainFrame'].frames[1].location.href='list/InventoryReport.Action'">库存统计</a></li>
			</ul>
	</div>
</body>
</html>
