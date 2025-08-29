<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
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
			<div class="col-md-6">
				<div class="card p-4">
					<h2 class="text-center mb-4">Register</h2>
					<form
						action="${pageContext.request.contextPath}/RegisterPageController"
						method="post">

						<!-- Step 1 -->
						<div id="step1">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									class="form-control" name="name" placeholder="Enter full name"
									required>
							</div>
							<div class="mb-3">
								<label class="form-label">Address</label> <input type="text"
									class="form-control" name="address" placeholder="Enter address"
									required>
							</div>
							<div class="mb-3">
								<label class="form-label">Email</label> <input type="email"
									class="form-control" name="email" placeholder="Enter email"
									required>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label> <input
									type="password" class="form-control" name="password"
									placeholder="Enter password" required>
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
									type="tel" class="form-control" name="mobile"
									pattern="[0-9]{10}" placeholder="10-digit mobile number"
									required>
							</div>

							<div class="mb-3">
								<label class="form-label">Aadhar Number</label> <input
									type="text" class="form-control" name="aadharNo"
									pattern="[0-9]{12}" placeholder="12-digit Aadhar number"
									required>
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
		function nextStep() {
			document.getElementById("step1").style.display = "none";
			document.getElementById("step2").style.display = "block";
		}
		function prevStep() {
			document.getElementById("step2").style.display = "none";
			document.getElementById("step1").style.display = "block";
		}
	</script>
</body>
</html>
