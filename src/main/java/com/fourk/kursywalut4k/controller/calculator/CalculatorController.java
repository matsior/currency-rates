package com.fourk.kursywalut4k.controller.calculator;

import com.fourk.kursywalut4k.dto.BasicCurrencyDto;
import com.fourk.kursywalut4k.dto.CalculateRequest;
import com.fourk.kursywalut4k.dto.CalculateResult;
import com.fourk.kursywalut4k.service.CalculatorService;
import com.fourk.kursywalut4k.service.CurrencyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/calculator")
public class CalculatorController extends HttpServlet {
    private final CalculatorService calculatorService = new CalculatorService();
    private final CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BasicCurrencyDto> basicCurrencies = currencyService.getBasicCurrencies();
        request.setAttribute("currencies", basicCurrencies);
        request.getRequestDispatcher("/views/calculator.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CalculateRequest calculateRequest = getCalculateRequest(request);
        CalculateResult result = calculatorService.calculate(calculateRequest);
        List<BasicCurrencyDto> basicCurrencies = currencyService.getBasicCurrencies();
        request.setAttribute("currencies", basicCurrencies);
        request.setAttribute("result", result);
        request.getRequestDispatcher("/views/calculator.jsp").forward(request, response);
    }

    private CalculateRequest getCalculateRequest(HttpServletRequest request) {
        String base = request.getParameter("base");
        String amountString = request.getParameter("amount");
        double amount = Double.parseDouble(amountString);
        String target = request.getParameter("target");
        return new CalculateRequest(base, target, amount);
    }
}
