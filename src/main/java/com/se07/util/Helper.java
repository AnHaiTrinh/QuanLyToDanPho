package com.se07.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Helper {
    public static int getUserId(){
        try {
            FileReader fileReader = new FileReader("src/main/resources/UserData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int index = Integer.valueOf(bufferedReader.readLine());
            System.out.println(index);
            return index;
        } catch (Exception e) {
            return -1;
        }
    }
}
