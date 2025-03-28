<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Event - Event Management</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css'/>">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-content">
            <a class="navbar-brand" href="home">Event Management</a>
            <ul class="nav-links">
                <li><a href="appointments">My Events</a></li>
                <li><a href="logout">Logout</a></li>
            </ul>
        </div>
    </nav>
    
    <div class="main-container">
        <div class="auth-container">
            <div class="card">
                <h2 class="auth-title">Update Event</h2>
                <form method="post" action="appointments">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="appointmentId" value="${appointmentId}">
                    <div class="form-group">
                        <label for="date">Select New Date & Time</label>
                        <input type="datetime-local" name="date" id="date" 
                               class="form-control" required value="${currentDate}"/>
                    </div>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">${errorMessage}</div>
                    </c:if>
                    <button type="submit" class="btn btn-primary" style="width: 100%;">Update Event</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html> 