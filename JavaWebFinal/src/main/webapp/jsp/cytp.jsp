<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath()%>/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票页面</title>
<link rel="stylesheet" href="/css/index.css" />
<script type="text/javascript" src="/js/jquery-1.x.js"></script>
<script type="text/javascript" src="/js/changeH.js"></script>

</head>
<body style="background-color: white">
	<form action="attendVoteServlet?type=${type}&title=${requestScope.title}" method="post" onsubmit="return checknull();">
		<table class="table3">
			<tr>
				<td class="d1" colspan="2"><img src="/img/title_ico.gif" style="vertical-align: middle" />&nbsp;&nbsp;参与投票</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="line3"></div>
					<p class="p11">
						<img src="/img/vote_icon.gif" /> ${requestScope.title}
					</p> <span class="p2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有 ${requestScope.optionNum} 个选项，已有 ${requestScope.voteNum} 个网友参与了投票。</span>
				</td>
			</tr>
			<c:forEach var="option" items="${optionList}">
				<%!int count = 1;%>
				<tr>
					<td width="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${count}</td>
					<td>
					   <c:choose>
							<c:when test="${type==1 }">
								<input type="checkbox" name="chbox" class="bb" value="${option}" />
							</c:when>
							<c:otherwise>
								<input type="radio" name="radio" class="bb" value="${option}" />
							</c:otherwise>
						</c:choose> <label>${option}</label>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td><input type="image" src="/img/button_vote.gif" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
