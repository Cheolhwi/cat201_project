module com.example.fxtest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fxtest to javafx.fxml;
    exports com.example.fxtest;
}