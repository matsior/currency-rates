package com.fourk.currencies4K.controller.admin;

import com.fourk.currencies4K.dto.UserDto;
import com.fourk.currencies4K.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value="GET", rolesAllowed = "ADMIN")
        }
)
public class AdminController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = userService.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/admin.jsp").forward(request, response);

    }
}
