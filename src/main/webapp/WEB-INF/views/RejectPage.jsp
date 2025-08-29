<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Request Rejected</title>
    
    <!-- ✅ Bootstrap CSS for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .rejected-container {
            max-width: 600px;
            margin: 100px auto;
            padding: 40px;
            background-color: #ffffff;
            border: 1px solid #dee2e6;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .rejected-icon {
            font-size: 80px;
            color: #dc3545;
        }
    </style>
</head>
<body>
    <!-- ✅ Main container -->
    <div class="rejected-container">
        
        <!-- ❌ Icon for rejection -->
        <div class="rejected-icon">✖</div>

        <!-- 📢 Message -->
        <h2 class="text-danger">Request Rejected</h2>
        <p class="mt-3">
            We regret to inform you that your account request has been <strong>rejected</strong>.
        </p>
        <p>
            If you believe this is a mistake, please contact our support team for assistance.
        </p>

        <!-- 🔙 Navigation Button -->
        <a href="/WEB-INF/views/index.jsp" class="btn btn-primary mt-3">Back to Home</a>
    </div>

    <!-- ✅ Bootstrap JS (optional for future use) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
