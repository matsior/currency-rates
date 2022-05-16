package com.fourk.kursywalut4k.client.userCurrencies;

import com.fourk.kursywalut4k.domain.api.BasicCurrencyDto;
import com.fourk.kursywalut4k.domain.api.CurrencyService;
import com.fourk.kursywalut4k.domain.api.UserDto;
import com.fourk.kursywalut4k.domain.api.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/mycurrencies")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value="GET", rolesAllowed = "USER")
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
}
