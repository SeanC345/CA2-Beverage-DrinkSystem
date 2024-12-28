// package controllers;

// import com.example.ca2drinkbeveragesystem.App;
// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.ListView;
// import javafx.scene.control.TextField;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;

// import java.util.List;
// import java.util.stream.Collectors;

// public class SearchResultsController {

//     @FXML
//     private TextField searchField;

//     @FXML
//     private Button searchButton;

//     @FXML
//     private Button backButton;

//     @FXML
//     private ListView<String> resultsList;

//     private ObservableList<String> searchResults = FXCollections.observableArrayList();

//     @FXML
//     public void initialize() {
//         // Initialize the ListView with an empty data set
//         resultsList.setItems(searchResults);
//         buttonEvents();
//     }

//     private void performSearch() {
//         String query = searchField.getText().toLowerCase();

//         // Mock data for demonstration; replace with real search logic
//         List<String> allData = List.of(
//                 "Mojito", "Old Fashioned", "Martini", "Whiskey Sour",
//                 "Bloody Mary", "Pina Colada", "Margarita"
//         );

//         // Filter data based on the search query
//         List<String> filteredResults = allData.stream()
//                 .filter(item -> item.toLowerCase().contains(query))
//                 .collect(Collectors.toList());

//         // Update the ListView with the filtered results
//         searchResults.setAll(filteredResults);
//     }

//     private void goBack() {
//         // Navigate back to the drink menu
//         App.switchScene("/views/DrinkMenuView");
//     }

//     private void buttonEvents() {
//         searchButton.setOnAction(e -> performSearch());
//         backButton.setOnAction(e -> goBack());
//     }
// }
