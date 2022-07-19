<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);
  if(session.getAttribute("sessionEmail")==null)
      response.sendRedirect("/JSCSuppliers/login.jsp");
  %>      
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">	
	<title>List All</title>	
</head>
<body>

<div class="container">
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">        
  		<a class="navbar-brand" href="LoginLogoutController">Logout</a>    		
    </div>
  </nav>
</div>
<div class="container"></div>
<div class="container">
	<%	String email = (String)session.getAttribute("sessionEmail");%>
	Welcome <b> <%= email %> </b>
</div>
</body>
</html>

