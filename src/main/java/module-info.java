module com.se07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;

    exports com.se07.main;
    opens com.se07.main to javafx.fxml;
}