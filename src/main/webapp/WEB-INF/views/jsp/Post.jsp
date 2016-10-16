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
<script type = "text/javascript">
function validate() {
	var text = document.comments.text.value;
	if (text=="") {
		alert("Can't add empty comments!");
		text.focus();
		return false;
	}
	return true;
}
</script>

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
            </ul>
            <br style="clear: left" />
        </div> <!-- end of tooplate_menu -->
    </div> <!-- END of tooplate_header -->
    
    <div id="tooplate_main">
		<div id="content" class="float_l">
        	<div class="post">
            	
            <h2>${news.getTitle()}</h2>
           
            <img src= <c:url value="${news.getPicturesURL()}" /> alt="" width="500" />   
            <div class="meta">
                <span class="admin">Admin</span><span class="tag"> ${news.getNumberOfReads()}</span>
                <div class="cleaner"></div>
            </div> 
            
            <p>${news.getText()}</p>
</div>
        
           <div class="cleaner h40"></div>
          
            <h4>Comments</h4>
                        
              	<div id="comment_section">
                <ol class="comments first_level">
                        <c:forEach items="${comments}" var="c" begin="0">
	                        <li>
	                            <div class="comment_box commentbox1">  
	                                <div class="comment_text">
	                                    <div class="comment_author">${c.getUsername()} <span class="date">${c.getDateAndTime()}</span> </div>
	                                    <wbr>${c.getText()}</wbr>
	                                    <p>Likes : ${c.getLikes()}</p>
	                                    <p>Dislikes : ${c.getDislikes()}</p>
	                                    <div>
		                                    <form action="likeComment" method="post">
													<input type="hidden" name="ID" value ="${c.getIdComment()}"/>
													<input type="hidden" name="NewsId" value ="${news.getIdNews()}"/>
													<input type="submit" class="submit_btn" name="submit" id="submit" value="Like" />
											</form>
											<form action="dislikeComment" method="post">
												    <input type="hidden" name="ID" value = "${c.getIdComment()}"/>
												    <input type="hidden" name="NewsId" value ="${news.getIdNews()}"/>
												    <input type="submit" class="submit_btn" name="submit" id="submit" value="Dislike" />
											</form>
										</div>
	                                </div>
	                                <div class="cleaner"></div>
	                            </div>                        
	                            
	                        </li>
						</c:forEach>
                  </ol>

                <div class="cleaner h20"></div>
	                <div id="comment_form">
                <c:if test = "${sessionScope.loggedAs != null}">
	                    <h4>Leave your comment</h4>
	                    
	                    <form:form action="addComment" name = "comments" onsubmit = "return validate()" method="post" commandName="comment">
	                        
	                        <div class="form_row">
	                            <label>Your comment</label><br />
	                            <form:textarea path="text" />
	                            <form:hidden path="idNews" value = "${news.getIdNews()}" />
	                        </div>
	
	                        <input type="submit" class="submit_btn" name="submit" id="submit" value="Comment" />
	                    </form:form>
                    
                </c:if>
                <c:if test = "${sessionScope.loggedAs == null}">
                <h4>Please Log in or Register to leave a comment</h4>
                </c:if>
            	</div>
            	
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