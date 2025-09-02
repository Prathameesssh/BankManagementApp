<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
/* Background gradient */
body {
	background: linear-gradient(to right, #2c5364, #203a43, #0f2027);
	color: #fff;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

/* Glass card */
.card {
	background: rgba(255, 255, 255, 0.12);
	backdrop-filter: blur(10px);
	border-radius: 20px;
	padding: 30px;
	box-shadow: 0 8px 25px rgba(0, 0, 0, 0.5);
	color: #fff;
}

/* Custom button */
.btn-custom {
	background: linear-gradient(45deg, #00c6ff, #0072ff);
	color: white;
	font-weight: 600;
	border: none;
	border-radius: 8px;
	transition: all 0.3s ease;
}

.btn-custom:hover {
	background: linear-gradient(45deg, #0072ff, #00c6ff);
	transform: scale(1.05);
}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card p-4">
					<h2 class="text-center mb-4">Login</h2>
					<form action="LoginPageController" method="post">
						<div class="mb-3">
							<label class="form-label">Email</label> <input type="text"
								class="form-control" name="email" placeholder="Enter email">
						</div>

						<div class="mb-3">
							<label class="form-label">Password</label> <input type="password"
								class="form-control" name="password"
								placeholder="Enter password">
						</div>

						<div class="d-grid">
							<button type="submit" class="btn btn-custom btn-lg">Login</button>
						</div>
					</form>
					<hr class="my-4">
					<p class="text-center">
						Don't have an account? <a href="IndexController?action=register"
							class="text-info fw-bold">Register</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- Error Modal -->
	<div class="modal fade" id="errorModal" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content text-dark">
				<div class="modal-header bg-danger text-white">
					<h5 class="modal-title">Login Failed</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body"><%=request.getAttribute("errorMessage")%></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap JS Bundle (must come before modal script) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<%
	if (request.getAttribute("errorMessage") != null) {
	%>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var errorModal = new bootstrap.Modal(document
					.getElementById('errorModal'));
			errorModal.show();
		});
	</script>
	<%
	}
	%>

</body>
</html>
