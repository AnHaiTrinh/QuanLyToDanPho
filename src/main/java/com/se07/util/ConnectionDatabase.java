package com.se07.util;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class ConnectionDatabase {
    public static Connection getConnection(String user, String password, String serverName, String databaseName,
                                           int portNumber, boolean encrypt) {
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

