<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>福圆运管理平台</title>

</head>

<body>
	<form id="loginForm" action="login/Customer.Action" method="post">
		<div id="login_main">
			<div id="login_container">
				<img src="images/login/top.gif"> <img class='login_left'
					src="images/login/left.gif">
				<div class='login_center'>
					<span class="tip_color">用户</span> <input type="text"
						name="customer.loginName"> <span class="tip_color">密码</span> <input
						type="password" name="customer.password">
					<div class="login_action">
						<img src="images/login/dl.gif" usemap="#Map">
						<map name="Map">
							<area shape="rect" coords="3,3,36,19" href="javascript:;"
								onclick="loginForm.submit()">
							<area shape="rect" coords="40,3,78,18" href="javascript:;"
								onclick="$('input').val('')">
						</map>
					</div>
				</div>
				<img class='login_right' src="images/login/right.gif">
				<div class='login_bottom'>
					<span class="tip_color">版本2016V1.0</span>
				</div>
			</div>
			<div>
	</form>
</body>
</html>
