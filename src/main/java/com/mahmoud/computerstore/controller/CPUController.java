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

public class CPUController {

    // TableView and columns - matching your cpu_view.fxml exactly
    @FXML
    private TableView cpuTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn brandColumn;

    @FXML
    private TableColumn modelColumn;

    @FXML
    private TableColumn socketColumn;

    @FXML
    private TableColumn tdpColumn;

    @FXML
    private TableColumn chipsetColumn;

    @FXML
    private TableColumn pcieColumn;

    @FXML
    private TableColumn memoryColumn;

    @FXML
    private TableColumn powerColumn;

    // TextFields - matching your FXML
    @FXML
    private TextField idField;

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addCpu(ActionEvent event) {
        System.out.println("💻 Add CPU button clicked - Coming Soon!");
        // TODO: Implement add CPU functionality
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