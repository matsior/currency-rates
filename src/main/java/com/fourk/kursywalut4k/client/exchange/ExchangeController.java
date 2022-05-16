package com.fourk.kursywalut4k.client.exchange;

import com.fourk.kursywalut4k.domain.api.CurrencyService;
import com.fourk.kursywalut4k.domain.api.ExtendedCurrencyDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/exchange")
public class ExchangeController extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ExtendedCurrencyDto> extendedCurrencyList = currencyService.getExtendedCurrencies();
        request.setAttribute("rates", extendedCurrencyList);
        request.getRequestDispatcher("views/exchange.jsp").forward(request, response);
    }
}