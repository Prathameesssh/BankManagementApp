<%@page import="com.aurionpro.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
}

.profile-card {
	max-width: 600px;
	margin: 40px auto;
	border-radius: 14px;
	box-shadow: 0 3px 16px rgba(0, 0, 0, 0.09);
}
</style>
</head>
<body>
	<div class="container">
		<div class="profile-card card p-4">
			<h3 class="card-title mb-3 text-center">User Profile</h3>
			<%
			User user = (User) request.getAttribute("profileUser");
			if (user != null) {
			%>
			<table class="table">
				<tr>
					<th>User ID</th>
					<td><%=user.getId()%></td>
				</tr>
				<tr>
					<th>Name</th>
					<td><%=user.getName()%></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><%=user.getEmail()%></td>
				</tr>
				<tr>
					<th>Mobile</th>
					<td><%=user.getMobile()%></td>
				</tr>
				<tr>
					<th>Address</th>
					<td><%=user.getAddress()%></td>
				</tr>
				<!-- Add any other fields as required -->
			</table>
			<div class="text-center mt-4">
				<form action="VerifyAccountController" method="post"
					style="display: inline;">
					<input type="hidden" name="userId" value="<%=user.getId()%>">
					<button type="submit" class="btn btn-success">Approve</button>
				</form>
				<form action="RejectAccountController" method="post"
					style="display: inline;">
					<input type="hidden" name="userId" value="<%=user.getId()%>">
					<button type="submit" class="btn btn-danger">Reject</button>
				</form>
			</div>

			<%
			} else {
			%>
			<div class="alert alert-warning text-center">User not found or
				invalid userId.</div>
			<%
			}
			%>
		</div>
		<div class="text-center mt-3">
			<a href="AdminDashboardController" class="btn btn-secondary">Return to Admin Dashboard</a>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
