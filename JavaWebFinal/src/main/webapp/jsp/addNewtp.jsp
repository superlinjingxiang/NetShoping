<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=request.getContextPath()%>/">
<title>添加新投票</title>
<link rel="stylesheet" href="css/reg.css" />
<script type="text/javascript" src="js/jquery-1.x.js"></script>
<script type="text/javascript" src="js/changeH.js"></script>
</head>
<body style="background-color: white">
	<div class="third">
		<div class="head">添加新投票</div>
		<form action="articleListServlet?flag=add" method="post" onsubmit="return check()">
			<table style="width: 480px">
				<tr>
					<td>投票内容：</td>
					<td><input type="text" name="title" class="bb" /></td>
				</tr>
				<tr>
					<td>投票类型：</td>
					<td align="left"><input type="radio" name="type" value="dan" checked="checked">单选 <input type="radio" name="type" value="duo">多选</td>
				</tr>
				<tbody id="addTr">
					<tr>
						<td>投票选项：</td>
						<td><input type="text" name="option" class="bb" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="text" name="option" class="bb" /></td>
					</tr>
				</tbody>
				<tr>
					<td></td>
					<td align="left">
						<input type="image" src="img/button_submit.gif" style="vertical-align: middle" /> &nbsp;&nbsp;&nbsp; 
						<a id="getStr">增加选项</a>&nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="jsp/tpList.jsp">取消操作</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="bottom"></div>
</body>
</html>