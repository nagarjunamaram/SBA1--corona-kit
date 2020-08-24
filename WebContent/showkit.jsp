<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<c:choose>
		<c:when test="${products == null || products.isEmpty() }">
			<p>No Products Available</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>id</th>
					<th>productName</th>
					<th>productDescription</th>	
					<th>cost</th>												
					<th>Quantity</th>
					<th>Total Cost</th>
												
				</tr>
				<c:forEach items="${products }" var="Product">
				 <c:if test="${quantitymap.get(Product.id)!=0}">
					<tr>
						<td>${Product.id }</td>
						<td>${Product.productName }</td>						
						<td>${Product.productDescription }</td>	
						<td>${Product.cost }</td>						
						<td>${quantitymap.get(Product.id)}</td>  
						<td>${totalprice.get(Product.id)}</td> 
					</tr> 
					</c:if>
				</c:forEach>
				
			</table>
			<div>
				<a href="ordersummary"><button>Place order</button></a>
			</div>
			
		</c:otherwise>
</c:choose>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>