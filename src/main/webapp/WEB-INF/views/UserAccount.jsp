<%@page import="com.aurionpro.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Date" %>
<%
    // Assuming "account" object is set in request by UserAccountController
    Account account = (Account) request.getAttribute("account");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Account Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center">
            <h3>User Account Details</h3>
        </div>
        <div class="card-body">
            <% if(account != null) { %>
            <table class="table table-bordered table-striped">
                <tbody>
                    <tr>
                        <th>ID</th>
                        <td><%= account.getId() %></td>
                    </tr>
                    <tr>
                        <th>User ID</th>
                        <td><%= account.getUserId() %></td>
                    </tr>
                    <tr>
                        <th>Account Number</th>
                        <td><%= account.getAccountNumber() %></td>
                    </tr>
                    <tr>
                        <th>Type</th>
                        <td><%= account.getType() %></td>
                    </tr>
                    <tr>
                        <th>Balance</th>
                        <td> <%= String.format("%.2f", account.getBalance()) %></td>
                    </tr>
                    <tr>
                        <th>Created At</th>
                        <td><%= account.getCreatedAt() %></td>
                    </tr>
                    <tr>
                        <th>Status</th>
                        <td>
                            <% if(account.isFreezed()) { %>
                                <span class="badge bg-danger">Freezed</span>
                            <% } else { %>
                                <span class="badge bg-success">Active</span>
                            <% } %>
                        </td>
                    </tr>
                </tbody>
            </table>
            <% } else { %>
                <p class="text-center text-muted">No account details found.</p>
            <% } %>
        </div>
        <div class="card-footer text-center">
            <a href="AdminDashboardController" class="btn btn-secondary"> Back</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
