package com.se07.util;

import com.se07.controller.services.HoKhauService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Lớp lưu thông tin của người dùng vào 1 file UserData.txt
 */
public class UserInfo {
    /**
     * @return ID của người dùng hiện tại
     */
    public static int getUserId() {
        try {
            FileReader fileReader = new FileReader("UserData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int index = Integer.valueOf(bufferedReader.readLine());
            fileReader.close();
            return index;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @return Tên đăng nhập của ngươ dùng hiện tại
     */
    public static String getUsername() {
        try {
            FileReader fileReader = new FileReader("UserData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String username = bufferedReader.readLine();
            fileReader.close();
            return username;
        } catch (Exception e) {
            return "Khách";
        }
    }

    public static String getMaHoKhau(int id) {
        Connection connection = ConnectionDatabase.getConnection();
        String maHoKhau = "";
        String query = "select ho_gia_dinh_user.maHoKhau from ho_gia_dinh_user where ho_gia_dinh_user.id = '" + id + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                maHoKhau = rs.getString("maHoKhau");
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoKhau;
    }

}