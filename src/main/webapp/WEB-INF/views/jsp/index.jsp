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
input[type=text] {
    width: 100%;
   
}
input[type=text]:focus {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    -webkit-transition: width 0.4s ease-in-out;
    transition: width 0.4s ease-in-out;
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
				<!--  
				<form action="search" method="post">
				  <li><input type="text" name="Search" placeholder="Search.."></li>
				</form>
				-->
            </ul>
            <br style="clear: left" />
        </div> <!-- end of tooplate_menu -->
    </div> <!-- END of tooplate_header -->
    
    <div id="tooplate_main">
    
    	<form name = "search" action = "search" method = "post" onsubmit = "return validation()" align = "center">
              <input name = "search" type="text" placeholder="Search news here..." width: 100% required>
		</form>
		<br />

		<div id="slider-wrapper">
        
            <div id="slider" class="nivoSlider">
			<c:forEach items="${news}" var="n" begin="0" end="4">
                <a href="${n.getIdNews()}"><img src= <c:url value="${n.getPicturesURL()}"  />  width="620" height="300" alt="" title="${n.getTitle()}" /></a>
            </c:forEach>
            </div>

        
        </div>
        <script type="text/javascript" src="js/jquery-1.4.3.min.js"></script>
		<script type="text/javascript" src="js/jquery.nivo.slider.pack.js"></script>
        <script type="text/javascript">
        $(window).load(function() {
            $('#slider').nivoSlider();
        });
        </script>

        
        
        <div class="cleaner h30"></div>
        
        <div class="newscol col half">
	        <h2>Football</h2>

            <c:forEach items="${football}" var="n" begin="0" end="3">
            <div class="newsbox col one_fourth no_margin_right">
            <img src= <c:url value="${n.getPicturesURL()}" /> href="${n.getIdNews()}" width="75" height="75" alt="" />
            <h6><a href="${n.getIdNews()}"  >${n.getTitle()} </a></h6>
           	  <div class="cleaner"></div>
            </div>
			</c:forEach>
        </div>
        
        <div class="newscol col half no_margin_right">
	        <h2>Basketball</h2>
            <c:forEach items="${basketball}" var="n" begin="0" end="3">
            <div class="newsbox col one_fourth no_margin_right">
            <img href="${n.getIdNews()}" src= <c:url value="${n.getPicturesURL()}" /> width="75" height="75" alt="" />
            <h6><a href="${n.getIdNews()}"  >${n.getTitle()} </a></h6>
           	  <div class="cleaner"></div>
            </div>
            </c:forEach>
        </div>
        
        <div class="newscol col half">
	        <h2>Volleyball</h2>
            <c:forEach items="${volleyball}" var="n" begin="0" end="3">
            <div class="newsbox col one_fourth no_margin_right">
            <img href="${n.getIdNews()}" src= <c:url value="${n.getPicturesURL()}" /> width="75" height="75" alt="" />
            <h6><a href="${n.getIdNews()}"  >${n.getTitle()} </a></h6>
           	  <div class="cleaner"></div>
            </div>
            </c:forEach>
        </div>
        
        <div class="newscol col half no_margin_right">
	        <h2>Formula1</h2>
           <c:forEach items="${formula1}" var="n" begin="0" end="3">
            <div class="newsbox col one_fourth no_margin_right">
            <img href="${n.getIdNews()}" src= <c:url value="${n.getPicturesURL()}" /> width="75" height="75" alt="" />
            <h6><a href="${n.getIdNews()}"  >${n.getTitle()} </a></h6>
           	  <div class="cleaner"></div>
            </div>
            </c:forEach>
        </div>

		<div class="cleaner"></div>
        
        <div class="col one_third">
        	<h5>Recent Posts</h5>
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