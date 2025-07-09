package com.mahmoud.computerstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GPUController {

    @FXML
    private TableView gpuTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn brandColumn;

    @FXML
    private TableColumn modelColumn;

    @FXML
    private TableColumn typeColumn;

    @FXML
    private TableColumn socketColumn;

    @FXML
    private TableColumn fanSizeColumn;

    @FXML
    private TableColumn radiatorColumn;

    @FXML
    private TableColumn noiseColumn;

    @FXML
    private TableColumn rpmColumn;

    @FXML
    private TextField idField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField socketField;

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addGpu(ActionEvent event) {
        System.out.println("🎮 Add GPU button clicked - Coming Soon!");
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