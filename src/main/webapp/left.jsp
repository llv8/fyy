<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
<!--
-->
</style>
<script type="text/JavaScript">
<!--
	function MM_preloadImages() { //v3.0
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
//-->
</script>
</head>

<body
	onload="MM_preloadImages('images/main_26_1.gif','images/main_29_1.gif','images/main_31_1.gif')">
	<div class="left_tree">
		<table width="177" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td valign="top"><table width="100%" border="0" cellspacing="0"
						cellpadding="0" style="table-layout: fixed">
						<tr>
							<td height="26" background="images/main_21.gif">&nbsp;</td>
						</tr>
						<tr>
							<td height="80"
								style="background-image: url(images/main_23.gif); background-repeat: repeat-x;"><table
									width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="45"><div align="center">
												<a href="#"><img src="images/main_26.gif" name="Image1"
													width="40" height="40" border="0" id="Image1"
													onmouseover="MM_swapImage('Image1','','images/main_26_1.gif',1)"
													onmouseout="MM_swapImgRestore()" /></a>
											</div></td>
										<td><div align="center">
												<a href="#"><img src="images/main_28.gif" name="Image2"
													width="40" height="40" border="0" id="Image2"
													onmouseover="MM_swapImage('Image2','','images/main_29_1.gif',1)"
													onmouseout="MM_swapImgRestore()" /></a>
											</div></td>
										<td><div align="center">
												<a href="#"><img src="images/main_31.gif" name="Image3"
													width="40" height="40" border="0" id="Image3"
													onmouseover="MM_swapImage('Image3','','images/main_31_1.gif',1)"
													onmouseout="MM_swapImgRestore()" /></a>
											</div></td>
									</tr>
									<tr>
										<td height="25"><div align="center">
												<a href="#">系统管理</a>
											</div></td>
										<td><div align="center">
												<a href="#">日志管理</a>
											</div></td>
										<td><div align="center">
												<a href="#">数据分析</a>
											</div></td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td style="line-height: 4px; background: url(images/main_38.gif)">&nbsp;</td>
						</tr>
						<tr>
							<td>
								<ul>

									<li><a href="javascript:;"
										onclick="top.frames['mainFrame'].frames[1].location.href='list/User.Action'">用户管理</a></li>
									<li><a href="javascript:;"
										onclick="top.frames['mainFrame'].frames[1].location.href='list/Material.Action'">物料管理</a></li>
									<li><a href="javascript:;"
										onclick="top.frames['mainFrame'].frames[1].location.href='list/Inventory.Action'">库存管理</a></li>
								</ul>
							</td>
						</tr>

					</table></td>
			</tr>
		</table>
	</div>
</body>
</html>
