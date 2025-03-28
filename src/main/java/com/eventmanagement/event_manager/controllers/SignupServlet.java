package com.eventmanagement.event_manager.controllers;

import com.eventmanagement.event_manager.dao.UserDAO;
import com.eventmanagement.event_manager.models.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignupServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email == null || email.isEmpty() || password == null || password.isEmpty()){
            request.setAttribute("errorMessage", "Please enter both email and password.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        boolean created = userDAO.createUser(new Patient(email, password));
        if(created) {
            response.sendRedirect("login");
        } else {
            request.setAttribute("errorMessage", "User already exists or an error occurred.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
}

