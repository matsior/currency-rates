package com.fourk.kursywalut4k.client.signup;

import com.fourk.kursywalut4k.domain.api.UserRegistration;
import com.fourk.kursywalut4k.domain.api.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/signup")
public class SignupController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRegistration userRegistration = getUserData(request);
        userService.register(userRegistration);
        log.info("new user registered {}", userRegistration);
        response.sendRedirect(request.getContextPath());
    }

    private UserRegistration getUserData(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return new UserRegistration(username, email, password);
    }
}
