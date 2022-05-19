package com.fourk.currencies4K.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.fourk.currencies4K.utils.PropertiesSupplier.getProperty;

public class DataBaseConnector {
    public static final String JDBC_URL = getProperty("db.url");
    public static final String DB_USER_NAME = getProperty("db.user");
    public static final String DB_PASSWORD = getProperty("db.password");

    public static PreparedStatement connectWithDataBase(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER_NAME, DB_PASSWORD);
        return connection.prepareStatement(query);
    }
}
