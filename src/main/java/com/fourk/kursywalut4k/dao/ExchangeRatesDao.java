package com.fourk.kursywalut4k.dao;

import com.fourk.kursywalut4k.connections.DataBaseConnections;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExchangeRatesDao {

    public void save(int userId, String currencyCode) {
        final String query = """
                INSERT INTO
                    exchange_rates (id_user,id_exchange)
                VALUES
                    (?, ?)
                """;
        try (PreparedStatement statement = DataBaseConnections.connectWithDataBase(query)) {
            statement.setInt(1, userId);
            statement.setString(2, currencyCode);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
