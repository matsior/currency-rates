package com.fourk.kursywalut4k.dao;

import com.fourk.kursywalut4k.connections.DataBaseConnections;
import com.fourk.kursywalut4k.model.user.User;

import java.sql.*;
import java.util.Optional;


public class UserDao {

    public void saveUser(User user) {
        final String query = """
                INSERT INTO
                    users (username, password, email)
                VALUES
                    (?,?,?)
                """;
        try (PreparedStatement statement = DataBaseConnections.connectWithDataBase(query))
              {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByUsername(String username) {
        final String query = """
                SELECT
                    id, username, password, email
                FROM
                    users
                WHERE
                    username = ?
                """;
        try (PreparedStatement statement = DataBaseConnections.connectWithDataBase(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRow(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(id, username, email, password);
    }
}