<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sportal</title>
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
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>
	tinymce.init({
		selector : 'textarea'
	});
</script>
<script type="text/javascript">
	function validation() {
		var title = document.addNews.title.value;
		var text = document.addNews.text.value;
		if (title == "" || text == "") {
			alert("Fill the fields!");
			title.focus();
			text.focus();
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
				class="logo"> </img>
			</a>

			<div id="tooplate_menu" class="ddsmoothmenu">
				<ul>
					<li><a href="index" class="selected">Home</a></li>
					<li><a href="about.html">About</a>
						<ul>
							<li><a href="#">Sub menu 1</a></li>
							<li><a href="#">Sub menu 1</a></li>
							<li><a href="#">Sub menu 1</a></li>
						</ul></li>
					<c:if test="${sessionScope.loggedAs == null}">
						<li><a href="register">Register</a></li>
						<li><a href="login">Login</a></li>
					</c:if>
					<c:if test="${sessionScope.loggedAs != null}">
						<li><a href="prifile">Profile</a></li>
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
				<form:form name="addNews" action="addnews"
					onsubmit="return validation()" method="post" commandName="news"
					enctype="multipart/form-data">
					<table border="0">
						<tr>
							<td colspan="2" align="center"><h2>Add news</h2></td>
						</tr>
						<tr>
							<td>Title:</td>
							<td><input type="text" name="title" required/></td>
						</tr>
						<tr>
							<td>Body:</td>
							<td><input type="text" name="text" required/></td>
						</tr>
						<tr>
							<td>Picture:</td>
							<td><input type="file" id="file" name="picturesurl"
								accept="image/*"></input></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Add News" /></td>
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