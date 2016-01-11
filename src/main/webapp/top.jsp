<%@page import="org.apache.commons.lang3.time.DateFormatUtils"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.commons.lang3.time.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>

<body>
	<img class="top_top" src="images/top/top.gif">
		<div class="top_main">
			<img src="images/top/center1.gif">
				<div>
					<img src="images/top/center2.gif">
						<div id="customer">
							<span class="tip_color">${ loginUser.userName }</span>
						</div>
				</div> <img src="images/top/center4.gif">
					<div class="top_action">
						<img src="images/top/center5.gif" usemap="#Map">
					</div>
					<div id="top_time">
						<span class="tip_color"><%=DateFormatUtils.format( new Date(), "yyyy-MM-dd" )%></span>
					</div>
		</div> <img class="top_bottom" src="images/top/bottom.gif"> <map
				name="Map" id="Map">
				<area shape="rect" coords="3,1,49,22" href="javascript:;"
					onclick="top.location.href='indexUI/User.Action'" />
				<area shape="rect" coords="52,2,95,21" href="javascript:;"
					onclick="top.frames['mainFrame'].frames[1].history.back()" />
				<area shape="rect" coords="102,2,144,21" href="javascript:;"
					onclick="top.frames['mainFrame'].frames[1].history.go()" />
				<area shape="rect" coords="150,1,197,22" href="javascript:;"
					onclick="top.location.href='indexUI/User.Action'" />
				<area shape="rect" coords="210,2,304,20" href="javascript:;"
					onclick="top.location.href='unLogin/User.Action'" />
				<area shape="rect" coords="314,1,361,23" href="javascript:;"
					onclick="top.location.href='unLogin/User.Action'" />
			</map>
</body>
</html>
