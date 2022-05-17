package com.fourk.kursywalut4k.dao;

import com.fourk.kursywalut4k.connections.DataBaseConnections;
import com.fourk.kursywalut4k.model.currency.CurrencyBasic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public List<CurrencyBasic> findUserSavedCurrenciesByUserId(int userId) {
        final String query = """
                SELECT
                    id_user, name, shortcut, exchange
                FROM
                    exchange_rates
                JOIN
                    currency
                ON
                    exchange_rates.id_exchange = currency.id_exchange
                WHERE
                    id_user = (?);
                """;
        try (PreparedStatement statement = DataBaseConnections.connectWithDataBase(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<CurrencyBasic> userSavedCurrencies = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String shortcut = resultSet.getString("shortcut");
                Double exchange = resultSet.getDouble("exchange");
                CurrencyBasic currency = new CurrencyBasic(name, shortcut, exchange);
                userSavedCurrencies.add(currency);
            }
            return userSavedCurrencies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
