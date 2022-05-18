package com.fourk.kursywalut4k.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnections {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/kursy_walut_4k?serverTimezone=UTC";
    public static final String DB_USER_NAME = "root";
    public static final String DB_PASSWORD = "1234";

    public static PreparedStatement connectWithDataBase(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER_NAME, DB_PASSWORD);
        return connection.prepareStatement(query);
    }
}
