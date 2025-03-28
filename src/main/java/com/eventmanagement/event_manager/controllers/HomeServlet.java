package com.eventmanagement.event_manager.controllers;

import com.eventmanagement.event_manager.dao.DoctorDAO;
import com.eventmanagement.event_manager.models.Doctor;
import com.eventmanagement.event_manager.models.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    private DoctorDAO doctorDAO = new DoctorDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient user = (Patient) session.getAttribute("user");
        if(user == null) {
            response.sendRedirect("login");
            return;
        }
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/Home.jsp").forward(request, response);
    }
}


