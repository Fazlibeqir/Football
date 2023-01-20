module com.prototype.football {
    requires javafx.controls;
    requires javafx.fxml;
    requires playn.core;


    opens com.prototype.football to javafx.fxml;
    exports com.prototype.football;
}