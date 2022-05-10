package com.fourk.kursywalut4k.client.exchange;

import com.fourk.kursywalut4k.domain.api.CurrencyService;
import com.fourk.kursywalut4k.domain.rate.BasicCurrency;
import com.fourk.kursywalut4k.domain.rate.ExtendedCurrency;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/exchange")
public class ExchangeController extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ExtendedCurrency> extendedCurrencyList = currencyService.createExtendedCurrencyList();
        request.setAttribute("rates", extendedCurrencyList);
        request.getRequestDispatcher("views/exchange.jsp").forward(request, response);
    }
}