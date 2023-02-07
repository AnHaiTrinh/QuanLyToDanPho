package com.se07.util;

import javafx.util.converter.IntegerStringConverter;

public class MyIntegerStringConverter extends IntegerStringConverter {
    @Override
    public Integer fromString(String value) {
        try {
            return super.fromString(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
