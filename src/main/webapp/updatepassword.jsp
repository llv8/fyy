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
			<span id="content_title">修改密码</span>
		</div>
		<%@ include file="message.jsp"%>
		<div class="form">
			<form method="post" action="updatePasswordCommit/${CUR_ACTION }">
				<div>
					<span>旧密码:</span> <input type="password" name="oldpassword"
						value="${oldpassword }" />
				</div>
				<div>
					<span>新密码:</span> <input type="password" name="newpassword"
						value="${newpassword }" />
				</div>
				<div>
					<span>确认新密码:</span> <input type="password" name="newpassword2"
						value="${newpassword2 }" />
				</div>
				<div class="action">
					<input type="submit" value="修改" /><input type="button" value="取消"
						onclick="location.href='welcome.jsp'" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
