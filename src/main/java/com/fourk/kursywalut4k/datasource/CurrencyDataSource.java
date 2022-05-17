package com.fourk.kursywalut4k.datasource;

import com.fourk.kursywalut4k.connections.APIConnections;
import com.fourk.kursywalut4k.model.currency.CurrencyBasic;
import com.fourk.kursywalut4k.model.currency.CurrencyExtended;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fourk.kursywalut4k.connections.APIConnections.createStringFromJson;

public class CurrencyDataSource {

    private static final String BASE_URL = "http://api.nbp.pl/api/exchangerates/";

    public List<CurrencyBasic> createBasicCurrencyList() {
        String urlAddress = BASE_URL + "tables/a";
        try {
            String data = createStringFromJson(urlAddress);
            List<CurrencyBasic> result = new ArrayList<>();
            JSONArray outerArray = new JSONArray(data);
            JSONObject jsonObject = outerArray.getJSONObject(0);
            JSONArray ratesArray = jsonObject.getJSONArray("rates");
            for (int i = 0; i < ratesArray.length(); i++) {
                String currencyName = ratesArray.getJSONObject(i).getString("currency");
                currencyName = new String(currencyName.getBytes(), StandardCharsets.UTF_8);
                String currencyCode = ratesArray.getJSONObject(i).getString("code");
                double currencyMid = ratesArray.getJSONObject(i).getDouble("mid");
                result.add(new CurrencyBasic(currencyName, currencyCode, currencyMid));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CurrencyExtended> createExtendedCurrencyList() {
        String urlAddress = BASE_URL + "tables/c";
        try {
            String data = createStringFromJson(urlAddress);
            List<CurrencyExtended> result = new ArrayList<>();
            JSONArray outerArray = new JSONArray(data);
            JSONObject jsonObject = outerArray.getJSONObject(0);
            JSONArray ratesArray = jsonObject.getJSONArray("rates");
            for (int i = 0; i < ratesArray.length(); i++) {
                String currencyName = ratesArray.getJSONObject(i).getString("currency");
                currencyName = new String(currencyName.getBytes(), StandardCharsets.UTF_8);
                String currencyCode = ratesArray.getJSONObject(i).getString("code");
                double currencyBid = ratesArray.getJSONObject(i).getDouble("bid");
                double currencyAsk = ratesArray.getJSONObject(i).getDouble("ask");
                result.add(new CurrencyExtended(currencyName, currencyCode, currencyBid, currencyAsk));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CurrencyBasic createCurrencyFromCode(String code) {
        String urlAddress = BASE_URL + "/rates/a/" + code;
        try {
            String data = createStringFromJson(urlAddress);
            return getBasicCurrency(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CurrencyBasic createCurrencyFromCode(String code, String date) {
        String urlAddress = BASE_URL + "/rates/a/" + code + "/" + date;
        try {
            String data = APIConnections.createStringFromJson(urlAddress);
            return getBasicCurrency(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CurrencyBasic getBasicCurrency(String data) {
        JSONObject jsonObject = new JSONObject(data);
        String currencyName = jsonObject.getString("currency");
        currencyName = new String(currencyName.getBytes(), StandardCharsets.UTF_8);
        String currencyCode = jsonObject.getString("code");
        JSONArray ratesArray = jsonObject.getJSONArray("rates");
        double currencyMid = ratesArray.getJSONObject(0).getDouble("mid");
        return new CurrencyBasic(currencyName, currencyCode, currencyMid);
    }
}