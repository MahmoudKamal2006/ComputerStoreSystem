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
 * Controller for inventory_view.fxml
 * Handles navigation from inventory view to component views and back to main
 */
public class InventoryController {

    /**
     * Returns to the main view when Home button is clicked
     * @param event The button click event
     */
    @FXML
    private void openHome(ActionEvent event) {
        try {
            // Load the main_view.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mahmoud/computerstore/view/main_view.fxml"));
            Parent mainView = loader.load();

            // Get the current stage (window) from the button that was clicked
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Create a new scene with the main view
            Scene mainScene = new Scene(mainView);

            // Set the new scene on the current stage
            currentStage.setScene(mainScene);
            currentStage.setTitle("Computer Store");

            System.out.println("🏠 Successfully returned to Main view");

        } catch (IOException e) {
            System.err.println("❌ Error loading main view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Component view handlers - we'll implement these when you create the component views

    @FXML
    private void openCPUs(ActionEvent event) {
        System.out.println("💻 Opening CPUs view - Coming Soon!");
        // TODO: Load cpu_view.fxml
    }

    @FXML
    private void openGPUs(ActionEvent event) {
        System.out.println("🎮 Opening GPUs view - Coming Soon!");
        // TODO: Load gpu_view.fxml
    }

    @FXML
    private void openRAMs(ActionEvent event) {
        System.out.println("🧠 Opening RAMs view - Coming Soon!");
        // TODO: Load ram_view.fxml
    }

    @FXML
    private void openMotherboards(ActionEvent event) {
        System.out.println("🔌 Opening Motherboards view - Coming Soon!");
        // TODO: Load motherboard_view.fxml
    }

    @FXML
    private void openStorages(ActionEvent event) {
        System.out.println("💾 Opening Storages view - Coming Soon!");
        // TODO: Load storage_view.fxml
    }

    @FXML
    private void openPSUs(ActionEvent event) {
        System.out.println("⚡ Opening PSUs view - Coming Soon!");
        // TODO: Load psu_view.fxml
    }

    @FXML
    private void openCases(ActionEvent event) {
        System.out.println("📦 Opening Cases view - Coming Soon!");
        // TODO: Load case_view.fxml
    }

    @FXML
    private void openCoolers(ActionEvent event) {
        System.out.println("❄️ Opening Coolers view - Coming Soon!");
        // TODO: Load cooling_view.fxml
    }
}