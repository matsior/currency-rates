package com.fourk.currencies4K.controller.archive;

import com.fourk.currencies4K.dto.BasicCurrencyDto;
import com.fourk.currencies4K.service.CurrencyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/archive")
public class ArchiveController extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BasicCurrencyDto> basicCurrencies = currencyService.getBasicCurrencies();
        request.setAttribute("currencies", basicCurrencies);

        String code = request.getParameter("code");
        String date = request.getParameter("date");


        if (code != null && date != null && LocalDate.parse(date).isBefore(LocalDate.now())) {
            BasicCurrencyDto currency = currencyService.getArchivalCurrency(code, date);
            request.setAttribute("currency", currency);
        }

        request.getRequestDispatcher("views/archive.jsp").forward(request, response);
    }
}