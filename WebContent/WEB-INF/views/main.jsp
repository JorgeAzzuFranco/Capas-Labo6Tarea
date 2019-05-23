<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring JPA</title>
</head>
<body>
	<h1>Students</h1>
	<table>
		<tr>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Age</td>
			<td>Status</td>
		</tr>
		<c:forEach items="${students}" var="students">
			<tr>
				<td>${students.sName}</td>
				<td>${students.lName}</td>
				<td>${students.sAge}</td>
				<td>${students.activoDelegate}</td>
				<td><button onclick="${pageContext.request.contextPath}/editar?cStudent=${student.cStudent}" >Editar</button></td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="${pageContext.request.contextPath}/buscar" method="post">
		<input type="text" name="code">
		<input type="submit">
	</form>
	
	<form action="${pageContext.request.contextPath}/save" method="post">
		<input type="submit" value="Agregar alumno">
	</form>
</body>
</html>