<%@page import="java.util.List"%>
<%@page import="com.fy.fyy.back.permission.ModelNode"%>
<%@page import="com.fy.fyy.back.common.Constraint"%>
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
			<div>

				<%
				  List<ModelNode> list = (List<ModelNode>)request.getSession().getAttribute( Constraint.LOGIN_PERM_ROOT );
				  if ( list != null ) {
				    for ( ModelNode modelNode : list ) {
				      pageContext.setAttribute( "modelNode", modelNode );
				%>
				<div>
					<ex:a id="${modelNode.id }" href='javascript:;'
						onclick='menuGo("${modelNode.href }")' text='${modelNode.text }' />
				</div>
				<%
				  }
				  }
				%>

			</div>
	</div>
</body>
</html>
