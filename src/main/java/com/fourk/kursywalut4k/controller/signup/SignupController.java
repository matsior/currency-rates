package com.fourk.kursywalut4k.controller.signup;

import com.fourk.kursywalut4k.dto.UserRegistration;
import com.fourk.kursywalut4k.service.UserService;
import com.fourk.kursywalut4k.utils.PasswordValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        if (PasswordValidation.isPasswordValid(userRegistration.getPassword())) {
            userService.register(userRegistration);
            log.info("new user registered {}", userRegistration);
            response.sendRedirect(request.getContextPath() + "/views/registration_successful.jsp");
        } else {
            log.info("failed registration: invalid password: {}", userRegistration.getPassword());
            response.sendRedirect(request.getContextPath() + "/views/registration_failed.jsp");
        }
    }

    private UserRegistration getUserData(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return new UserRegistration(username, email, password);
    }
}