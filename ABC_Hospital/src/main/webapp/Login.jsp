<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	%>
	
	<div class="container-fluid text-center"
		style="background: Lightblue; color: green;">
		<div class="row">
			<div class="col-3">
				<img alt="logo" src="logo.png" height="170px">
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
			<%if (request.getAttribute("successMessage") != null) {%>
			<div class="col-6 m-auto">
				<h4 class="mt-3 text-primary">
					<%=request.getAttribute("successMessage")%>
				</h4>
			</div>
			<% } %>
			<%if (request.getAttribute("loMessage") != null) {%>
			<div class="col-6 m-auto">
				<h4 class="mt-3 text-primary">
					<%=request.getAttribute("loMessage")%>
				</h4>
			</div>
			<% } %>
		</div>
		<div class="row">
			<div class="col-6 m-auto border border-secondary rounded mt-3 p-3 px-5">
			<h3 class="text-center p-2">Login here</h3>
			
				<%if (request.getAttribute("errMessage") != null) {%>
				<p class="mt-3 text-danger">
					<%=request.getAttribute("errMessage")%>
				</p>
				<%}%>
				
				<form action="LoginServlet" method="post">
					<div class="form-group py-2">
						<label for="userEmail" class="form-label">Email address</label> <input
							type="email" class="form-control" name="userEmail" id="userEmail"
							placeholder="Enter your email" required="required">
					</div>
					<div class="form-group py-2">
						<label for="password" class="form-label">Password</label> <input
							type="password" class="form-control" name="password" id="password"
							placeholder="Enter password" required="required">
					</div>

					<button type="submit" class="btn btn-outline-success my-3">Login</button>
					<br>
				
					<span>if you are new user? <a href="RegisterUser.jsp"> Register here</a></span>
				</form>
			</div>
		</div>
	</div>
</body>
</html>