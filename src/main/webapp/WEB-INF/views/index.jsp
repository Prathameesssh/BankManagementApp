<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Integro Bank</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(to right, #2c5364, #203a43, #0f2027);
	color: #fff;
	height: 100vh;
}

.card {
	background: rgba(255, 255, 255, 0.15);
	backdrop-filter: blur(8px);
	border-radius: 15px;
	padding: 30px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.4);
}

.btn-custom {
	background: #00c6ff;
	color: white;
	font-weight: 500;
}

.btn-custom:hover {
	background: #0072ff;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-dark bg-dark">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1">🏦 Integro Bank</span>
		</div>
	</nav>

	<!-- Main Content -->
	<div class="container d-flex justify-content-center align-items-center"
		style="height: 80vh;">
		<div class="card text-center p-4" style="width: 350px;">
			<h3 class="mb-4">Welcome</h3>
			<p class="mb-4">Please choose an option below:</p>

			<!-- Login Form -->
			<form action="IndexController" method="get" class="d-grid gap-2">
				<input type="hidden" name="action" value="login" />
				<button type="submit" class="btn btn-outline-primary btn-lg text-white">Login</button>
			</form>

			<!-- Register Form -->
			<form action="IndexController" method="get" class="d-grid gap-2 mt-3">
				<input type="hidden" name="action" value="register" />
				<button type="submit" class="btn btn-outline-primary btn-lg text-white">Register</button>
			</form>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
