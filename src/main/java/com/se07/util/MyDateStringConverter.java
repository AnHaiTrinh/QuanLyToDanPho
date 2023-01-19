package com.se07.util;

import javafx.util.converter.DateStringConverter;

import java.util.Date;

public class MyDateStringConverter extends DateStringConverter {
    public MyDateStringConverter(final String pattern) {
        super(pattern);
    }

    @Override
    public Date fromString(String value) {
        try {
            return super.fromString(value);
        } catch (RuntimeException re) {
            return null;
        }
    }
}
