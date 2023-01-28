package com.se07.util;

import javafx.util.converter.DateStringConverter;

import java.util.Date;

/**
 * Lớp hỗ trợ việc biến đổi kiểu dữ liệu dạng chuỗi ({@code String}) thành kiểu dữ liệu dạng ngày ({@code Date})
 * Kế thừa từ lớp DateStringConverter của java
 */
public class MyDateStringConverter extends DateStringConverter {
    /**
     * @param pattern Chuỗi thể hiện định dạng của ngày đầu ra
     */
    public MyDateStringConverter(final String pattern) {
        super(pattern);
    }

    /**
     * Phương thức biến đổi chuỗi thành ngày
     *
     * @param value Chuỗi cần biến đổi
     * @return Ngày đã được biến đổi từ chuỗi đầu vào
     */
    @Override
    public Date fromString(String value) {
        try {
            return super.fromString(value);
        } catch (RuntimeException re) {
            return null;
        }
    }
}
