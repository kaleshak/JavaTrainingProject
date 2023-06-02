<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav
			class="navbar navbar-expand-md navbar-dark justify-content-between"
			style="background-color: tomato">
			<h4>
				<b> User Management App </b>
			</h4>
			<a href="login.jsp"><button>Login</button></a>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<h4>Registration</h4>

				<form action="register" method="post">
					<p class="text-success">${message}</p>

					<fieldset class="form-group">
						<label>User Name</label> <input type="text" class="form-control"
							name="name" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>User Email</label> <input type="text" class="form-control"
							name="email" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>User Country</label> <input type="text"
							class="form-control" name="country" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>User Password</label> <input type="password"
							class="form-control" name="password" required="required">
					</fieldset>
					<button type="submit" class="btn btn-success">Register</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>