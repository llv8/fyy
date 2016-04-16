<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="message">
	<%
	  List<String> errors = (List<String>)request.getAttribute( "error" );
	  List<String> infos = (List<String>)request.getAttribute( "info" );
	%>

	<div class="error">

		<%
		  if ( errors != null ) {
		    for ( String message : errors ) {
		      pageContext.setAttribute( "message", message );
		%>
		<li>${message }</li>
		<%
		  }
		  }
		%>
	</div>


	<div class="info">

		<%
		  if ( infos != null ) {
		    for ( String message : infos ) {
		      pageContext.setAttribute( "message", message );
		%>
		<li>${message }</li>
		<%
		  }
		  }
		%>
	</div>

</div>