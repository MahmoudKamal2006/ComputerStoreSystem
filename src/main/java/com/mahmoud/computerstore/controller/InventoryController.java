package com.mahmoud.computerstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryController {

    @FXML
    private void openHome(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/main_view.fxml", "Computer Store", event);
    }

    @FXML
    private void openCPUs(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/cpu_view.fxml", "Computer Store - CPUs", event);
    }

    @FXML
    private void openGPUs(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/gpu_view.fxml", "Computer Store - GPUs", event);
    }

    @FXML
    private void openRAMs(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/ram_view.fxml", "Computer Store - RAM", event);
    }

    @FXML
    private void openMotherboards(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/motherboard_view.fxml", "Computer Store - Motherboards", event);
    }

    @FXML
    private void openStorages(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/storage_view.fxml", "Computer Store - Storage", event);
    }

    @FXML
    private void openPSUs(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/psu_view.fxml", "Computer Store - PSUs", event);
    }

    @FXML
    private void openCases(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/case_view.fxml", "Computer Store - Cases", event);
    }

    @FXML
    private void openCoolers(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/cooling_view.fxml", "Computer Store - Cooling", event);
    }

    private void loadView(String fxmlPath, String title, ActionEvent event) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(view));
            stage.setTitle(title);
        } catch (Exception e) {
            System.err.println("Error loading view: " + fxmlPath);
            e.printStackTrace();
        }
    }
}