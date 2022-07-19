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
  		<a class="navbar-brand" class="active" href="ProductController?action=listAll">List All</a>
  		<a class="navbar-brand" href="SupplierController?action=listSupplier">Manage Supplier</a>
  		<a class="navbar-brand" href="ProductController?action=listProduct">Manage Product</a> 
  		<a class="navbar-brand" href="LoginLogoutController">Logout</a>    		
    </div>
  </nav>
</div>
<div class="container"></div>
<div class="container">
	<%	String email = (String)session.getAttribute("sessionEmail");%>
	Welcome <b> <%= email %> </b>
</div>
<div class="container">
<br><br>
<h3>List of Suppliers and Products</h3>
<br><br>
<table id="list" class="table table-striped" style="width:100%">
  <tr>
    <th>Supplier Id</th>
    <th>Supplier Name</th>
    <th>Phone No</th>
    <th>Product Id</th>
    <th>Product Name</th>
    <th>Quantity</th>
    <th colspan="2">Actions</th>
  </tr>
 <c:forEach items="${prods}" var="p">
  	<tr>
    <td><c:out value="${p.supplier.sid}" /></td>
    <td><c:out value="${p.supplier.name}" /></td>
    <td><c:out value="${p.supplier.phoneno}" /></td>
    <td><c:out value="${p.pid}" /></td>
    <td><c:out value="${p.productname}" /></td>  
    <td><c:out value="${p.quantity}" /></td>   
    <td><a href="SupplierController?action=viewSupplier&sid=<c:out value="${p.supplier.sid}" />" class="btn btn-primary">View Supplier</a></td>
    <td><a href="ProductController?action=viewProduct&pid=<c:out value="${p.pid}" />" class="btn btn-primary">View Product</a></td>     
     </tr>	   
    </c:forEach>       
</table>
</div>
<script>
$(document).ready(function() {
    $('#list').DataTable();
} );
</script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>

