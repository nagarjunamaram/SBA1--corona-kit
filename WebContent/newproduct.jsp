<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h3>${isNew?'New Product':'Edit Product' }</h3>
	
	<form action="${isNew?'insertproduct':'updateproduct' }" method="post">
		<div>
			<label>Product ID</label>
			<input type="number" name="pid" value="${Product.id }" required ${isNew?'':'readonly' }/>
		</div>	
		<div>
			<label>Product Name</label>
			<input type="text" name="pname" value="${Product.productName }" required />
		</div>	
		<div>
			<label>Cost</label>
			<input type="text" name="cost" value="${Product.cost }" required />
		</div>	
		<div>
			<label>Product Description</label>
			<input type="text" name="pdescription" value="${Product.productDescription }" required />
		</div>	
				
		<button>SAVE</button>		
	</form>	
	<jsp:include page="footer.jsp"/>
</body>
</html>