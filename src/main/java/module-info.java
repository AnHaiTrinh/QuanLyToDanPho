module com.se07.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.se07.main to javafx.fxml;
    exports com.se07.main;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;
    opens com.se07.view to javafx.fxml;
    exports com.se07.view;
    opens com.se07.controller.controllers to javafx.fxml;
    exports com.se07.controller.controllers;


}