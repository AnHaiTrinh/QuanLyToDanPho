package com.se07.controller.services;

import com.se07.model.models.UserModel;
import com.se07.util.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserService {
    public boolean insertNewUser(UserModel userModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into user(ID, username, password, role) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userModel.getID());
            statement.setString(2, userModel.getUsername());
            statement.setString(3, userModel.getPassword());
            statement.setInt(4, userModel.getRole());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
