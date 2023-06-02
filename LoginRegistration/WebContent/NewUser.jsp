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
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

if(session.getAttribute("name")== null){
	request.getRequestDispatcher("login.jsp");
}
%>
	<header>
		<nav
			class="navbar navbar-expand-md navbar-dark justify-content-between"
			style="background-color: tomato">
			<h4>
				<b> User Management App </b>
			</h4>
			<a href="Logout"><button>Logout</button></a>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<h4>New Registration</h4>

				<form action="insert" method="post">
					<p class="text-success">${message2}</p>

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
					<button type="submit" class="btn btn-success">Save</button>
					<a href="list"><button type="button" class="btn btn-danger">Cancel</button></a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>