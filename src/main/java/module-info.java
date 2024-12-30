module com.example.ca2drinkbeveragesystem {
    requires javafx.controls;
    requires javafx.fxml;

    // Export packages for external access (including test access)
    exports com.example.ca2drinkbeveragesystem;
    exports models;
    exports data;
    exports utils;

    // Open packages for reflection used by JavaFX
    opens controllers to javafx.fxml;
    opens data to javafx.fxml; // This line allows JavaFX to reflectively access data classes
    opens models to javafx.fxml;
    opens utils to javafx.base;
    opens com.example.ca2drinkbeveragesystem to javafx.fxml;
}
