<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(to right, #2c5364, #203a43, #0f2027);
	color: #fff;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.card {
	background: rgba(255, 255, 255, 0.12);
	backdrop-filter: blur(10px);
	border-radius: 20px;
	padding: 30px;
	box-shadow: 0 8px 25px rgba(0, 0, 0, 0.5);
	color: #fff;
}

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

.error-msg {
	color: #ff6b6b;
	font-size: 0.9rem;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card p-4">
					<h2 class="text-center mb-4">Register</h2>
					<form id="registerForm"
						action="${pageContext.request.contextPath}/RegisterPageController"
						method="post">
						<%
						if (request.getAttribute("error") != null) {
						%>
						<div class="alert alert-danger text-center">
							<%=request.getAttribute("error")%>
						</div>
						<%
						}
						if (request.getAttribute("success") != null) {
						%>
						<div class="alert alert-success text-center">
							<%=request.getAttribute("success")%>
						</div>
						<%
						}
						%>
						<!-- Step 1 -->
						<div id="step1">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									class="form-control" name="name" id="name"
									placeholder="Enter full name" required>
								<div class="error-msg" id="nameError"></div>
							</div>

							<div class="mb-3">
								<label for="address" class="form-label">Address</label>
								<textarea name="address" id="address" class="form-control"
									rows="3" placeholder="Enter Address"></textarea>
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input type="email"
									class="form-control" name="email" id="email"
									placeholder="Enter email" required>
								<div class="error-msg" id="emailError"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input
									type="password" class="form-control" name="password"
									id="password" placeholder="Enter password" required>
								<div class="error-msg" id="passwordError"></div>
							</div>

							<div class="d-grid">
								<button type="button" class="btn btn-custom btn-lg"
									onclick="nextStep()">Next</button>
							</div>
						</div>

						<!-- Step 2 -->
						<div id="step2" style="display: none;">
							<div class="mb-3">
								<label class="form-label">Mobile Number</label> <input
									type="tel" class="form-control" name="mobile" id="mobile"
									placeholder="10-digit mobile number" required>
								<div class="error-msg" id="mobileError"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">Aadhar Number</label> <input
									type="text" class="form-control" name="aadharNo" id="aadharNo"
									placeholder="12-digit Aadhar number" required>
								<div class="error-msg" id="aadharError"></div>
							</div>

							<div class="d-flex justify-content-between">
								<button type="button" class="btn btn-secondary btn-lg"
									onclick="prevStep()">Back</button>
								<button type="submit" class="btn btn-custom btn-lg">Register</button>
							</div>
						</div>

					</form>
					<hr class="my-4">
					<p class="text-center">
						Already have an account? <a href="IndexController?action=login"
							class="text-info fw-bold">Login</a>
					</p>
				</div>
			</div>
		</div>
	</div>

	<script>
		function validateStep1() {
			let valid = true;

			// Name
			let name = document.getElementById("name").value.trim();
			if (!/^[A-Za-z\s]{3,}$/.test(name)) {
				document.getElementById("nameError").innerText = "Name must be at least 3 letters (alphabets only).";
				valid = false;
			} else {
				document.getElementById("nameError").innerText = "";
			}

			// Email
			let email = document.getElementById("email").value.trim();
			let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailPattern.test(email)) {
				document.getElementById("emailError").innerText = "Enter a valid email address.";
				valid = false;
			} else {
				document.getElementById("emailError").innerText = "";
			}

			// Password
			let password = document.getElementById("password").value;
			let passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{7,}$/;
			if (!passwordPattern.test(password)) {
				document.getElementById("passwordError").innerText = "Password must be 7+ chars, with uppercase, lowercase, digit & special char.";
				valid = false;
			} else {
				document.getElementById("passwordError").innerText = "";
			}

			return valid;
		}

		function validateStep2() {
			let valid = true;

			// Mobile
			let mobile = document.getElementById("mobile").value.trim();
			if (!/^\d{10}$/.test(mobile)) {
				document.getElementById("mobileError").innerText = "Mobile number must be exactly 10 digits.";
				valid = false;
			} else {
				document.getElementById("mobileError").innerText = "";
			}

			// Aadhar
			let aadhar = document.getElementById("aadharNo").value.trim();
			if (!/^\d{12}$/.test(aadhar)) {
				document.getElementById("aadharError").innerText = "Aadhar number must be exactly 12 digits.";
				valid = false;
			} else {
				document.getElementById("aadharError").innerText = "";
			}

			return valid;
		}

		function nextStep() {
			if (validateStep1()) {
				document.getElementById("step1").style.display = "none";
				document.getElementById("step2").style.display = "block";
			}
		}

		function prevStep() {
			document.getElementById("step2").style.display = "none";
			document.getElementById("step1").style.display = "block";
		}

		// Final validation before submit
		document.getElementById("registerForm").addEventListener("submit",
				function(e) {
					if (!validateStep1() || !validateStep2()) {
						e.preventDefault();
					}
				});
	</script>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<div style="color: red;">
		<%=request.getAttribute("error")%>
	</div>
	<%
	}
	%>
</body>
</html>
