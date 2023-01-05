package com.se07.util;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class ConnectionDatabase {
    static final String user = System.getenv("USER");
    static final String password = System.getenv("MSSQL_PWD");
    static final String serverName = System.getenv("SERVER_NAME");
    static final String databaseName = System.getenv("DATABASE_NAME");
    static final int portNumber = Integer.parseInt(System.getenv("PORT_NUMBER"));
    static final boolean encrypt = Boolean.parseBoolean(System.getenv("ENCRYPT"));
    public static Connection getConnection() {
        Connection databaseLink = null;
        SQLServerDataSource sqlServerDataSource = new SQLServerDataSource();
        sqlServerDataSource.setUser(user);
        sqlServerDataSource.setPassword(password);
        sqlServerDataSource.setServerName(serverName);
        sqlServerDataSource.setDatabaseName(databaseName);
        sqlServerDataSource.setPortNumber(portNumber);
        sqlServerDataSource.setEncrypt(encrypt);
        try {
            databaseLink = sqlServerDataSource.getConnection();
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        }
        return  databaseLink;
    }
}
