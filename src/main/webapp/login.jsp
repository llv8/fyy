<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>福圆运管理平台</title>

</head>

<body>
	<form id="loginForm" action="login/User.Action" method="post">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td bgcolor="#e5f6cf">&nbsp;</td>
			</tr>
			<tr>
				<td height="608" background="images/login_03.gif"><table
						class="login_main">
						<tr>
							<td height="266" background="images/login_04.gif">&nbsp;</td>
						</tr>
						<tr>
							<td height="95"><table width="100%" border="0"
									cellspacing="0" cellpadding="0">
									<tr>
										<td width="424" height="95" background="images/login_06.gif">&nbsp;</td>
										<td width="183" background="images/login_07.gif">

											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="21%" height="30"><div align="center">
															<span class="tip_color">用户</span>
														</div></td>
													<td width="79%" height="30"><input type="text"
														name="user.loginName"
														style="height: 18px; width: 130px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432;"></td>
												</tr>
												<tr>
													<td height="30"><div align="center">
															<span class="tip_color">密码</span>
														</div></td>
													<td height="30"><input type="password"
														name="user.password"
														style="height: 18px; width: 130px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432;"></td>
												</tr>
												<tr>
													<td height="30">&nbsp;</td>
													<td height="30"><img src="images/dl.gif" width="81"
														height="22" border="0" usemap="#Map"></td>
												</tr>
											</table>

										</td>
										<td width="255" background="images/login_08.gif">&nbsp;</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td height="247" class="version" valign="top"
								background="images/login_09.gif"><div class="login_version">版本
									2016V1.0</div></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td bgcolor="#a2d962">&nbsp;</td>
			</tr>
		</table>

		<map name="Map">
			<area shape="rect" coords="3,3,36,19" href="javascript:;"
				onclick="loginForm.submit()">
			<area shape="rect" coords="40,3,78,18" href="javascript:;"
				onclick="$('input').val('')">
		</map>
	</form>
</body>
</html>
