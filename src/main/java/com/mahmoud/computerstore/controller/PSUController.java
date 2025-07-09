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

public class PSUController {

    @FXML
    private TableView psuTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn brandColumn;

    @FXML
    private TableColumn modelColumn;

    @FXML
    private TableColumn wattageColumn;

    @FXML
    private TableColumn efficiencyColumn;

    @FXML
    private TableColumn modularColumn;

    @FXML
    private TableColumn formFactorColumn;

    @FXML
    private TableColumn connectorsColumn;

    @FXML
    private TextField idField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField wattageField;

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addPsu(ActionEvent event) {
        System.out.println("⚡ Add PSU button clicked - Coming Soon!");
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