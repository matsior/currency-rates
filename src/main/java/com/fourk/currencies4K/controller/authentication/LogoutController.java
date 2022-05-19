package com.fourk.currencies4K.controller.authentication;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPrincipalName = request.getUserPrincipal().getName();
        request.logout();
        log.info("user {} logged out", userPrincipalName);
        response.sendRedirect(request.getContextPath());
    }
}
