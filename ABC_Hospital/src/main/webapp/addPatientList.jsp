<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Generate Patient List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if (session.getAttribute("userEmail") == null)
		response.sendRedirect("Login.jsp");
	%>

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

		<div class="row">
			<div class="col-6 m-auto mt-5 p-3 border border-secondary rounded">
				<h3 class="text-center text-success">Add Patient Details</h3>
				<form action="<%=request.getContextPath()%>/add" method="post">

					<div class="form-group py-2">
						<label for="patientName"> Patient Name </label> <input type="text"
							class="form-control" name="patientName" placeholder="Name"
							required="required">
					</div>
					<div class="form-group py-2">
						<label for="patientAge"> Patient Age </label> <input type="text"
							class="form-control" name="patientAge" placeholder="Age"
							required="required">
					</div>
					<div class="form-group py-2">
						<label for="patientAddress"> Patient Address </label> <input
							type="text" class="form-control" name="patientAddress"
							placeholder="Address" required="required">
					</div>
					<div class="d-flex">
						<input class="btn btn-outline-success my-3" type="submit"
							value="Add Patient"> <a
							href="<%=request.getContextPath()%>/list"
							class="text-decoration-none mx-2"> <input
							class="btn btn-outline-danger my-3" type="button" value="Cancel">
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
