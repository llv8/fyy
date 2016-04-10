<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="message">
	<%
		List<String> errors = (List<String>) request.getAttribute("error");
		List<String> warns = (List<String>) request.getAttribute("warn");
		List<String> infos = (List<String>) request.getAttribute("info");
	%>
	<%
		if (errors != null) {
	%>
	<div class="error">
		<%
			for (String message : errors) {
					pageContext.setAttribute("message", message);
		%>
		<li>${message }</li>
		<%
			}
		%>
	</div>
	<%
		} else if (warns != null) {
	%>

	<div class="warn">
		<%
			for (String message : warns) {
					pageContext.setAttribute("message", message);
		%>
		<li>${message }</li>
		<%
			}
		%>
	</div>
	<%
		} else if (infos != null) {
	%>

	<div class="info">
		<%
			for (String message : infos) {
					pageContext.setAttribute("message", message);
		%>
		<li>${message }</li>
		<%
			}
		%>
	</div>
	<%
		}
	%>

</div>