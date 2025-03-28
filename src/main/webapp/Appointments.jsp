<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Events - Event Management</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css'/>">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-content">
            <a class="navbar-brand" href="home">Event Management</a>
            <ul class="nav-links">
                <li><a href="home">Home</a></li>
                <li><a href="logout">Logout</a></li>
            </ul>
        </div>
    </nav>
    
    <div class="main-container">
        <h2 class="auth-title">Your Events</h2>
        <div class="card">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Host</th>
                        <th>Event Type</th>
                        <th>Date & Time</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="appointment" items="${appointments}">
                        <tr>
                            <td>${appointment.id}</td>
                            <td>${appointment.doctor.name}</td>
                            <td>${appointment.doctor.specialization}</td>
                            <td><fmt:formatDate value="${appointment.appointmentDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td class="action-buttons">
                                <a href="appointments?action=edit&id=${appointment.id}&currentDate=<fmt:formatDate value="${appointment.appointmentDate}" pattern="yyyy-MM-dd'T'HH:mm"/>" 
                                   class="btn btn-primary btn-sm">Update</a>
                                <form method="post" action="appointments" style="display: inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="${appointment.id}">
                                    <button type="submit" class="btn btn-danger btn-sm" 
                                            onclick="return confirm('Are you sure you want to delete this appointment?')">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>

