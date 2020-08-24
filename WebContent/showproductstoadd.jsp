<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:choose>
		<c:when test="${products == null || products.isEmpty() }">
			<p>No Products Available</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>id</th>
					<th>productName</th>
					<th>cost</th>
					<th>productDescription</th>									
					<th>Action</th>
					<th>Quantity</th>
					
												
				</tr>
				<c:forEach items="${products }" var="Product">
					<tr>
						<td>${Product.id }</td>
						<td>${Product.productName }</td>
						<td>${Product.cost }</td>
						<td>${Product.productDescription }</td>									
						<td>
							
						<a href="addnewitem?id=${Product.id }">Add Item</a>
							<span>|</span>
						<a href="deleteitem?id=${Product.id }">Delete Item</a>
											
						</td>
						
						  <td>${quantitymap.get(Product.id)}</td>  
						
					</tr> 
				</c:forEach>
			</table>
			<div>
				<a href="showkit"><button>Add items to Cart</button></a>
			</div>
			
		</c:otherwise>
</c:choose>

<%-- Required View Template --%>


	
	<jsp:include page="footer.jsp"/>
</body>
</html>