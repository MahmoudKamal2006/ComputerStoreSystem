package com.mahmoud.computerstore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX Application class
 * This launches your GUI application starting with main_view.fxml
 */
public class ComputerStoreApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the main view FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mahmoud/computerstore/view/main_view.fxml"));
            Parent root = loader.load();

            // Create the scene with the main view
            Scene scene = new Scene(root);

            // Set up the primary stage (main window)
            primaryStage.setTitle("Computer Store");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false); // Optional: prevent resizing to keep your 600x400 design
            primaryStage.show();

            System.out.println("🚀 Computer Store Application Started Successfully!");

        } catch (Exception e) {
            System.err.println("❌ Error starting application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}