package com.auca.quiz_application.filter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Session is created
        HttpSession session = se.getSession();
        System.out.println("Session created: " + session.getId());

        // Check if the session attribute "user" is not set
        if (session.getAttribute("user") == null) {
            HttpServletResponse response = (HttpServletResponse) session.getServletContext().getAttribute("response");
            try {
                response.sendRedirect("/secured");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session is destroyed
        HttpSession session = se.getSession();
        System.out.println("Session destroyed: " + session.getId());
    }
}
