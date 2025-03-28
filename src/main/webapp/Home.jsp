<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.eventmanagement.event_manager.utils.LanguageUtil" %>
<%@ page import="java.util.ResourceBundle" %>
<%
    ResourceBundle bundle = LanguageUtil.getBundle(request);
%>
<!DOCTYPE html>
<html lang="${sessionScope.lang != null ? sessionScope.lang : 'en'}">
<head>
    <meta charset="UTF-8">
    <title><%= bundle.getString("home.title") %></title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css'/>">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-content">
            <a class="navbar-brand" href="home"><%= bundle.getString("navbar.appointmentSystem") %></a>
            <div class="nav-group">
                <ul class="nav-links">
                    <li><a href="appointments"><%= bundle.getString("navbar.myAppointments") %></a></li>
                    <li><a href="logout"><%= bundle.getString("navbar.logout") %></a></li>
                </ul>
                <form method="get" action="home" style="margin-left: 1rem;">
                    <select name="lang" class="language-select" onchange="this.form.submit()">
                        <option value="en" <%= (session.getAttribute("lang") == null || "en".equals(session.getAttribute("lang"))) ? "selected" : "" %>>English</option>
                        <option value="fr" <%= "fr".equals(session.getAttribute("lang")) ? "selected" : "" %>>Français</option>
                    </select>
                </form>
            </div>
        </div>
    </nav>
    
    <div class="main-container">
        <h2 class="auth-title"><%= bundle.getString("home.availableDoctors") %></h2>
        <div class="grid">
            <c:forEach var="doctor" items="${doctors}">
                <div class="card">
                    <h3 class="card-title">${doctor.name}</h3>
                    <p class="card-text"><%= bundle.getString("home.specialization") %>: ${doctor.specialization}</p>
                    <a href="appointments?action=new&doctorId=${doctor.id}" class="btn btn-primary" style="display: block; text-align: center; text-decoration: none; margin-top: 1rem;">
                        <%= bundle.getString("home.bookAppointment") %>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
