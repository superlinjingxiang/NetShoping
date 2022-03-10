<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=request.getContextPath()%>/">
<title>注册页面</title>
<link rel="stylesheet" href="css/reg.css" />
<script type="text/javascript" src="js/jquery-1.x.js"></script>
<script type="text/javascript">
	function checkname() {
		var name = document.getElementById("username").value;
		$.get("AjaxRegisterServlet", {
			"user_name" : name
		}, function(data) {
			var msg = document.getElementById("msg");
			msg.innerHTML = data;
			msg.value = data;
		});

	}

	function check() {
		var pmsg = document.getElementById("pmsg");
		if (password.value != password1.value) {
			pmsg.innerHTML = "密码不一致";
			return false;
		} else if (password.value == "" || password.value == null) {
			pmsg.innerHTML = "密码为空";
			return false;
		} else if (msg.value != "用户名可以使用") {
			return false;
		}
	}
</script>
</head>
<body style="background: url(/img/mountain.png)">

	<div class="main" style="background-color: white">
		<div class="top">
			<img src="img/logo.gif" />
		</div>
		<div class="line1"></div>
		<div class="second"></div>
		<div class="third">
			<div class="head">新用户注册</div>
			<form action="registerServlet" method="post" onsubmit="return check();">
				<table>
					<tr>
						<td>用户名：</td>
						<td><input type="text" id="username" name="username" class="aa" onblur="checkname()" /></td>
						<td><span id="msg"></span></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" name="password" class="aa" id="password" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input type="password" name="password1" class="aa" id="password1" /></td>
						<td align="left"><span id="pmsg"></span></td>
					</tr>
					<tr>
						<td></td>
						<td align="left"><input type="submit" value="" style="width: 120px; height: 30px; border: 0; background: url(img/button_register.gif);" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="line2"></div>
		<div class="end"><a style="color: red;font-size: 30px" >superLin &copy; 版权所有</div></div>
	</div>
</body>
</html>
