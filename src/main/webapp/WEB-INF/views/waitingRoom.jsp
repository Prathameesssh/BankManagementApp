<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Pending Verification</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">

    <div class="card shadow-lg border-0 text-center p-4" style="max-width: 500px;">
        <div class="card-body">
            <h3 class="card-title text-warning mb-3">Profile Under Verification</h3>
            <p class="card-text text-muted">
                Your profile is currently <strong>under verification</strong>.  
                Please check back later.
            </p>
            <a href="/WEB-INF/views/IndexController" class="btn btn-primary mt-3">Go Back to Home</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
