package com.fourk.currencies4K.dao;

import com.fourk.currencies4K.utils.DataBaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersCurrenciesDao {

    public List<String> findUserSavedCurrenciesByUserId(int userId) {
        final String query = """
                SELECT
                    user_id, currency_code
                FROM
                    users_currencies
                WHERE
                    user_id = (?);
                """;
        try (PreparedStatement statement = DataBaseConnector.connectWithDataBase(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<String> userSavedCurrencyCodes = new ArrayList<>();
            while (resultSet.next()) {
                String currencyCode = resultSet.getString("currency_code");
                userSavedCurrencyCodes.add(currencyCode);
            }
            return userSavedCurrencyCodes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(int userId, String currencyCode) {
        final String query = """
                INSERT INTO
                    users_currencies (user_id, currency_code)
                VALUES
                    (?, ?)
                ON DUPLICATE KEY UPDATE
                    currency_code = (?)
                """;
        try (PreparedStatement statement = DataBaseConnector.connectWithDataBase(query)) {
            statement.setInt(1, userId);
            statement.setString(2, currencyCode);
            statement.setString(3, currencyCode);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
