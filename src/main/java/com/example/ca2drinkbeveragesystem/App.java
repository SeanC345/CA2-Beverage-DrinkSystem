package com.example.ca2drinkbeveragesystem;

import java.io.IOException;
import java.io.Serializable;

import data.CustomHashTable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Drink;
import models.Ingredient;
import utils.CustomLinkedList;


public class App extends Application implements Serializable {

    private static Scene scene;
    public static CustomHashTable<String, Drink> drinksTable = new CustomHashTable<>(10000);
    public static CustomHashTable<String, Ingredient> ingredientsTable = new CustomHashTable<>(10000);
    public static Drink selectedDrink;
    public static CustomLinkedList<Drink> drinkResults;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/MainView.fxml"));
        scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setResizable(false);
        stage.setTitle("Beverage System");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println("Loading FXML: " + fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void switchScene(String fxmlFile) {
        try {
            setRoot(fxmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSelectedDrink(Drink drink) {
        selectedDrink = drink;
    }

    public static void setDrinkResults(CustomLinkedList<Drink> results) {
        drinkResults = results;
    }
}