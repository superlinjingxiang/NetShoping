<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- html基础路径设置 -->
<base href="<%=request.getContextPath()%>/">
<title>修改投票页面</title>
<link rel="stylesheet" href="css/reg.css" />
<script type="text/javascript" src="js/jquery-1.x.js"></script>
<script type="text/javascript" src="js/changeH.js"></script>
</head>
<body style="background-color: white">
	<div class="third">
		<div class="head">修改投票</div>
		<form action="changeSuccessServlet" method="post" onsubmit="return check()">
			<table style="width: 480px">
				<tr>
					<td>投票内容：</td>
					<td><input type="text" name="title" class="bb" value="${title}" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>投票类型：</td>
					<td align="left"><input type="radio" name="type" value="dan" checked="checked">单选 <input type="radio" name="type" value="duo">多选</td>
				</tr>
				<tbody id="addTr">
					<tr>
						<td>投票选项：</td>
						<td><input type="text" name="option" class="bb" value="${list[0]}" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="text" name="option" class="bb" value="${list[1]}" /></td>
					</tr>
					<c:forEach items="${ylist}" var="y">
						<tr>
							<td></td>
							<td><input type="text" class="bb" name="option" value="${y}" /></td>
							<td onClick="getDel(this)"><a>删除</a></td>
						</tr>
					</c:forEach>

				</tbody>
				<tr>
					<td></td>
					<td align="left"><input type="image" src="img/button_submit.gif" style="vertical-align: middle" /> &nbsp;&nbsp;&nbsp; <a id="getStr">增加选项</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="jsp/tpList.jsp">取消操作</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="bottom"></div>
</body>
</html>