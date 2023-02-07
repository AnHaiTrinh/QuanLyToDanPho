package com.se07.util;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

/**
 * Lớp tiện ích cung cấp kết nối với cơ sở dữ liệu
 */
public class ConnectionDatabase {
    private static final String user = System.getenv("USER");
    private static final String password = System.getenv("MSSQL_PWD");
    private static final String serverName = System.getenv("SERVER_NAME");
    private static final String databaseName = System.getenv("DATABASE_NAME");
    private static final int portNumber = Integer.parseInt(System.getenv("PORT_NUMBER"));
    private static final boolean encrypt = Boolean.parseBoolean(System.getenv("ENCRYPT"));

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            SQLServerDataSource sqlServerDataSource = new SQLServerDataSource();
            sqlServerDataSource.setUser(user);
            sqlServerDataSource.setPassword(password);
            sqlServerDataSource.setServerName(serverName);
            sqlServerDataSource.setDatabaseName(databaseName);
            sqlServerDataSource.setPortNumber(portNumber);
            sqlServerDataSource.setEncrypt(encrypt);
            try {
                connection = sqlServerDataSource.getConnection();
            } catch (SQLServerException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}