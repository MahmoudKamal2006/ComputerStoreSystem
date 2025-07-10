package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.PSU;
import com.mahmoud.computerstore.service.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PSUController implements Initializable {

    // TableView and columns
    @FXML
    private TableView<PSU> psuTable;

    @FXML
    private TableColumn<PSU, Integer> idColumn;

    @FXML
    private TableColumn<PSU, String> brandColumn;

    @FXML
    private TableColumn<PSU, String> modelColumn;

    @FXML
    private TableColumn<PSU, Integer> wattageColumn;

    @FXML
    private TableColumn<PSU, String> efficiencyColumn;

    @FXML
    private TableColumn<PSU, String> modularColumn;

    @FXML
    private TableColumn<PSU, String> formFactorColumn;

    @FXML
    private TableColumn<PSU, String> connectorsColumn;

    // TextFields
    @FXML
    private TextField idField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField wattageField;

    @FXML
    private TextField efficiencyField;

    @FXML
    private TextField modularField;

    @FXML
    private TextField formFactorField;

    @FXML
    private TextField connectorsField;

    private DataService dataService;
    private ObservableList<PSU> psuList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        wattageColumn.setCellValueFactory(new PropertyValueFactory<>("wattage"));
        efficiencyColumn.setCellValueFactory(new PropertyValueFactory<>("efficiencyRating"));
        modularColumn.setCellValueFactory(new PropertyValueFactory<>("modular"));
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        connectorsColumn.setCellValueFactory(new PropertyValueFactory<>("connectors"));

        loadPSUData();
    }

    private void loadPSUData() {
        try {
            psuList = FXCollections.observableArrayList(dataService.loadPSUs());
            psuTable.setItems(psuList);
            System.out.println("Loaded " + psuList.size() + " PSUs into table");
        } catch (Exception e) {
            System.err.println("Error loading PSU data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addPsu(ActionEvent event) {
        try {
            // Get values from text fields
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            String efficiencyRating = efficiencyField.getText().trim();
            String modular = modularField.getText().trim();
            String formFactor = formFactorField.getText().trim();
            String connectors = connectorsField.getText().trim();

            // Validate required fields
            if (brand.isEmpty() || model.isEmpty()) {
                System.err.println("Brand and Model are required fields!");
                return;
            }

            // Default value for numeric field
            int wattage = 650; // Default wattage

            try {
                if (!wattageField.getText().trim().isEmpty()) {
                    wattage = Integer.parseInt(wattageField.getText().trim());
                }
            } catch (NumberFormatException e) {
                System.err.println("Wattage must be a valid number!");
                return;
            }

            // Generate new ID
            int newId = psuList.size() + 1;

            // Create new PSU object with all fields
            PSU newPsu = new PSU(
                    newId,
                    brand,
                    model,
                    wattage,
                    efficiencyRating.isEmpty() ? "80+ Gold" : efficiencyRating,
                    modular.isEmpty() ? "Full" : modular,
                    formFactor.isEmpty() ? "ATX" : formFactor,
                    connectors.isEmpty() ? "24-pin, 8-pin, 6+2-pin" : connectors
            );

            // Add to list and table
            psuList.add(newPsu);
            psuTable.refresh();

            // Save to CSV file
            try {
                dataService.savePSUs(psuList);
                System.out.println("Successfully saved PSU to CSV file!");
            } catch (Exception saveException) {
                System.err.println("Error saving to CSV: " + saveException.getMessage());
                saveException.printStackTrace();
            }

            // Clear the text fields
            clearFields();

            System.out.println("Successfully added new PSU: " + brand + " " + model + " (" + wattage + "W)");

        } catch (Exception e) {
            System.err.println("Error adding PSU: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        brandField.clear();
        modelField.clear();
        wattageField.clear();
        efficiencyField.clear();
        modularField.clear();
        formFactorField.clear();
        connectorsField.clear();
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