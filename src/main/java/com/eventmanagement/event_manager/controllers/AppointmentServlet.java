package com.eventmanagement.event_manager.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.eventmanagement.event_manager.dao.AppointmentDAO;
import com.eventmanagement.event_manager.dao.DoctorDAO;
import com.eventmanagement.event_manager.models.Appointment;
import com.eventmanagement.event_manager.models.Doctor;
import com.eventmanagement.event_manager.models.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AppointmentServlet extends HttpServlet {
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient user = (Patient) session.getAttribute("user");
        if(user == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        if(action != null) {
            switch(action) {
                case "new":
                    String doctorId = request.getParameter("doctorId");
                    request.setAttribute("doctorId", doctorId);
                    request.getRequestDispatcher("/AppointmentForm.jsp").forward(request, response);
                    break;
                case "edit":
                    String appointmentId = request.getParameter("id");
                    request.setAttribute("appointmentId", appointmentId);
                    request.setAttribute("currentDate", request.getParameter("currentDate"));
                    request.getRequestDispatcher("/UpdateAppointment.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("appointments", appointmentDAO.getAppointmentsByUser(user.getUsername()));
                    request.getRequestDispatcher("/Appointments.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("appointments", appointmentDAO.getAppointmentsByUser(user.getUsername()));
            request.getRequestDispatcher("/Appointments.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient user = (Patient) session.getAttribute("user");
        if(user == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        if("delete".equals(action)) {
            int appointmentId = Integer.parseInt(request.getParameter("id"));
            if(appointmentDAO.deleteAppointment(appointmentId, user.getUsername())) {
                response.sendRedirect("appointments");
            } else {
                request.setAttribute("errorMessage", "Failed to delete appointment");
                request.getRequestDispatcher("/Appointments.jsp").forward(request, response);
            }
            return;
        } else if("update".equals(action)) {
            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
            String dateStr = request.getParameter("date");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            try {
                Date newDate = sdf.parse(dateStr);
                if(appointmentDAO.updateAppointment(appointmentId, newDate, user.getUsername())) {
                    response.sendRedirect("appointments");
                } else {
                    request.setAttribute("errorMessage", "Failed to update appointment");
                    request.getRequestDispatcher("/UpdateAppointment.jsp").forward(request, response);
                }
            } catch(ParseException e) {
                request.setAttribute("errorMessage", "Invalid date format");
                request.getRequestDispatcher("/UpdateAppointment.jsp").forward(request, response);
            }
            return;
        }

        String doctorIdStr = request.getParameter("doctorId");
        String dateStr = request.getParameter("date");

        if(doctorIdStr == null || doctorIdStr.isEmpty() || dateStr == null || dateStr.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("/AppointmentForm.jsp").forward(request, response);
            return;
        }

        int doctorId = Integer.parseInt(doctorIdStr);
        Doctor doctor = doctorDAO.getDoctorById(doctorId);
        if(doctor == null) {
            request.setAttribute("errorMessage", "Invalid doctor selected.");
            request.getRequestDispatcher("/AppointmentForm.jsp").forward(request, response);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date appointmentDate = null;
        try {
            appointmentDate = sdf.parse(dateStr);
        } catch(ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format.");
            request.getRequestDispatcher("/AppointmentForm.jsp").forward(request, response);
            return;
        }

        Appointment appointment = new Appointment(doctor, appointmentDate);
        boolean added = appointmentDAO.addAppointment(appointment, user.getUsername());
        if(added) {
            response.sendRedirect("appointments");
        } else {
            request.setAttribute("errorMessage", "Error booking appointment. Please try again.");
            request.getRequestDispatcher("/AppointmentForm.jsp").forward(request, response);
        }
    }
}
