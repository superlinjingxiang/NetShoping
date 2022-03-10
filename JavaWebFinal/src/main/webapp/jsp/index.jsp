<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath()%>/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页菜单和底部部分页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
<script type="text/javascript" src="/js/jquery-1.x.js"></script>
<script type="text/javascript" src="/js/changeH.js"></script>
</head>
<body style="background:url(/img/map.jpg)">

<h1 style="color: #ffffff"></h1>

	<div class="main">
		<div class="top">
			<img src="/img/logo.gif" />
		</div>
		<div class="line1"></div>
		<div class="second">

			<form action="articleListServlet?flag=search" method="post" target="mainframe">
				<table class="table1">
					<tr>
						<td width="220px">您好，${sessionScope.username}</td>
						<td width="220px"><a href="exitServlet">注销</a></td>
						<td width="260px"><img src="/img/new.gif" /> <a href="/jsp/tpList.jsp" onclick="hi();" target="mainframe">返回列表</a></td>
						<td width="280px"><img src="/img/addnew.gif" /> <a href="/jsp/addNewtp.jsp" target="mainframe">添加新投票</a></td>
						<td width="280px"><img src="/img/edit.gif" /> <a href="articleListServlet?del=d" onclick="hi();" target="mainframe">维护</a> <input type="hidden" name="hi" id="hi" value="0"></td>

						<td width="150px"><input name="search" type="text" style="margin-bottom: 4px;" /></td>
						<td width="50px"><input type="submit" value="" style="width: 40px; height: 23px; border: 0; background: url(/img/button_search.gif);" /></td>

					</tr>
				</table>
			</form>


		</div>

		<div class="mid">
			<iframe src="//player.bilibili.com/player.html?aid=372715590&bvid=BV1cZ4y157gB&cid=254496660&page=1?rel=0&amp;autoplay=1&amp;loop='loop'"
					width="900" height="90" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
			<iframe src="/jsp/tpList.jsp" width="900" id="win" name="mainframe" onload="Javascript:SetWinHeight(this)" frameborder="0" scrolling="no" /></iframe>
		</div>


		<div class="line2"></div>
		<div class="end"><a style="color: white;font-size: 25px" >superLin &copy; 版权所有</a></div>

	</div>
</body>
</html>