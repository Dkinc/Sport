<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


</head>

<body>

<div id="tooplate_wrapper">
	
    <div id="tooplate_header">
       <a href="index" id="branding"> <img src="images/logo.png"
					alt="" class="logo"> </img> </a>
					
        <div id="tooplate_menu" class="ddsmoothmenu">
            <ul>
                <li><a href="index" class="selected">Home</a></li>
                <li><a href="about.html">About</a>
                    <ul>
                        <li><a href="#">Sub menu 1</a></li>
                        <li><a href="#">Sub menu 1</a></li>
                        <li><a href="#">Sub menu 1</a></li>
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
		<div id="slider-wrapper">
        
            <div id="slider" class="nivoSlider">
			<!--  
            <c:forEach items="${mainNews}" var="n" begin="0" end="4">
            <a href=" " > <img src= "images/news/${n.getPicturesURL()}" alt="" title="${n.getTitle()}" /></a>
			</c:forEach>
            -->
                <img src="images/slider/toystory.jpg" alt="" />
                <a href="http://dev7studios.com"><img src="images/slider/up.jpg" alt="" title="This is an example of a caption" /></a>
                <img src="images/slider/walle.jpg" alt="" />
                <img src="images/slider/nemo.jpg" alt="" title="#htmlcaption" />
            </div>
            <div id="htmlcaption" class="nivo-html-caption">
                <strong>This</strong> is an example of a <em>HTML</em> caption with <a href="#">a link</a>.
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
	        <h2>Business</h2>
            <div class="newsbox col one_fourth">
            <a href="${news.getIdNews()}" > <img src= "${news.getPicturesURL()}" alt="" title="${news.getTitle()}" /></a>
            <h6><a href="${news.getIdNews()}"  >${news.getTitle()} </a></h6>
            <h6>${news.getPicturesURL()}</h6>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth no_margin_right">
            	<a href="${news.getIdNews()}" > <img src= "${news.getPicturesURL()}" alt="" title="${news.getTitle()}" /></a>
            <h6><a href="${news.getIdNews()}"  >${news.getTitle()} </a></h6>
            <h6>${news.getPicturesURL()}</h6>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth">
            	<a href="fullpost.html"><img src="images/tooplate_image_03.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth no_margin_right">
            	<a href="fullpost.html"><img src="images/tooplate_image_04.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
        </div>
        
        <div class="newscol col half no_margin_right">
	        <h2>Sport</h2>
            <div class="newsbox col one_fourth">
            	<a href="fullpost.html"><img src="images/tooplate_image_05.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth no_margin_right">
            	<a href="fullpost.html"><img src="images/tooplate_image_07.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth">
            	<a href="fullpost.html"><img src="images/tooplate_image_06.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth no_margin_right">
            	<a href="fullpost.html"><img src="images/tooplate_image_08.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
        </div>
        
        <div class="newscol col half">
	        <h2>Political</h2>
            <div class="newsbox col one_fourth">
            	<a href="fullpost.html"><img src="images/tooplate_image_10.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth no_margin_right">
            	<a href="fullpost.html"><img src="images/tooplate_image_09.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth">
            	<a href="fullpost.html"><img src="images/tooplate_image_11.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth no_margin_right">
            	<a href="fullpost.html"><img src="images/tooplate_image_12.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
        </div>
        
        <div class="newscol col half no_margin_right">
	        <h2>Technology</h2>
            <div class="newsbox col one_fourth">
            	<a href="fullpost.html"><img src="images/tooplate_image_13.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth no_margin_right">
            	<a href="fullpost.html"><img src="images/tooplate_image_14.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth">
            	<a href="fullpost.html"><img src="images/tooplate_image_15.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
            <div class="newsbox col one_fourth no_margin_right">
            	<a href="fullpost.html"><img src="images/tooplate_image_16.jpg" alt="image" /></a>
                <h6><a href="fullpost.html">Lorem ipsum dolor sit </a></h6>
              <p>Vestibulum at dolor arcu, nec convallis diam.</p>
           	  <div class="cleaner"></div>
            </div>
        </div>

		<div class="cleaner"></div>
        
        <div class="col one_third">
        	<h5>Recent Posts</h5>
            <div class="rp_pp">
                <a href="fullpost.html">Integer venenatis pharetra magna vitae ultrices</a>
                <p>Feb 23, 2048 - 20 Comments</p>
                <div class="cleaner"></div>
            </div>
            <div class="rp_pp">
                <a href="fullpost.html">Vestibulum quis nulla nunc, nec lobortis nunc.</a>
                <p>Feb 16, 2048 - 20 Comments</p>
                <div class="cleaner"></div>
            </div>
            <div class="rp_pp">
                <a href="fullpost.html">Pellentesque convallis tristique mauris.</a>
                <p>Feb 10, 2048 - 20 Comments</p>
                <div class="cleaner"></div>
            </div>
        </div>
        
        <div class="col one_third">
        	<h5>Popular Posts</h5>
            <div class="rp_pp">
                <a href="fullpost.html">Id tempor odio faucibus et proin pharetra justo.</a>
                <p>Feb 02, 2048 - 20 Comments</p>
                <div class="cleaner"></div>
            </div>
            <div class="rp_pp">
                <a href="fullpost.html">Etiam Fringilla Sapien quis mauris  vestibulum.</a>
                <p>June 03, 2048 - 20 Comments</p>
                <div class="cleaner"></div>
            </div>
            <div class="rp_pp">
                <a href="fullpost.html">Sed ac arcu ipsum, ut suscipit neque. </a>
                <p>August 08, 2048 - 20 Comments</p>
                <div class="cleaner"></div>
            </div>
        </div>
        
       
        <div class="cleaner"></div>
	</div> <!-- END of tooplate_main -->   
    
</div> <!-- END of tooplate_wrapper -->

<div id="tooplate_footer_wrapper">
	<div id="tooplate_footer">
    	 Copyright © Angel,Denis & Miroslav
    </div> <!-- END of tooplate_footer -->
</div> <!-- END of tooplate_footer_wrapper -->

</body>
</html>