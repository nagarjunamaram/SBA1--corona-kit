<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New User</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h3>Add New User</h3>
	
	<form action="insertuser" method="post">
		<div>
			<label>User Name</label> <br>
			<input type="text" name="username" required/>
		</div>	
		<div>
			<label>Mobile Number</label> <br>
			<input type="number" name="contactNumber" required />
		</div>	
		<div>
			<label>Email          </label> <br>
			<input type="email" name="email" required />
		</div>	
		<div>
			<label>Address        </label><br>
			<textarea name ="address"rows="4" cols="20"></textarea>
		</div>	
		
	
				
		<button>SAVE</button>		
	</form>	
	<jsp:include page="footer.jsp"/>
</body>
</html>