<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up - Appointment Management</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css'/>">
</head>
<body>
    <div class="auth-container">
        <div class="card">
            <h2 class="auth-title">Sign Up</h2>
            <form method="post" action="signup">
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" class="form-control" required/>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" required/>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
                <button type="submit" class="btn btn-success" style="width: 100%;">Sign Up</button>
            </form>
            <a href="login" class="auth-link">Already have an account? Login</a>
        </div>
    </div>
</body>
</html>

