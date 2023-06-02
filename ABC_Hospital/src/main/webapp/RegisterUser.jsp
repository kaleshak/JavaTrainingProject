<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div>
		<div class="container-fluid text-center"
			style="background: Lightblue; color: green;">
			<div class="row">
				<div class="col-3">
					<img src="logo.png" alt="logo" height="170px">
				</div>
				<div class="col-7 p-4">
					<h2>ABC Hospital</h2>
					<span>no.1,xyz street ,xyz Road, </span><br> <span>bengaluru,
						karnataka-560010.</span><br> <span>ph.no:9876543210. </span>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-6 m-auto mt-5 p-3 px-5 border border-secondary rounded">
					<h3 class="text-center text-success">Registration Form</h3>
					<form action="RegisterUserServlet" method="post">

						<div class="form-group  py-2">
							<label for="userName" class="form-label"> User Name </label> <input type="text"
								class="form-control" name="userName" placeholder="User Name"
								required="required">
						</div>
						<div class="form-group  py-2">
							<label for="userEmail" class="form-label"> Email </label> <input type="email"
								class="form-control" name="userEmail" placeholder="Email"
								required="required">
						</div>
						<div class="form-group  py-2">
							<label for="password" class="form-label"> Password </label> <input type="password"
								class="form-control" name="password" placeholder="Password"
								required="required">
						</div>
						<input class="btn btn-outline-primary my-3" type="submit"
							value="Register">
							<br>
							
							<span> if you register already, click here to
						<a href="Login.jsp"> Login</a></span>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>