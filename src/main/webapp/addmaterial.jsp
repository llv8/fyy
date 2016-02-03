
<%@page import="com.fy.fyy.back.bean.Material.Unit"%>
<%@page import="com.fy.fyy.back.bean.Material.Category"%>
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
			<span id="content_title">${bean.id!=null?"修改物料":"新增物料" }</span>
		</div>
		<div class="form">
			<form method="post" action="${bean.id!=null?"update":"add" }/${CUR_ACTION }">
				<input type="hidden" name="bean.id" value="${bean.id }" />
				<div>
					<span>用户名:</span> <input name="bean.name" value="${bean.name }" />
				</div>
				<div>
					<span>类型:</span> <select name="bean.categoryId">
						<%
						  List<Category> categorylist = (List<Category>)request.getAttribute( "categorylist" );
						  for ( Category category : categorylist ) {
						    pageContext.setAttribute( "category", category );
						%>
						<option value="${category.id }">${category.name }</option>
						<%
						  }
						%>
					</select>
				</div>
				<div>
					<span>单位:</span> <select name="bean.unitId">
						<%
						  List<Unit> unitlist = (List<Unit>)request.getAttribute( "unitlist" );
						  for ( Unit unit : unitlist ) {
						    pageContext.setAttribute( "unit", unit );
						%>
						<option value="${unit.id }">${unit.name }</option>
						<%
						  }
						%>
					</select>
				</div>
				<div>
					<span>备注:</span> <input name="bean.note" value="${bean.note }" />
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
