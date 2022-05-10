package com.fourk.kursywalut4k.domain.api;

import com.fourk.kursywalut4k.domain.rate.BasicCurrency;
import com.fourk.kursywalut4k.domain.rate.ExtendedCurrency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService {

    public List<BasicCurrency> createBasicCurrencyList() {
        try {
            String urlAddress = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";
            URL request = new URL(urlAddress);
            URLConnection connection = request.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String data = bufferedReader.readLine();
            bufferedReader.close();
            List<BasicCurrency> result = new ArrayList<>();
            JSONArray outerArray = new JSONArray(data);
            JSONObject jsonObject = outerArray.getJSONObject(0);
            JSONArray ratesArray = jsonObject.getJSONArray("rates");
            for (int i = 0; i < ratesArray.length(); i++) {
                String currencyName = ratesArray.getJSONObject(i).getString("currency");
                String currencyCode = ratesArray.getJSONObject(i).getString("code");
                double currencyMid = ratesArray.getJSONObject(i).getDouble("mid");
                result.add(new BasicCurrency(currencyName, currencyCode, currencyMid));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ExtendedCurrency> createExtendedCurrencyList() {
        try {
            String urlAddress = "http://api.nbp.pl/api/exchangerates/tables/c/";
            URL request = new URL(urlAddress);
            URLConnection connection = request.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String data = bufferedReader.readLine();
            bufferedReader.close();
            List<ExtendedCurrency> result = new ArrayList<>();
            JSONArray outerArray = new JSONArray(data);
            JSONObject jsonObject = outerArray.getJSONObject(0);
            JSONArray ratesArray = jsonObject.getJSONArray("rates");
            for (int i = 0; i < ratesArray.length(); i++) {
                String currencyName = ratesArray.getJSONObject(i).getString("currency");
                String currencyCode = ratesArray.getJSONObject(i).getString("code");
                double currencyBid = ratesArray.getJSONObject(i).getDouble("bid");
                double currencyAsk = ratesArray.getJSONObject(i).getDouble("ask");
                result.add(new ExtendedCurrency(currencyName, currencyCode, currencyBid, currencyAsk));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
