module com.example.ca2drinkbeveragesystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ca2drinkbeveragesystem to javafx.fxml;
    exports com.example.ca2drinkbeveragesystem;
}