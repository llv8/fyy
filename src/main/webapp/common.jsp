<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<script src="js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<link rel=StyleSheet href="css/common.css" type="text/css" />
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="ex"%>
