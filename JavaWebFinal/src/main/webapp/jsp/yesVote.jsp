<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已投票页</title>
<link rel="stylesheet" href="/css/index.css" />
</head>
<body>
	<table class="table3">
		<tr>
			<td class="d1" colspan="2">
				<img src="/img/title_ico.gif" style="vertical-align: middle" />&nbsp;&nbsp;参与投票
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="line3"></div>
				<p class="p11">
					<img src="/img/vote_icon.gif" /> ${title }
				</p> <span class="p2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有 ${optionNum} 个选项，已有 ${voteNum} 个网友参与了投票。</span>
			</td>
		</tr>
		<%!int count = 1;%>
		<c:forEach var="slist" items="${list}">
			<tr>
				<td width="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${count}</td>
				<td><label>${slist}</label> 
				    <c:forEach var="inte" items="${sinte }">
						<c:if test="${slist==inte}">
							<label>&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<img src="img/gou.png" style="height: 15px; width: 15px; margin-top: 3px">
						</c:if>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td>
			  <a href="lookVoteServlet?title=${title}&optionNum=${optionNum}&voteNum=${voteNum}" target="mainframe">
			     <input type="image" src="img/button_view.gif" />
			  </a>
			</td>
		</tr>
	</table>
</body>
</html>