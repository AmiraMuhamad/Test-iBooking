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
	<title>View Supplier</title>
</head>
<body>
<div class="container">
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">        
  		<a class="navbar-brand" class="active" href="ProductController?action=listAll">List All</a>
  		<a class="navbar-brand" href="SupplierController?action=listSupplier">Manage Supplier</a>
  		<a class="navbar-brand" href="ProductController?action=listProduct">Manage Product</a> 
  		<a class="navbar-brand" href="LoginLogoutController">Logout</a>   		
    </div>
  </nav>
</div>

<div class="container">
<br><br>
<h3>View Supplier</h3>
<br><br>
		<label for="id">Supplier ID</label>: <c:out value="${sup.sid}"/><br>
    	<label for="name">Name</label>: <c:out value="${sup.name}"/><br>    	
    	<label for="address">Address</label>: <c:out value="${sup.address}"/><br>	
      	<label for="city">City</label>: <c:out value="${sup.city}" /><br>    	
        <label for="postcode">Postcode</label>: <c:out value="${sup.postcode}"/><br>   		
        <label for="state">State</label>: <c:out value="${sup.state}" /><br>    	
        <label for="country">Country</label>: <c:out value="${sup.country}"/><br>    	        
        <label for="phoneno">Phone No</label>: <c:out value="${sup.phoneno}"/><br><br>        
        <a href="SupplierController?action=listSupplier" class="btn btn-warning">Supplier List</a>
        
</div>
</body>
</html>