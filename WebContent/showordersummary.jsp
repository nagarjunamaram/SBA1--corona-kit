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
<h1>User details</h1>
<div>
			<span>User Name:</span>
			<span>${username}</span> 
		</div>	
		<div>
			<span>Mobile Number:</span>
			<span>${contactNumber}</span> 
		
		</div>	
		<div>
			<span>Email:          </span>
			<span>${email}</span> 
		
		</div>	
		<div>
			<span>Address:       </span>
			<span>${address}</span> 
		
		</div>	

<c:choose>

		<c:when test="${products == null || products.isEmpty() }">
			<p>No Products Available</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>					
					<th>productName</th>
					<th>productDescription</th>	
					<th>cost</th>												
					<th>Quantity</th>
					<th>Total Cost</th>
												
				</tr>
				<c:forEach items="${products }" var="Product">
				 <c:if test="${quantitymap.get(Product.id)!=0}">
					<tr>						
						<td>${Product.productName }</td>						
						<td>${Product.productDescription }</td>	
						<td>${Product.cost }</td>						
						<td>${quantitymap.get(Product.id)}</td>  
						<td>${totalprice.get(Product.id)}</td> 
					</tr> 
					</c:if>
				</c:forEach>
				
			</table>
			<h1>Total amount:</h1>
			<h1>${totalamount}</h1>
			
		</c:otherwise>
</c:choose>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>