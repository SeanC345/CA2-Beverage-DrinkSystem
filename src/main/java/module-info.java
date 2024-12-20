module com.example.ca2drinkbeveragesystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens controllers to javafx.fxml;
    opens data to javafx.fxml;
    opens models to javafx.fxml;
    opens utils to javafx.base;
    opens com.example.ca2drinkbeveragesystem to javafx.fxml;
    exports com.example.ca2drinkbeveragesystem;
    exports models;
}