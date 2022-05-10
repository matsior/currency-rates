package com.fourk.kursywalut4k.client.home;

import com.fourk.kursywalut4k.domain.api.CurrencyService;
import com.fourk.kursywalut4k.domain.rate.BasicCurrency;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"", "/average"})
public class HomeController extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BasicCurrency> basicCurrencyList = currencyService.createBasicCurrencyList();
        request.setAttribute("rates", basicCurrencyList);
        request.getRequestDispatcher("views/index.jsp").forward(request, response);
    }
}
