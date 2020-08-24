<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
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
								
				</tr>
				<c:forEach items="${products }" var="Product">
					<tr>
						<td>${Product.id }</td>
						<td>${Product.productName }</td>
						<td>${Product.cost }</td>
						<td>${Product.productDescription }</td>									
						<td>
							<a href="deleteproduct?id=${Product.id }">DELETE</a>
							<span>|</span>
							<a href="editproduct?id=${Product.id }">EDIT</a>
						</td>
					</tr> 
				</c:forEach>
			</table>
		</c:otherwise>
</c:choose>

<%-- Required View Template --%>


	
	<jsp:include page="footer.jsp"/>
</body>
</html>