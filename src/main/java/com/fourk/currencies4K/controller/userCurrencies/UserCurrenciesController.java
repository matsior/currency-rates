package com.fourk.currencies4K.controller.userCurrencies;

import com.fourk.currencies4K.dto.BasicCurrencyDto;
import com.fourk.currencies4K.dto.UserDto;
import com.fourk.currencies4K.service.CurrencyService;
import com.fourk.currencies4K.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@WebServlet("/mycurrencies")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value="GET", rolesAllowed = {"USER", "ADMIN"})

        }
)
public class UserCurrenciesController extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPrincipalName = request.getUserPrincipal().getName();
        Optional<UserDto> user = userService.findByName(userPrincipalName);
        List<BasicCurrencyDto> userCurrencies = currencyService.getUserCurrencies(user.get().getId());
        request.setAttribute("rates", userCurrencies);
        request.getRequestDispatcher("views/user_currencies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPrincipalName = request.getUserPrincipal().getName();
        Integer userId = userService.findByName(userPrincipalName).get().getId();
        String code = request.getParameter("code");
        currencyService.save(userId, code);
        log.info("user {} saved new currency ({})", userPrincipalName, code);
        response.sendRedirect(request.getContextPath());
    }
}
