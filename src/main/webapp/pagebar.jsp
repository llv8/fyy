
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="bb">
	<span>共${bean.pageInfo.countRecord }条纪录，当前第${bean.pageInfo.currentPageNum }/${bean.pageInfo.countPage }页，每页${bean.pageInfo.pageSize }条纪录</span>
	<div>
		<img src="images/tab/first.gif"
			onclick="location.href='<%=basePath%>list/${CUR_ACTION }?bean.pageInfo.currentPageNum=1'" />
		<img src="images/tab/back.gif"
			onclick="location.href='<%=basePath%>list/${CUR_ACTION }?bean.pageInfo.currentPageNum=${bean.pageInfo.currentPageNum-1==0?1:(bean.pageInfo.currentPageNum-1) }'" />
		<img src="images/tab/next.gif"
			onclick="location.href='<%=basePath%>list/${CUR_ACTION }?bean.pageInfo.currentPageNum=${bean.pageInfo.currentPageNum+1>bean.pageInfo.countPage?bean.pageInfo.countPage:(bean.pageInfo.currentPageNum+1) }'" />
		<img src="images/tab/last.gif"
			onclick="location.href='<%=basePath%>list/${CUR_ACTION }?bean.pageInfo.currentPageNum=${bean.pageInfo.countPage }'" />
	</div>
</div>
