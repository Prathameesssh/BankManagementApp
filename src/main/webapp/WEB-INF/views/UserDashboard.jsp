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

				<!-- Update Profile Section -->
				<div id="sendMoney" class="section d-none">
					<h2>Send Money</h2>

					<%
					String sendError = (String) session.getAttribute("error");
					String sendSuccess = (String) session.getAttribute("success");
					if (sendError != null) {
					%>
					<div class="alert alert-danger"><%=sendError%></div>
					<%
					session.removeAttribute("sendError");
					} else if (sendSuccess != null) {
					%>
					<div class="alert alert-success"><%=sendSuccess%></div>
					<%
					session.removeAttribute("sendSuccess");
					}
					%>

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
					<form action="UpdateProfileController" method="post" class="mt-3"
						onsubmit="return validateProfileForm()">
						<div class="mb-3">
							<label class="form-label">Full Name</label> <input type="text"
								class="form-control" id="fullName" name="fullName"
								value="<%=user.getName()%>" required> <small
								id="nameError" class="text-danger"></small>
						</div>
						<div class="mb-3">
							<label class="form-label">Address</label> <input type="text"
								class="form-control" id="address" name="address"
								value="<%=user.getAddress()%>" required> <small
								id="addressError" class="text-danger"></small>
						</div>
						<div class="mb-3">
							<label class="form-label">Email</label> <input type="email"
								class="form-control" id="email" name="email"
								value="<%=user.getEmail()%>" required> <small
								id="emailError" class="text-danger"></small>
						</div>
						<div class="mb-3">
							<label class="form-label">Mobile</label> <input type="text"
								class="form-control" id="mobile" name="mobile"
								value="<%=user.getMobile()%>" required> <small
								id="mobileError" class="text-danger"></small>
						</div>
						<button type="submit" class="btn btn-warning">Update</button>
					</form>
				</div>

				<script>
function validateProfileForm() {
    let valid = true;

    // Reset error messages
    document.getElementById("nameError").innerText = "";
    document.getElementById("addressError").innerText = "";
    document.getElementById("emailError").innerText = "";
    document.getElementById("mobileError").innerText = "";

    // Full Name validation
    const fullName = document.getElementById("fullName").value.trim();
    if (fullName.length < 3) {
        document.getElementById("nameError").innerText = "Name must be at least 3 characters long.";
        valid = false;
    }

    // Address validation
    const address = document.getElementById("address").value.trim();
    if (address.length < 5) {
        document.getElementById("addressError").innerText = "Address must be at least 5 characters long.";
        valid = false;
    }

    // Email validation (already handled by type="email", but add extra check)
    const email = document.getElementById("email").value.trim();
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        document.getElementById("emailError").innerText = "Please enter a valid email.";
        valid = false;
    }

    // Mobile validation (10 digits only)
    const mobile = document.getElementById("mobile").value.trim();
    const mobileRegex = /^[0-9]{10}$/;
    if (!mobileRegex.test(mobile)) {
        document.getElementById("mobileError").innerText = "Mobile number must be 10 digits.";
        valid = false;
    }

    return valid; // if false, form won’t submit
}
</script>

				<!-- Transactions Section -->
				<div id="transactions" class="section d-none">
					<h2>Previous Transactions</h2>

					<!-- Filter UI -->
					<div class="mb-3 d-flex gap-2">
						<select id="filterColumn" class="form-select w-auto">
							<option value="0">Txn ID</option>
							<option value="1">From Account</option>
							<option value="2">To Account</option>
							<option value="3">Amount</option>
							<option value="4">Date</option>
						</select> <input type="text" id="filterInput" class="form-control w-auto"
							placeholder="Search transactions...">
					</div>

					<div class="card shadow-sm mt-3">
						<div class="card-body p-0">
							<table id="transactionsTable" class="table table-striped mb-0">
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

				<!-- JavaScript for client-side filtering -->
				<script>
    document.getElementById("filterInput").addEventListener("keyup", function () {
        let filterValue = this.value.toLowerCase();
        let columnIndex = document.getElementById("filterColumn").value;
        let table = document.getElementById("transactionsTable");
        let trs = table.getElementsByTagName("tr");

        for (let i = 1; i < trs.length; i++) { // skip header row
            let td = trs[i].getElementsByTagName("td")[columnIndex];
            if (td) {
                let txtValue = td.textContent || td.innerText;
                if (txtValue.toLowerCase().includes(filterValue)) {
                    trs[i].style.display = "";
                } else {
                    trs[i].style.display = "none";
                }
            }
        }
    });
</script>

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
