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
                $(this).val("Reminding...");
                $(this).prop("disabled", true);
                var element = e.target;
                // who still needs to sign the document
                var remindee = (element.id === "btn1") ? "Jeff Stanton" : "Eric Wolfe";
                // if the remindee is a parent or a student
                var parentOrStudent = (remindee === "Jeff Stanton") ? "student" : "parent";
                var params = {"reminder": "Eric Wolfe", "remindee": remindee, "class": "US Government", "parentOrStudent": parentOrStudent};

                var saveData = $.ajax({
                    type: 'POST',
                    url: "remind",
                    data: params,
                    dataType: "text",
                    success: function(response) {
                        $(element).val("Reminded");
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
            <h1 class="class-name-header">US Government Students</h1>
            <div class="row header">

                <div class="col-md-6">
                    Student
                </div>
                <div class="col-md-2">
                    Parent
                </div>
            </div>

        </div>

        <div class="row students-teacher-page last-student">
            <div class="col-md-2"><i class="fa fa-graduation-cap fa-2x" aria-hidden="true"></i></div>
            <div class="col-md-5 student-teacher-page student-teacher">
                <span class="student-name">Jeff Stanton</span>
                <i class="<% out.print(signedSet.contains("Jeff Stanton") ? "fa fa-check-square-o fa-2x signed-boxed": "fa fa-square-o fa-2x signed-boxed");%>" aria-hidden="true"></i>
                <input id="btn1" class="btn btn-default teacher-btn sign <% out.print(signedSet.contains("Jeff Stanton") ? "set-gray" : "");%>" type="submit" <c:if test="${signedSet.contains(\"Jeff Stanton\")}">disabled</c:if> value="Send Reminder">
            </div>

            <i class="fa fa-male fa-2x" aria-hidden="true"></i>
            <div class="col-md-5 student-teacher-page student-teacher">
                <span class="parent-name">Eric Wolfe</span>
                <i class="<% out.print(signedSet.contains("Eric Wolfe") ? "fa fa-check-square-o fa-2x signed-boxed": "fa fa-square-o fa-2x signed-boxed");%>" aria-hidden="true"></i>
                <input id="btn2" class="btn btn-default teacher-btn sign <% out.print(signedSet.contains("Eric Wolfe") ? "set-gray" : "");%>" type="submit" <c:if test="${signedSet.contains(\"Eric Wolfe\")}">disabled</c:if> value="Send Reminder">
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
                <a href="contact.html">Contact</a>
            </li>
        </ul>
        <p class="copyright text-muted small">Copyright &copy; Sign My Syllabus. All Rights Reserved</p>
    </div>
</footer>

</body>

</html>


