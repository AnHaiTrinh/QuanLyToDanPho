package com.se07.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class UserInfo {
    public static int getUserId() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/UserData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int index = Integer.valueOf(bufferedReader.readLine());
            return index;
        } catch (Exception e) {
            return -1;
        }
    }

    public static String getUsername() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/UserData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String username = bufferedReader.readLine();
            return username;
        } catch (Exception e) {
            return "Khách";
        }
    }
}
