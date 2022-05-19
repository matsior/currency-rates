package com.fourk.currencies4K.dao;

import com.fourk.currencies4K.utils.DataBaseConnector;
import com.fourk.currencies4K.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDao {

    public void saveUser(User user) {
        final String query = """
                INSERT INTO
                    users (username, password, email)
                VALUES
                    (?,?,?)
                """;
        try (PreparedStatement statement = DataBaseConnector.connectWithDataBase(query)) {
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
        try (PreparedStatement statement = DataBaseConnector.connectWithDataBase(query)) {
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

    public List<User> findAll() {
        final String query = """
                SELECT
                    id, username, email, password
                FROM
                    users
                """;
        try (PreparedStatement statement = DataBaseConnector.connectWithDataBase(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(mapRow(resultSet));

            }
            return users;
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