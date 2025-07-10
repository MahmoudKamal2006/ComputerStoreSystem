package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.RAM;
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

public class RAMController implements Initializable {

    // TableView and columns
    @FXML
    private TableView<RAM> ramTable;

    @FXML
    private TableColumn<RAM, Integer> idColumn;

    @FXML
    private TableColumn<RAM, String> brandColumn;

    @FXML
    private TableColumn<RAM, String> modelColumn;

    @FXML
    private TableColumn<RAM, String> typeColumn;

    @FXML
    private TableColumn<RAM, String> capacityColumn;

    @FXML
    private TableColumn<RAM, String> speedColumn;

    @FXML
    private TableColumn<RAM, Integer> modulesColumn;

    @FXML
    private TableColumn<RAM, String> voltageColumn;

    // TextFields
    @FXML
    private TextField idField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField capacityField;

    @FXML
    private TextField speedField;

    @FXML
    private TextField modulesField;

    @FXML
    private TextField voltageField;

    private DataService dataService;
    private ObservableList<RAM> ramList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        modulesColumn.setCellValueFactory(new PropertyValueFactory<>("modules"));
        voltageColumn.setCellValueFactory(new PropertyValueFactory<>("voltage"));

        loadRAMData();
    }

    private void loadRAMData() {
        try {
            ramList = FXCollections.observableArrayList(dataService.loadRAM());
            ramTable.setItems(ramList);
            System.out.println("Loaded " + ramList.size() + " RAM modules into table");
        } catch (Exception e) {
            System.err.println("Error loading RAM data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addRam(ActionEvent event) {
        try {
            // Get values from text fields
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            String type = typeField.getText().trim();
            String capacity = capacityField.getText().trim();
            String speed = speedField.getText().trim();
            String voltage = voltageField.getText().trim();

            // Validate required fields
            if (brand.isEmpty() || model.isEmpty()) {
                System.err.println("Brand and Model are required fields!");
                return;
            }

            // Default value for numeric field
            int modules = 2; // Default number of modules (dual channel)

            try {
                if (!modulesField.getText().trim().isEmpty()) {
                    modules = Integer.parseInt(modulesField.getText().trim());
                }
            } catch (NumberFormatException e) {
                System.err.println("Modules must be a valid number!");
                return;
            }

            // Generate new ID
            int newId = ramList.size() + 1;

            // Create new RAM object with all fields
            RAM newRam = new RAM(
                    newId,
                    brand,
                    model,
                    type.isEmpty() ? "DDR5" : type,
                    capacity.isEmpty() ? "16GB" : capacity,
                    speed.isEmpty() ? "3200MHz" : speed,
                    modules,
                    voltage.isEmpty() ? "1.35V" : voltage
            );

            // Add to list and table
            ramList.add(newRam);
            ramTable.refresh();

            // Save to CSV file
            try {
                dataService.saveRAM(ramList);
                System.out.println("Successfully saved RAM to CSV file!");
            } catch (Exception saveException) {
                System.err.println("Error saving to CSV: " + saveException.getMessage());
                saveException.printStackTrace();
            }

            // Clear the text fields
            clearFields();

            System.out.println("Successfully added new RAM: " + brand + " " + model + " (" + type + ")");

        } catch (Exception e) {
            System.err.println("Error adding RAM: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        brandField.clear();
        modelField.clear();
        typeField.clear();
        capacityField.clear();
        speedField.clear();
        modulesField.clear();
        voltageField.clear();
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