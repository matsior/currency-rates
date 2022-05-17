package com.fourk.kursywalut4k.controller.calculator;

import com.fourk.kursywalut4k.dto.CalculatorResult;
import com.fourk.kursywalut4k.service.CalculatorService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/calculator")
public class CalculatorController extends HttpServlet {
    private final CalculatorService calculatorService = new CalculatorService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/calculator.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String base = request.getParameter("base");
        String amountString = request.getParameter("amount");
        double amount = Double.parseDouble(amountString);
        String target = request.getParameter("target");
        CalculatorResult result = calculatorService.calculate(base, target, amount);
        request.setAttribute("result", result);
        request.getRequestDispatcher("/views/calculator.jsp").forward(request, response);
    }
}
