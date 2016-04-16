<%@page import="com.fy.fyy.back.action.ActionModel"%>
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
			<span id="content_title">物料列表</span>
			<div>
				<ex:a id="<%=ActionModel.MaterialAdd.getId() %>"
					href='addUI/${CUR_ACTION }'
					text='<%=ActionModel.MaterialAdd.getName() %>' />
			</div>
		</div>
		<%@ include file="message.jsp"%>


		<div class="tab">
			<table>
				<tr>
					<th>名称</th>
					<th>类型</th>
					<th>单位</th>
					<th>更新日期</th>
					<th>创建日期</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
				<%
				  List<Material> list = (List<Material>)request.getAttribute( "beanList" );
				  System.out.println( list );
				  for ( Material marterial : list ) {
				    pageContext.setAttribute( "marterial", marterial );
				%>
				<tr>
					<td>${marterial.name }</td>
					<td>${marterial.category.name }</td>
					<td>${marterial.unit.name }</td>
					<td>${marterial.updateDate }</td>
					<td>${marterial.createDate }</td>
					<td>${marterial.note }</td>
					<td><div>

							<span> <ex:a id="<%=ActionModel.MaterialUpdate.getId() %>"
									href='updateUI/${CUR_ACTION }?bean.id=${marterial.id }'
									text='<%="["+ActionModel.MaterialUpdate.getName()+"]" %>' />
							</span> <span> <ex:a id="<%=ActionModel.MaterialDel.getId() %>"
									href='javascript:;'
									onclick='delCfm("${CUR_ACTION }","${marterial.id}")'
									text='<%="["+ActionModel.MaterialDel.getName()+"]" %>' /></span>
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
