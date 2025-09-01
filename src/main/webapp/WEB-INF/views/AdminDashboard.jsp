<%@page import="com.aurionpro.model.Account"%>
<%@page import="com.aurionpro.model.User"%>
<%@page import="com.aurionpro.model.Transaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%
// Assume session contains a List<User> named "users"
List<Account> accounts = (List<Account>) session.getAttribute("accounts");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard - Integro Bank</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	overflow-x: hidden;
}

.sidebar {
	height: 100vh;
	position: fixed;
	left: 0;
	top: 0;
	width: 250px;
	background-color: #343a40;
	padding-top: 60px;
}

.sidebar a {
	color: #fff;
	display: block;
	padding: 15px;
	text-decoration: none;
}

.sidebar a:hover {
	background-color: #495057;
}

.content {
	margin-left: 250px;
	padding: 20px;
}

.card {
	border-radius: 12px;
}
</style>
</head>
<body>

	<!-- Sidebar -->
	<div class="sidebar">
		<h4 class="text-center text-white mb-4">Integro Bank</h4>
		<a href="javascript:void(0)" onclick="showSection('pending')">Pending
			Verification</a> <a href="javascript:void(0)"
			onclick="showSection('transactions')">All Transactions</a> <a
			href="javascript:void(0)" onclick="showSection('freeze')">Freeze
			Accounts</a> <a class="text-white" onclick="showSection('manageFunds')">Manage
			Users </a>
		<hr class="bg-light">
		<a href="LogoutController" class="text-danger">Logout</a>
	</div>

	<!-- Main Content -->
	<div class="content">
		<h2 class="mb-4">Admin Dashboard</h2>

		<!-- Section Toggle Buttons -->
		<div class="btn-group mb-3" role="group">
			<button type="button" class="btn btn-outline-primary"
				onclick="showSection('pending')">Pending Verification</button>
			<button type="button" class="btn btn-outline-success"
				onclick="showSection('transactions')">All Transactions</button>
			<button type="button" class="btn btn-outline-danger"
				onclick="showSection('freeze')">Freeze Accounts</button>
			<button type="button" class="btn btn-outline-warning"
				onclick="showSection('manageFunds')">Manage Funds</button>
		</div>

		<!-- Pending Account Verification -->
		<section id="pending" class="mb-5 section">
			<div class="card shadow-sm">
				<div class="card-header bg-primary text-white">Pending Account
					Verification</div>
				<div class="card-body">
					<p class="text-muted">Approve users to assign them account
						numbers.</p>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>User ID</th>
								<th>Full Name</th>
								<th>Email</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<User> pendingUsers = (List<User>) session.getAttribute("pendingUsers");
							if (pendingUsers != null && !pendingUsers.isEmpty()) {
								for (User u : pendingUsers) {
							%>
							<tr>
								<td><%=u.getId()%></td>
								<td><a href="ProfileController?userId=<%=u.getId()%>">
										<%=u.getName()%>
								</a></td>
								<td><%=u.getEmail()%></td>

								<td>
									<form action="VerifyAccountController" method="post"
										style="display: inline;">
										<input type="hidden" name="userId" value="<%=u.getId()%>">
										<button type="submit" class="btn btn-success btn-sm">Approve</button>
									</form>
									<form action="RejectAccountController" method="post"
										style="display: inline;">
										<input type="hidden" name="userId" value="<%=u.getId()%>">
										<button type="submit" class="btn btn-danger btn-sm">Reject</button>
									</form>
								</td>
							</tr>
							<%
							}
							} else {
							%>
							<tr>
								<td colspan="4" class="text-center text-muted">No pending
									users</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</section>

		<!-- All Transactions -->
		<section id="transactions" class="mb-5 section d-none">
			<div class="card shadow-sm">
				<div class="card-header bg-success text-white">All User
					Transactions</div>
				<div class="card-body">

					<!-- Filter Form -->
					<div class="mb-3 row">
						<div class="col-md-3">
							<label for="fromDate" class="form-label">From Date</label> <input
								type="date" id="fromDate" class="form-control">
						</div>
						<div class="col-md-3">
							<label for="toDate" class="form-label">To Date</label> <input
								type="date" id="toDate" class="form-control">
						</div>
						<div class="col-md-3">
							<label for="txnId" class="form-label">Transaction ID</label> <input
								type="text" id="txnId" class="form-control" placeholder="Txn ID">
						</div>
						<div class="col-md-3">
							<label for="accountNo" class="form-label">Account No.</label> <input
								type="text" id="accountNo" class="form-control"
								placeholder="From/To Account">
						</div>
					</div>
					<button class="btn btn-primary mb-3" onclick="filterTransactions()">Filter</button>
					<button class="btn btn-secondary mb-3" onclick="resetFilter()">Reset</button>

					<!-- Transaction Table -->
					<div style="max-height: 300px; overflow-y: auto;">
						<table id="txnTable" class="table table-bordered">
							<thead>
								<tr>
									<th>Txn ID</th>
									<th>From</th>
									<th>To</th>
									<th>Amount</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody>
								<%
								List<Transaction> txns = (List<Transaction>) session.getAttribute("allTransactions");
								if (txns != null && !txns.isEmpty()) {
									for (Transaction t : txns) {
								%>
								<tr>
									<td><%=t.getId()%></td>
									<td><%=t.getFrom_account()%></td>
									<td><%=t.getTo_account()%></td>
									<td><%=String.format("%.2f", t.getAmount())%></td>
									<td><%=t.getCreatedAt()%></td>
								</tr>
								<%
								}
								} else {
								%>
								<tr>
									<td colspan="5" class="text-center text-muted">No
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
		</section>

		<script>
	function filterTransactions() {
		const fromDate = document.getElementById("fromDate").value;
		const toDate = document.getElementById("toDate").value;
		const txnId = document.getElementById("txnId").value.toLowerCase();
		const accountNo = document.getElementById("accountNo").value.toLowerCase();

		const table = document.getElementById("txnTable");
		const rows = table.getElementsByTagName("tr");

		for (let i = 1; i < rows.length; i++) { // skip header
			const cells = rows[i].getElementsByTagName("td");
			if (cells.length === 0) continue;

			const rowTxnId = cells[0].innerText.toLowerCase();
			const rowFrom = cells[1].innerText.toLowerCase();
			const rowTo = cells[2].innerText.toLowerCase();
			const rowDate = cells[4].innerText;

			let showRow = true;

			// Transaction ID filter
			if (txnId && !rowTxnId.includes(txnId)) showRow = false;

			// Account number filter
			if (accountNo && !(rowFrom.includes(accountNo) || rowTo.includes(accountNo))) showRow = false;

			// Date filter
			if (fromDate || toDate) {
				const txnDate = new Date(rowDate);
				if (fromDate && txnDate < new Date(fromDate)) showRow = false;
				if (toDate && txnDate > new Date(toDate)) showRow = false;
			}

			rows[i].style.display = showRow ? "" : "none";
		}
	}

	function resetFilter() {
		document.getElementById("fromDate").value = "";
		document.getElementById("toDate").value = "";
		document.getElementById("txnId").value = "";
		document.getElementById("accountNo").value = "";
		filterTransactions(); // reset to show all
	}
</script>


		<!-- Freeze Accounts -->
		<section id="freeze" class="mb-5 section d-none">
			<div class="card shadow-sm">
				<div class="card-header bg-danger text-white">Freeze /
					Unfreeze Accounts</div>
				<div class="card-body">
					<form class="row g-3" action="FreezeAccountController"
						method="post">
						<div class="col-md-4">
							<input type="text" name="accountNumber" class="form-control"
								placeholder="Enter Account Number" required>
						</div>
						<div class="col-md-4">
							<select class="form-select" name="action">
								<option value="freeze">Freeze</option>
								<option value="unfreeze">Unfreeze</option>
							</select>
						</div>
						<div class="col-md-4">
							<button type="submit" class="btn btn-danger">Submit</button>
						</div>
					</form>
					<!-- Currently Frozen Accounts Table -->
					<p class="text-muted mb-2">Currently frozen accounts:</p>
					<table class="table table-striped">
						<thead class="table-light">
							<tr>
								<th>User ID</th>
								<th>Name</th>
								<th>Account Number</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							boolean anyFrozen = false;
							if (accounts != null) {
								for (Account a : accounts) {
									if (a.isFreezed()) {
								anyFrozen = true;
							%>
							<tr>
								<td><%=a.getId()%></td>
								<td><%=a.getUserId()%></td>
								<td><%=a.getAccountNumber()%></td>
								<td>
									<form action="FreezeAccountController" method="post"
										style="display: inline;">
										<input type="hidden" name="accountNumber"
											value="<%=a.getAccountNumber()%>"> <input
											type="hidden" name="action" value="unfreeze">
										<button type="submit" class="btn btn-sm btn-success">Unfreeze</button>
									</form>
								</td>
							</tr>
							<%
							}
							}
							}
							if (!anyFrozen) {
							%>
							<tr>
								<td colspan="4" class="text-center text-muted">No frozen
									accounts</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</section>
		<!-- Manage Funds (Credit/Debit) -->
		<section id="manageFunds" class="mb-5 section d-none">
			<div class="card shadow-sm">
				<div class="card-header bg-warning text-dark">Manage Funds</div>
				<div class="card-body">
					<p class="text-muted">Credit or debit funds to a user account.</p>
					<form class="row g-3" action="ManageFundsController" method="post">
						<div class="col-md-4">
							<label for="accountNumber" class="form-label">Account
								Number</label> <input type="text" name="accountNumber"
								id="accountNumber" class="form-control"
								placeholder="Enter Account Number" required>
						</div>
						<div class="col-md-4">
							<label for="amount" class="form-label">Amount</label> <input
								type="number" step="0.01" name="amount" id="amount"
								class="form-control" placeholder="Enter Amount" required>
						</div>
						<div class="col-md-4">
							<label for="actionType" class="form-label">Action</label> <select
								class="form-select" name="actionType" id="actionType" required>
								<option value="credit">Credit</option>
								<option value="debit">Debit</option>
							</select>
						</div>
						<div class="col-12 text-end">
							<button type="submit" class="btn btn-warning">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Section Toggle Script -->
	<script>
    function showSection(sectionId) {
        document.querySelectorAll('.section').forEach(sec => sec.classList.add('d-none'));
        document.getElementById(sectionId).classList.remove('d-none');
    }
    // Set default section on load
    window.onload = function() { showSection('pending'); };
    </script>
</body>
</html>
