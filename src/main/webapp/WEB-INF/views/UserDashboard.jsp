<%@page import="com.aurionpro.model.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.aurionpro.model.Account"%>
<%@page import="com.aurionpro.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User user = (User) session.getAttribute("user");
List<Transaction> transactions = (List<Transaction>) session.getAttribute("transactions");
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
					onclick="showSection('updateProfile')">⚙️ Update Profile</a> <a
					href="#" onclick="showSection('transactions')">📜 Transactions</a>
			</div>

			<!-- Main Content -->
			<div class="col-md-9 content">

				<!-- Home Section -->
				<section id="home" class="section">
					<div class="container">
						<div class="card shadow-sm mt-4 mx-auto"
							style="max-width: 700px; border-radius: 12px;">
							<div class="card-header bg-primary text-white">
								<h3 class="mb-0">
									Welcome,
									<%=((User) session.getAttribute("user")).getName()%>!
								</h3>
							</div>
							<div class="card-body">
								<div class="row mb-3">
									<div class="col-sm-6">
										<h6 class="text-muted">Account Number</h6>
										<p>
											<strong><%=((Account) session.getAttribute("userAccount")).getAccountNumber()%></strong>
										</p>
									</div>
									<div class="col-sm-6">
										<h6 class="text-muted">Balance</h6>
										<p>
											<strong>₹<%=String.format("%.2f", ((Account) session.getAttribute("userAccount")).getBalance())%></strong>
										</p>
									</div>
								</div>
								<hr>
								<div class="row mb-3">
									<div class="col-sm-6">
										<h6 class="text-muted">Member Since</h6>
										<p><%=new java.text.SimpleDateFormat("dd MMM yyyy")
		.format(((Account) session.getAttribute("userAccount")).getCreatedAt())%></p>
									</div>
									<div class="col-sm-6">
										<h6 class="text-muted">Account Type</h6>
										<p><%=((Account) session.getAttribute("userAccount")).getType()%></p>
									</div>
								</div>
								<hr>
								<h5 class="mb-3">Your Profile</h5>
								<div class="row">
									<div class="col-sm-6">
										<p>
											<strong>Name:</strong>
											<%=((User) session.getAttribute("user")).getName()%></p>
										<p>
											<strong>Email:</strong>
											<%=((User) session.getAttribute("user")).getEmail()%></p>
										<p>
											<strong>Mobile:</strong>
											<%=((User) session.getAttribute("user")).getMobile()%></p>
									</div>
									<div class="col-sm-6">
										<p>
											<strong>Address:</strong>
											<%=((User) session.getAttribute("user")).getAddress()%></p>
										<p>
											<strong>Aadhar No:</strong>
											<%=((User) session.getAttribute("user")).getAadharNo()%></p>
										<p>
											<strong>Member Since:</strong>
											<%=new java.text.SimpleDateFormat("dd MMM yyyy").format(((User) session.getAttribute("user")).getCreated_at())%></p>
									</div>
								</div>
							</div>
							<div class="card-footer text-end">
								<a href="#" onclick="showSection('updateProfile')"
									class="btn btn-outline-primary btn-sm">Edit Profile</a>
							</div>
						</div>
				</section>


				<!-- Send Money Section -->
				<div id="sendMoney" class="section d-none">
					<h2>Send Money</h2>
					<form action="SendMoneyController" method="post" class="mt-3">
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

				<!-- Update Profile Section -->
				<div id="updateProfile" class="section d-none">
					<h2>Update Profile</h2>
					<form action="UpdateProfileController" method="post" class="mt-3">
						<div class="mb-3">
							<label class="form-label">Full Name</label> <input type="text"
								class="form-control" name="fullName" value="<%=user.getName()%>"
								required>
						</div>
						<div class="mb-3">
							<label class="form-label">Address</label> <input type="text"
								class="form-control" name="address"
								value="<%=user.getAddress()%>" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Email</label> <input type="email"
								class="form-control" name="email" value="<%=user.getEmail()%>"
								required>
						</div>
						<div class="mb-3">
							<label class="form-label">Mobile</label> <input type="text"
								class="form-control" name="mobile" value="<%=user.getMobile()%>"
								required>
						</div>
						<button type="submit" class="btn btn-warning">Update</button>
					</form>
				</div>
				<!-- Transactions Section -->
				<div id="transactions" class="section d-none">
					<h2>Previous Transactions</h2>
					<div class="card shadow-sm mt-3">
						<div class="card-body p-0">
							<table class="table table-striped mb-0">
								<thead class="table-light">
									<tr>
										<th>Txn ID</th>
										<th>From Account</th>
										<th>To Account</th>
										<th>Amount</th>
										<th>Date</th>
									</tr>
								</thead>
								<tbody>
									<%
									if (transactions != null && !transactions.isEmpty()) {
										for (Transaction t : transactions) {
									%>
									<tr>
										<td><%=t.getId()%></td>
										<td><%=t.getFrom_account()%></td>
										<td><%=t.getTo_account()%></td>
										<td>₹<%=String.format("%.2f", t.getAmount())%></td>
										<td><%=new java.text.SimpleDateFormat("dd MMM yyyy").format(t.getCreatedAt())%></td>
									</tr>
									<%
									}
									} else {
									%>
									<tr>
										<td colspan="6" class="text-center text-muted">No
											transactions found</td>
									</tr>
									<%
									}
									%>
								</tbody>
							</table>
						</div>
					</div>
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
