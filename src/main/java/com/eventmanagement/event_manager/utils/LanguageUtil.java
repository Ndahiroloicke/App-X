package com.eventmanagement.event_manager.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageUtil {
    public static ResourceBundle getBundle(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String langParam = request.getParameter("lang");
        if (langParam != null) {
            session.setAttribute("lang", langParam);
        }
        String lang = (String) session.getAttribute("lang");

        Locale locale;
        if ("fr".equals(lang)) {
            locale = new Locale("fr", "FR");
        } else {
            locale = new Locale("en", "US");
        }
        return ResourceBundle.getBundle("messages", locale);
    }
}
