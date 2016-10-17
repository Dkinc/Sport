<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sports News</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<!--
Template 2060 Newspaper 
http://www.tooplate.com/view/2060-newspaper
-->
<link href="css/tooplate_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />
<link rel="stylesheet" href="css/login.css" />



<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js"></script>

<script type="text/javascript">
	ddsmoothmenu.init({
		mainmenuid : "tooplate_menu", //menu DIV id
		orientation : 'h', //Horizontal or vertical menu: Set to "h" or "v"
		classname : 'ddsmoothmenu', //class added to menu's outer DIV
		//customtheme: ["#1c5a80", "#18374a"],
		contentsource : "markup" //"markup" or ["container_id", "path_to_menu_file"]
	})
</script>
<script type="text/javascript">
	function validation() {
		var name = document.login.username.value;
		var password = document.login.password.value;

		if (username == "" || password == "") {
			alert("Fill fields please!");
			username.focus();
			password.focus();
			return false;
		} else {
			return true;
		}
	}
</script>

</head>

<body>

	<div id="tooplate_wrapper">

		<div id="tooplate_header">
			<a href="index" id="branding"> <img src="images/logo.png" alt=""
				width="200" height="36" class="logo"> </img>
			</a>

			<div id="tooplate_menu" class="ddsmoothmenu">
				<ul>
					<li><a href="index" class="selected">Home</a></li>
					<li><a href="">Categories</a>
                    <ul>
                        <li><a href="football">Football</a></li>
                        <li><a href="basketball">Basketball</a></li>
                        <li><a href="volleyball">Volleyball</a></li>
                        <li><a href="formula1">Formula 1</a></li>
                  </ul>
                </li>
					<c:if test="${sessionScope.loggedAs == null}">
						<li><a href="register">Register</a></li>
						<li><a href="login">Login</a></li>
					</c:if>
					<c:if test="${sessionScope.loggedAs != null}">
						<li><a href="profile">Profile</a></li>
						<li><a href="logout">Logout</a></li>
					</c:if>
				</ul>
				<br style="clear: left" />
			</div>
			<!-- end of tooplate_menu -->
		</div>
		<!-- END of tooplate_header -->

		<div id="tooplate_main">

			<div align="center">
				<form:form name = "login" action="login" onsubmit="return validation()"
					method="post" commandName="user">
					<table border="0">
						<tr>
							<td colspan="2" align="center"><h2>Login Failed</h2></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><h3>Please try again</h3></td>
						
						</tr>
						<tr>
							<td>UserName:</td>
							<td><input type="text" name="username" id="username" required/></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="password" id="passowrd" required/></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Login" /></td>
						</tr>
					</table>
				</form:form>
			</div>

		</div>
		<!-- END of tooplate_main -->

	</div>
	<!-- END of tooplate_wrapper -->

	<div id="tooplate_footer_wrapper">
		<div id="tooplate_footer">Copyright © Angel,Denis & Miroslav</div>
		<!-- END of tooplate_footer -->
	</div>
	<!-- END of tooplate_footer_wrapper -->

</body>
</html>