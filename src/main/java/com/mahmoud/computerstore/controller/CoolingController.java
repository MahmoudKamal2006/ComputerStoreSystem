package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.Cooling;
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

public class CoolingController implements Initializable {

    // TableView and columns
    @FXML
    private TableView<Cooling> coolingTable;

    @FXML
    private TableColumn<Cooling, Integer> idColumn;

    @FXML
    private TableColumn<Cooling, String> brandColumn;

    @FXML
    private TableColumn<Cooling, String> modelColumn;

    @FXML
    private TableColumn<Cooling, String> typeColumn;

    @FXML
    private TableColumn<Cooling, String> socketColumn;

    @FXML
    private TableColumn<Cooling, Integer> fanSizeColumn;

    @FXML
    private TableColumn<Cooling, String> radiatorColumn;

    @FXML
    private TableColumn<Cooling, String> noiseColumn;

    @FXML
    private TableColumn<Cooling, Integer> rpmColumn;

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
    private TextField socketField;

    @FXML
    private TextField fanSizeField;

    @FXML
    private TextField radiatorField;

    @FXML
    private TextField noiseField;

    @FXML
    private TextField rpmField;

    private DataService dataService;
    private ObservableList<Cooling> coolingList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        socketColumn.setCellValueFactory(new PropertyValueFactory<>("socketCompatibility"));
        fanSizeColumn.setCellValueFactory(new PropertyValueFactory<>("fanSize"));
        radiatorColumn.setCellValueFactory(new PropertyValueFactory<>("radiatorSize"));
        noiseColumn.setCellValueFactory(new PropertyValueFactory<>("noiseLevel"));
        rpmColumn.setCellValueFactory(new PropertyValueFactory<>("rpm"));

        loadCoolingData();
    }

    private void loadCoolingData() {
        try {
            coolingList = FXCollections.observableArrayList(dataService.loadCooling());
            coolingTable.setItems(coolingList);
            System.out.println("Loaded " + coolingList.size() + " cooling solutions into table");
        } catch (Exception e) {
            System.err.println("Error loading cooling data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addCooling(ActionEvent event) {
        try {
            // Get values from text fields
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            String type = typeField.getText().trim();
            String socketCompatibility = socketField.getText().trim();
            String radiatorSize = radiatorField.getText().trim();
            String noiseLevel = noiseField.getText().trim();

            // Validate required fields
            if (brand.isEmpty() || model.isEmpty()) {
                System.err.println("Brand and Model are required fields!");
                return;
            }

            // Default Values for numeric fields
            int fanSize = 120; // Default fan size in mm
            int rpm = 1500; // Default RPM

            try {
                if (!fanSizeField.getText().trim().isEmpty()) {
                    fanSize = Integer.parseInt(fanSizeField.getText().trim());
                }
                if (!rpmField.getText().trim().isEmpty()) {
                    rpm = Integer.parseInt(rpmField.getText().trim());
                }
            } catch (NumberFormatException e) {
                System.err.println("Fan Size and RPM must be valid numbers!");
                return;
            }

            // Generate new ID
            int newId = coolingList.size() + 1;

            // Create new Cooling object with all fields
            Cooling newCooling = new Cooling(
                    newId,
                    brand,
                    model,
                    type.isEmpty() ? "Air" : type,
                    socketCompatibility.isEmpty() ? "AM4, AM5, LGA1700" : socketCompatibility,
                    fanSize,
                    radiatorSize.isEmpty() ? "0mm" : radiatorSize,
                    noiseLevel.isEmpty() ? "25dB" : noiseLevel,
                    rpm
            );

            // Add to list and table
            coolingList.add(newCooling);
            coolingTable.refresh();

            // Save to CSV file
            try {
                dataService.saveCooling(coolingList);
                System.out.println("Successfully saved Cooling to CSV file!");
            } catch (Exception saveException) {
                System.err.println("Error saving to CSV: " + saveException.getMessage());
                saveException.printStackTrace();
            }

            // Clear the text fields
            clearFields();

            System.out.println("Successfully added new Cooling: " + brand + " " + model + " (" + type + ")");

        } catch (Exception e) {
            System.err.println("Error adding Cooling: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        brandField.clear();
        modelField.clear();
        typeField.clear();
        socketField.clear();
        fanSizeField.clear();
        radiatorField.clear();
        noiseField.clear();
        rpmField.clear();
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