package com.se07.util;

import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextInputControl;

public class ComponentVisibility {
    public static void change(ComboBoxBase comboBoxBase, boolean value) {
        comboBoxBase.setDisable(!value);
        comboBoxBase.setEditable(value);
        comboBoxBase.setVisible(value);
    }

    public static void change(TextInputControl textInputControl, boolean value) {
        textInputControl.setDisable(!value);
        textInputControl.setEditable(value);
        textInputControl.setVisible(value);
    }
}
