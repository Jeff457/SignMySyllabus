<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.HashSet"%>

<%
  HashSet<String> signedSet = (HashSet<String>)request.getSession().getAttribute("signedSet");
  System.out.println((signedSet == null) ? "It's null" : "It's not null");
  if (signedSet == null) {
    signedSet = new HashSet<>();
    request.getSession().setAttribute("signedSet", signedSet);
  }
  else
    for (String item : signedSet)
      System.out.println(item);

  pageContext.setAttribute("signedSet", signedSet);
  pageContext.setAttribute("referer", request.getHeader("referer"));
%>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign My Syllabus</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="css/landing-page.css" rel="stylesheet">
    
    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
    <script>
    $(document).ready(function(){
    	$(".sign").click(function(e){
    		var element = e.target;
    		if (element.id === "btn1")
    			var name = $("#name1").text();
    		else
    			var name = $("#name2").text();
    		$(this).val("Loading...");
            $(this).prop("disabled",true);
    		var query = $(".sign").val();
    		var params = { "action" : "signParent", "query" : query, "name" : "Eric Wolfe" };
    		
    		var saveData = $.ajax({
    			type: 'POST',
    			url: "DocuSignServlet",
    			data: params,
    			dataType: "text",
    			success: function(resultData) {
    			    var json = JSON.parse(resultData);
    				window.location = json.url;
    			}
    		});
        });
    });
    </script>

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
      <div class="container">
        <a class="navbar-brand" href="index.html">Sign My Syllabus</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="about.html">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="contact.html">Contact</a>
            </li>
              <li class="nav-item nav-user">
                  <i class="fa fa-user" aria-hidden="true"></i>
                  Eric
              </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Header -->
    <header class="parent-header">
      <div class="container">
        <div class="parent-message">
          <h1 class="class-name-header">Welcome, Eric</h1>
          <div class="row header">
            <div class="col-md-3"></div>
            <div class="col-md-2 parent-name-header">
              Name
            </div>
            <div class="col-md-2 parent-class-header">
              Class
            </div>
            <div class="col-md-2">
              Action Required
            </div>
          </div>

	          <div class="row students last-student">
                <i class="fa fa-graduation-cap grad-parent fa-2x" aria-hidden="true"></i>
	            <div class="col-md-4 student">
	              <span id="name2">Jeff Stanton</span>
	            </div>
                <i class="fa fa-university fa-2x university-parent" aria-hidden="true"></i>
	            <div class="col-md-4 student">
	              US Government
	            </div>
	            <div class="col-md-3">
	              <input id="btn2" class="sign btn btn-default parent-btn <% out.print(signedSet.contains("Eric Wolfe") ? "set-gray" : "");%>" type="submit" <c:if test="${signedSet.contains(\"Eric Wolfe\")}">disabled</c:if> value="Sign Syllabus">
	            </div>
	          </div>
        </div>
      </div>
    </header>

    <!-- Footer -->
    <footer>
      <div class="container">
        <ul class="list-inline">
          <li class="list-inline-item">
            <a href="index.html">Home</a>
          </li>
          <li class="footer-menu-divider list-inline-item">&sdot;</li>
          <li class="list-inline-item">
            <a href="about.html">About</a>
          </li>
          <li class="footer-menu-divider list-inline-item">&sdot;</li>
          <li class="list-inline-item">
            <a href="#services">Services</a>
          </li>
          <li class="footer-menu-divider list-inline-item">&sdot;</li>
          <li class="list-inline-item">
            <a href="contact.html">Contact</a>
          </li>
        </ul>
        <p class="copyright text-muted small">Copyright &copy; Sign My Syllabus. All Rights Reserved</p>
      </div>
    </footer>

  </body>

</html>
