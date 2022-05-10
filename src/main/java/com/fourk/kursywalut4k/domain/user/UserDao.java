package com.fourk.kursywalut4k.domain.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public void save(User user) {
        final String query = """
                INSERT INTO 
                    user_account (nick, password, email) 
                VALUES 
                    (?,?,?)
                """;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursy_walut_4k?serverTimezone=UTC", "root", "1234");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
