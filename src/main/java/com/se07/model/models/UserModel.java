package com.se07.model.models;

public class UserModel {
    private int ID;
    private String username;
    private String password;
    private int roles;

    public UserModel(int ID, String username, String password, int roles) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }
}
