<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.x.js"></script>
<script>
	function check() {
		if (username.value == "" || username.value == null
				|| password.value == "" || password.value == null) {
			alert("用户名或密码不能为空！")
			return false
		}
	}
</script>


		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

		<title>OneUI - Bootstrap 4 Admin Template &amp; UI Framework</title>

		<meta name="description" content="OneUI - Bootstrap 4 Admin Template &amp; UI Framework created by pixelcave and published on Themeforest">
		<meta name="author" content="pixelcave">
		<meta name="robots" content="noindex, nofollow">

		<!-- Open Graph Meta -->
		<meta property="og:title" content="OneUI - Bootstrap 4 Admin Template &amp; UI Framework">
		<meta property="og:site_name" content="OneUI">
		<meta property="og:description" content="OneUI - Bootstrap 4 Admin Template &amp; UI Framework created by pixelcave and published on Themeforest">
		<meta property="og:type" content="website">
		<meta property="og:url" content="">
		<meta property="og:image" content="">

		<!-- Icons -->
		<!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/media/favicons/favicon.png">
		<link rel="icon" type="image/png" sizes="192x192" href="${pageContext.request.contextPath}/assets/media/favicons/favicon-192x192.png">
		<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/assets/media/favicons/apple-touch-icon-180x180.png">
		<!-- END Icons -->

		<!-- Stylesheets -->
		<!-- Fonts and OneUI framework -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&amp;display=swap">
		<link rel="stylesheet" id="css-main" href="${pageContext.request.contextPath}/assets/css/oneui.min.css">

		<!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
		<!-- <link rel="stylesheet" id="css-theme" href="assets/css/themes/amethyst.min.css"> -->
		<!-- END Stylesheets -->

</head>
<body style="background:url(/img/yuhangyuan.png)">
<h1 style="color: #ffffff"></h1>

	<div class="main">
		<div class="top">
			<img src="${pageContext.request.contextPath}/img/logo.gif" />
		</div>
		<div class="mid">
			<div class="mid01">
				<img src="${pageContext.request.contextPath}/img/tupian.png" width="179px" height="154px"/>
			</div>
			<div class="mid02">
				<p class="p1">青软实训</p>
				<p class="p2">网上调查系统...</p>
			</div>
			<div class="mid03">
				<div class="head">
					用户登录 &nbsp; <img src="${pageContext.request.contextPath}/img/arrow_down.gif" /> <span style="color: red">${requestScope.msg }</span>

				</div>
				<div class="line1"></div>
				<form action="${pageContext.request.contextPath}/loginServlet" method="post" onsubmit="return check();">
					<table>
						<tr>

							<td style="font-size: medium"><i class="far fa-2x fa-address-card"></i>用户名：	</td>
							<td><input type="text" name="username" id="username"size="20px"/></td>
						</tr>
						<tr></tr>
						<tr>
							<td style="font-size: medium"><i class="far fa-2x fa-credit-card"></i>密码：</td>
							<td><input type="password" name="password" id="password" /></td>
						</tr>
						<tr></tr>
						<tr>
							<td></td>
							<td><input type="submit" value="   登录" style="font-size: large;color: white ;width: 77px; height: 32px; border: 0; background-color: #0E9A00;" /> <a href="reg.jsp">新用户注册</a></td>
						</tr>
					</table>
				</form>
			</div>

		</div>
		<div class="line2"></div>
		<div class="end"><a style="color: white;font-size: 25px" >superLin &copy; 版权所有</a></div>
	</div>
</body>
</html>
