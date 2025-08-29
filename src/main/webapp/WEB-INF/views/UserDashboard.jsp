<%@page import="com.aurionpro.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User user = (User) session.getAttribute("user");
String fullName = (String) user.getName();
if (fullName == null) {
	response.sendRedirect("login.jsp"); // redirect to login if session expired
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard - Integro Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
}

.sidebar {
	min-height: 100vh;
	background: #343a40;
	color: white;
}

.sidebar a {
	color: white;
	text-decoration: none;
	display: block;
	padding: 10px 20px;
}

.sidebar a:hover {
	background: #495057;
}

.content {
	padding: 20px;
}
</style>
</head>
<body>
	<!-- Header -->
	<nav class="navbar navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Integro Bank</a>
			<div class="d-flex">
				<span class="navbar-text text-white me-3"> Welcome, <%=fullName%>
				</span>
				<form action="LogoutController" method="post">
					<button type="submit" class="btn btn-danger btn-sm">Logout</button>
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<!-- Sidebar -->
			<div class="col-md-3 sidebar">
				<h4 class="p-3">Dashboard</h4>
				<a href="#" onclick="showSection('home')">🏠 Home</a> <a href="#"
					onclick="showSection('sendMoney')">💸 Send Money</a> <a href="#"
					onclick="showSection('addBeneficiary')">👥 Add Beneficiary</a> <a
					href="#" onclick="showSection('updateProfile')">⚙️ Update
					Profile</a> <a href="#" onclick="showSection('transactions')">📜
					Transactions</a>
			</div>

			<!-- Main Content -->
			<div class="col-md-9 content">

				<!-- Home Section -->
				<div id="home" class="section">
					<h2>Welcome to Integro Bank</h2>
					<p>Select an option from the left to get started.</p>
				</div>

				<!-- Send Money Section -->
				<div id="sendMoney" class="section d-none">
					<h2>Send Money</h2>
					<form action="SendMoneyController" method="post" class="mt-3">
						<div class="mb-3">
							<label class="form-label">From Account</label> <input type="text"
								class="form-control" name="fromAccount" required>
						</div>
						<div class="mb-3">
							<label class="form-label">To Account</label> <input type="text"
								class="form-control" name="toAccount" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Amount</label> <input type="number"
								class="form-control" name="amount" required>
						</div>
						<button type="submit" class="btn btn-success">Send</button>
					</form>
				</div>

				<!-- Add Beneficiary Section -->
				<div id="addBeneficiary" class="section d-none">
					<h2>Add Beneficiary</h2>
					<form action="AddBeneficiaryController" method="post" class="mt-3">
						<div class="mb-3">
							<label class="form-label">Beneficiary Name</label> <input
								type="text" class="form-control" name="beneficiaryName" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Account Number</label> <input
								type="text" class="form-control" name="beneficiaryAccount"
								required>
						</div>
						<div class="mb-3">
							<label class="form-label">Bank Name</label> <input type="text"
								class="form-control" name="bankName" required>
						</div>
						<button type="submit" class="btn btn-primary">Add</button>
					</form>
				</div>

				<!-- Update Profile Section -->
				<div id="updateProfile" class="section d-none">
					<h2>Update Profile</h2>
					<form action="UpdateProfileController" method="post" class="mt-3">
						<div class="mb-3">
							<label class="form-label">Full Name</label> <input type="text"
								class="form-control" name="fullName" value="<%=fullName%>"
								required>
						</div>
						<div class="mb-3">
							<label class="form-label">Address</label> <input type="text"
								class="form-control" name="address" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Mobile</label> <input type="text"
								class="form-control" name="mobile" required>
						</div>
						<button type="submit" class="btn btn-warning">Update</button>
					</form>
				</div>

				<!-- Transactions Section -->
				<div id="transactions" class="section d-none">
					<h2>Previous Transactions</h2>
					<p>Transaction history will be fetched from backend here.</p>
				</div>
			</div>
		</div>
	</div>

	<script>
        function showSection(sectionId) {
            document.querySelectorAll('.section').forEach(sec => sec.classList.add('d-none'));
            document.getElementById(sectionId).classList.remove('d-none');
        }
    </script>
</body>
</html>
