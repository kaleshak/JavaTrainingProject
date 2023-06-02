<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

if(session.getAttribute("name")== null){
	request.getRequestDispatcher("login.jsp");
}
%>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div class="text-center ">
				<h4>User Management App</h4>

			</div>
			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="Logout"><button class="btn btn-danger ">Logout</button></a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">

		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>

			<div class="container text-left">

				<a href="new" class="btn btn-success">Add
					New User</a>
			</div>

			<br>
			<table class="table table-bordered">
				<thead>
				<colgroup>
					<col span="5">
					<col span="2" style="background-color: pink">
				</colgroup>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Country</th>
					<th>Password</th>
					<th>Actions</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${listUser}">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.country}" /></td>
							<td><c:out value="${user.password}" /></td>
							<td><a href="edit?id=<c:out value='${user.id}' />">
									<button class="btn btn-primary">Update</button>
							</a> <a href="delete?id=<c:out value='${user.id}' />">
									<button class="btn btn-danger">Delete</button>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>