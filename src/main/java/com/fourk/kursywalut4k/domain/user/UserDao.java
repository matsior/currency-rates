package com.fourk.kursywalut4k.domain.user;

import com.fourk.kursywalut4k.domain.connections.DataBaseConnections;

import java.sql.*;
import java.util.Optional;


public class UserDao {

    public void saveUser(User user) {
        final String query = """
                INSERT INTO
                    user_account (nick, password, email)
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
                    id_user, nick, password, email
                FROM
                    user_account
                WHERE
                    nick = ?
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
        int id = resultSet.getInt("id_user");
        String username = resultSet.getString("nick");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(id, username, email, password);
    }
}