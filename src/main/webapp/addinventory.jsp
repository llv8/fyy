
<%@page import="com.fy.fyy.back.bean.Inventory.Type"%>
<%@page import="com.fy.fyy.back.bean.Material"%>
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
			<span id="content_title">${bean.id>0?"修改库存":"新增库存" }</span>
		</div>
		<%@ include file="message.jsp" %>
		<div class="form">
			<form method="post" action="${bean.id>0?"update":"add" }/${CUR_ACTION }">
				<input type="hidden" name="bean.id" value="${bean.id }" />
				<div>
					<span>名称:</span> <select name="bean.materialId">
						<%
						  List<Material> materiallist = (List<Material>)request.getAttribute( "materiallist" );
						  for ( Material material : materiallist ) {
						    pageContext.setAttribute( "material", material );
						%>
						<option ${material.id==bean.materialId?"selected":"" } value="${material.id }">${material.name }</option>
						<%
						  }
						%>
					</select>
				</div>
				<div>
					<span>类型:</span> <select name="bean.typeId">
						<%
						  List<Type> typelist = (List<Type>)request.getAttribute( "typelist" );
						  for ( Type type : typelist ) {
						    pageContext.setAttribute( "type", type );
						%>
						<option ${type.id==bean.typeId?"selected":"" } value="${type.id }">${type.name }</option>
						<%
						  }
						%>
					</select>
				</div>
				<div>
					<span>数量:</span> <input name="bean.num" value="${bean.num }" />
				</div>
				<div>
					<span>备注:</span> <input name="bean.note" value="${bean.note }" />
				</div>
				<div class="action">
					<input type="submit" value="${bean.id>0?" 修改":"添加" }" /><input
						type="button" value="取消"
						onclick="location.href='list/${CUR_ACTION }'" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
