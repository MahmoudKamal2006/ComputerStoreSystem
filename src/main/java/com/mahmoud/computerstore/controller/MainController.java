package com.mahmoud.computerstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for main_view.fxml
 * Handles navigation from the main menu to other views
 */
public class MainController {

    /**
     * Opens the Inventory view when Inventory button is clicked
     * @param event The button click event
     */
    @FXML
    private void openInventory(ActionEvent event) {
        try {
            // Load the inventory_view.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mahmoud/computerstore/view/inventory_view.fxml"));
            Parent inventoryView = loader.load();

            // Get the current stage (window) from the button that was clicked
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Create a new scene with the inventory view
            Scene inventoryScene = new Scene(inventoryView);

            // Set the new scene on the current stage
            currentStage.setScene(inventoryScene);
            currentStage.setTitle("Computer Store - Inventory");

            System.out.println("✅ Successfully opened Inventory view");

        } catch (IOException e) {
            System.err.println("❌ Error loading inventory view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Opens the PC Builder view when PC Builder button is clicked
     * @param event The button click event
     */
    @FXML
    private void openPCBuilder(ActionEvent event) {
        // TODO: Implement PC Builder view later
        System.out.println("🔧 PC Builder feature - Coming Soon!");

        // For now, just show a message
        // Later we'll load pc_builder_view.fxml here
    }
}