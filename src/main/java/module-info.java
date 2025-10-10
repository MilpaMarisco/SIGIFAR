module com.sigifar {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;
    requires java.sql;

    opens com.sigifar.app to javafx.fxml;
    exports com.sigifar.app;
}
