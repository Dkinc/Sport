<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />



<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js"></script>

<script type="text/javascript">

ddsmoothmenu.init({
	mainmenuid: "tooplate_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

</script> 
<style>
input {
    width: 100%;
}
</style>


</head>

<body>

<div id="tooplate_wrapper">
	
    <div id="tooplate_header">
       <a href="index" id="branding"> <img src="images/logo.png"
					alt="" width="200" height="36" class="logo"> </img> </a>
					
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
                <c:if test = "${sessionScope.loggedAs == null}">
                	<li><a href="register">Register</a></li>
                	<li><a href="login">Login</a></li>
				</c:if>
				<c:if test = "${sessionScope.loggedAs != null}">
                	<li><a href="profile">Profile</a></li>
                	<li><a href="logout">Logout</a></li>
				</c:if>
				<c:if test = "${sessionScope.loggedAs == 'admin'}">
                	<li><a href="addnews">AddNews</a></li>
				</c:if>
            </ul>
            <br style="clear: left" />
        </div> <!-- end of tooplate_menu -->
    </div> <!-- END of tooplate_header -->
    
    <div id="tooplate_main">
   
        <form name = "search" action = "search" method = "post" onsubmit = "return validation()" align = "center">
              <input name = search type="text" placeholder="Search news here..." width: 100% required>
		</form>
		<br />
		
        <div class="col one_third">
        	<h5>${category}</h5>
        	<c:forEach items="${news}" var="n" begin="0" end="4">
            <div class="rp_pp">
            <h6><a href="${n.getIdNews()}"  >${n.getTitle()} </a></h6>
           	  <div class="cleaner"></div>
            </div>
			</c:forEach>
            
        </div>
        
       
        <div class="cleaner"></div>
	</div> <!-- END of tooplate_main -->   
    
</div> <!-- END of tooplate_wrapper -->

<div id="tooplate_footer_wrapper">
	<div id="tooplate_footer">
    	 Copyright � Angel,Denis & Miroslav
    </div> <!-- END of tooplate_footer -->
</div> <!-- END of tooplate_footer_wrapper -->

</body>
</html>