package com.fourk.kursywalut4k.client.archive;

import com.fourk.kursywalut4k.domain.api.BasicCurrencyDto;
import com.fourk.kursywalut4k.domain.api.CurrencyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/archive")
public class ArchiveController extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String date = request.getParameter("date");

        if (code != null && date != null) {
            BasicCurrencyDto currency = currencyService.getArchivalCurrency(code, date);
            request.setAttribute("currency", currency);
        }

        request.getRequestDispatcher("views/archive.jsp").forward(request, response);
    }
}