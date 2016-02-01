<%@page import="java.util.List"%>
<%@page import="com.fy.fyy.back.bean.Employee"%>
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
			<span id="content_title">用户列表</span>
			<div>
				<img src="images/tab/add.gif" /> <a href="addUI/Employee.Action">新增</a>

			</div>
		</div>



		<div class="tab">
			<table>
				<tr>
					<th>用户名</th>
					<th>状态</th>
					<th>部门</th>
					<th>职位</th>
					<th>更新日期</th>
					<th>创建日期</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
				<%
				  List<Employee> list = (List<Employee>)request.getAttribute( "employeeList" );
				  for ( Employee employee : list ) {
				    pageContext.setAttribute( "bean", employee );
				%>
				<tr>
					<td>${bean.userName }</td>
					<td>${bean.status.name }</td>
					<td>${bean.department.name }</td>
					<td>${bean.position.name }</td>
					<td>${bean.updateDate }</td>
					<td>${bean.createDate }</td>
					<td>${bean.phone }</td>
					<td>${bean.email }</td>
					<td><div>
							<img src="images/tab/update.gif" /><span> [</span><a
								href="addUI/Employee.Action?employee.id=${bean.id }">编辑</a><span>]</span>
						</div></td>
					<td><div>
							<span><img src="images/tab/del2.gif" /> </span><span>[</span><a
								href="del/Employee.Action?employee.id=${bean.id }">删除</a><span>]</span>
						</div></td>
				</tr>
				<%
				  }
				%>
			</table>
		</div>



		<div class="bb">
			<span>共${employee.pageInfo.countRecord }条纪录，当前第${employee.pageInfo.currentPageNum }/${employee.pageInfo.countPage }页，每页${employee.pageInfo.pageSize }条纪录</span>
			<div>
				<img src="images/tab/first.gif" onclick="location.href='<%=basePath%>list/Employee.Action?employee.pageInfo.currentPageNum=1'" />
				<img src="images/tab/back.gif" onclick="location.href='<%=basePath%>list/Employee.Action?employee.pageInfo.currentPageNum=${employee.pageInfo.currentPageNum-1==0?1:(employee.pageInfo.currentPageNum-1) }'" />
				<img src="images/tab/next.gif" onclick="location.href='<%=basePath%>list/Employee.Action?employee.pageInfo.currentPageNum=${employee.pageInfo.currentPageNum+1>employee.pageInfo.countPage?employee.pageInfo.countPage:(employee.pageInfo.currentPageNum+1) }'" />
				<img src="images/tab/last.gif" onclick="location.href='<%=basePath%>list/Employee.Action?employee.pageInfo.currentPageNum=${employee.pageInfo.countPage }'" />
				<input id="goPageNum" /><img src="images/tab/go.gif" onclick="var goPageNum=document.getElementById('goPageNum').value;isNaN(goPageNum)&&alert('请输入数字');var countPage=${employee.pageInfo.countPage };var go=goPageNum<1?1:goPageNum>countPage?countPage:goPageNum;location.href='<%=basePath%>list/Employee.Action?employee.pageInfo.currentPageNum='+go+''"/>
			</div>
		</div>
	</div>
</body>
</html>
