<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<style type="text/css">
footer {
  background-color: tomato;
  padding: 10px;
  text-align: center;
  color: white;
}
</style>
</head>

<body>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
%>


	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">

		<ul class="navbar-nav navbar-collapse justify-content-end">
		</ul>
	</nav>
	<p class="alert text-primary center">${message}</p>
	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<h1>User Login Form</h1>
		<p class="alert text-danger center">${INVALID}</p>
		<form action="login" method="post">

			<div class="form-group">
				<label for="uname">User Name:</label> <input type="text"
					class="form-control" id="username" placeholder="User Name"
					name="name" required>
			</div>

			<div class="form-group">
				<label for="uname">Password:</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password" required>
			</div>

			<button type="submit" class="btn btn-primary">Login</button>


			<a href="Registration.jsp" class="text-decoration-none">
			
			<button type="button" class="btn btn-success">Sign up</button>
			</a>
		</form>
	</div>
	<br>
	<footer>
		<p>
			<b>Thank you for Visiting</b>
		</p>
	</footer>

</body>
</html>