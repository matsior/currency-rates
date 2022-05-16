package com.fourk.kursywalut4k.domain.currency;

import com.fourk.kursywalut4k.domain.connections.DataBaseConnections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public List<BasicCurrency> findUserSavedCurrenciesByUserId(int userId) {
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
            List<BasicCurrency> userSavedCurrencies = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String shortcut = resultSet.getString("shortcut");
                Double exchange = resultSet.getDouble("exchange");
                BasicCurrency currency = new BasicCurrency(name, shortcut, exchange);
                userSavedCurrencies.add(currency);
            }
            return userSavedCurrencies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
