<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.patient.list.Category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Patient List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.1/css/fontawesome.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		$.ajax({url : 'DynamicDataServlet',
				type : 'GET',
				dataType : 'json',
				success : function(data) {
				if (data && data.length > 0) {
				var options = '<option value="">Select</option>';
					for (var i = 0; i < data.length; i++) {
						options += '<option value="' + data[i].id + '">'+ data[i].name+ '</option>';
						}
			$('#dropdown').html(options);
						}
					},
				});
			});
</script>

</head>
<body>

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if (session.getAttribute("userEmail") == null)
		response.sendRedirect("Login.jsp");
	%>

	<div class="container-fluid text-center"
		style="background: Lightblue; color: green;">
		<div class="row">
			<div class="col-3">
				<img src="logo.png" alt="logo" height="170px">
			</div>
			<div class="col-7 p-4">
				<h2>ABC Hospital</h2>
				<span>no.1,xyz street ,xyz Road, </span><br> <span>bengaluru,
					karnataka-560010.</span><br> <span> ph.no:9876543210. </span>
			</div>
		</div>
	</div>
	<%if (request.getAttribute("welcomeMessage") != null) {%>
		<h4 class="text-center mt-3 text-primary">
			<%=request.getAttribute("welcomeMessage")%>
		</h4>
	<%}%>
	<div
		class="w-75 m-auto my-3 d-flex justify-content-between align-items-center">
		<a href="addPatientList.jsp" class="text-decoration-none">
			<button class="btn btn-outline-success">Add Patient</button>
		</a>
		<h3 class="text-success">Patient List</h3>
		<a href="Logout" class="text-decoration-none">
			<button class="btn btn-outline-danger">LogOut</button>
		</a>
	</div>

	<div class="w-75 text-center m-auto">

		<table class="table table-striped table-success">
			<thead class="thead-success">
				<tr>
					<th>ID</th>
					<th>Patient Name</th>
					<th>Patient Age</th>
					<th>Patient Address</th>
					<th colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="patient" items="${patientList}">
					<tr>
						<td>${patient.patientID}</td>
						<td>${patient.patientName}</td>
						<td>${patient.patientAge}</td>
						<td>${patient.patientAddress}</td>

						<td class="text-center" style="width: 100px"><a
							href="edit?patientID=${patient.patientID}"
							class="btn btn-outline-primary">Update</a></td>
						<td class="text-center" style="width: 100px"><a
							href="delete?patientID=${patient.patientID}"
							onclick="return confirm('Are you sure you want to delete this patient?');"
							class="btn btn-outline-primary">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<hr>
	<div
		class="text-center m-auto my-5 p-3 w-75 border border-secondary rounded">
		<h5 style="color: green;">To get the Patient list Select the file
			type and click Generate.</h5>

		<form method="POST" action="ServletControl"
			class="d-flex justify-content-center align-items-center">

			<select id="dropdown" name="fileType" class="form-select w-50 m-3">

			</select> <input class="btn btn-outline-success" type="submit"
				value="Generate">
		</form>
	</div>

</body>
</html>
