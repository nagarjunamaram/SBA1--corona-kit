<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<header>
	<h1>Corona kit</h1>
	<hr />
	
	<nav>
		<a href="index.jsp">Home</a>	
		 <c:if test="${logincheck}">
		 <a href="logout">Logout</a>
		  </c:if>	
	</nav>
	
	<hr />
</header>