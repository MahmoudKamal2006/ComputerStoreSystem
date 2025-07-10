package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.Storage;
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

public class StorageController implements Initializable {

    // TableView and columns
    @FXML
    private TableView<Storage> storageTable;

    @FXML
    private TableColumn<Storage, Integer> idColumn;

    @FXML
    private TableColumn<Storage, String> brandColumn;

    @FXML
    private TableColumn<Storage, String> modelColumn;

    @FXML
    private TableColumn<Storage, String> typeColumn;

    @FXML
    private TableColumn<Storage, String> capacityColumn;

    @FXML
    private TableColumn<Storage, String> interfaceColumn;

    @FXML
    private TableColumn<Storage, String> formFactorColumn;

    @FXML
    private TableColumn<Storage, String> readSpeedColumn;

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
    private TextField interfaceField;

    @FXML
    private TextField formFactorField;

    @FXML
    private TextField readSpeedField;

    private DataService dataService;
    private ObservableList<Storage> storageList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        interfaceColumn.setCellValueFactory(new PropertyValueFactory<>("interfaceType"));
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        readSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("readSpeed"));

        loadStorageData();
    }

    private void loadStorageData() {
        try {
            storageList = FXCollections.observableArrayList(dataService.loadStorage());
            storageTable.setItems(storageList);
            System.out.println("Loaded " + storageList.size() + " storage devices into table");
        } catch (Exception e) {
            System.err.println("Error loading storage data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addStorage(ActionEvent event) {
        try {
            // Get values from text fields
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            String type = typeField.getText().trim();
            String capacity = capacityField.getText().trim();
            String interfaceType = interfaceField.getText().trim();
            String formFactor = formFactorField.getText().trim();
            String readSpeed = readSpeedField.getText().trim();

            // Validate required fields
            if (brand.isEmpty() || model.isEmpty()) {
                System.err.println("Brand and Model are required fields!");
                return;
            }

            // Generate new ID
            int newId = storageList.size() + 1;

            // Determine write speed based on type and read speed for more realistic defaults
            String writeSpeed;
            if (type.equalsIgnoreCase("NVMe") || type.equalsIgnoreCase("SSD")) {
                // For SSDs/NVMe, write speed is typically 80-90% of read speed
                try {
                    int readSpeedValue = Integer.parseInt(readSpeed.replaceAll("[^0-9]", ""));
                    int writeSpeedValue = (int)(readSpeedValue * 0.85); // 85% of read speed
                    writeSpeed = writeSpeedValue + "MB/s";
                } catch (NumberFormatException e) {
                    writeSpeed = "3000MB/s"; // Default for NVMe/SSD
                }
            } else {
                // For HDDs, write speed is usually similar or slightly lower than read speed
                writeSpeed = readSpeed.isEmpty() ? "140MB/s" : readSpeed;
            }

            // Create new Storage object with all fields (including calculated writeSpeed)
            Storage newStorage = new Storage(
                    newId,
                    brand,
                    model,
                    type.isEmpty() ? "NVMe" : type,
                    capacity.isEmpty() ? "1TB" : capacity,
                    interfaceType.isEmpty() ? "NVMe" : interfaceType,
                    formFactor.isEmpty() ? "M.2" : formFactor,
                    readSpeed.isEmpty() ? "3500MB/s" : readSpeed,
                    writeSpeed // Calculated or default write speed
            );

            // Add to list and table
            storageList.add(newStorage);
            storageTable.refresh();

            // Save to CSV file
            try {
                dataService.saveStorage(storageList);
                System.out.println("Successfully saved Storage to CSV file!");
            } catch (Exception saveException) {
                System.err.println("Error saving to CSV: " + saveException.getMessage());
                saveException.printStackTrace();
            }

            // Clear the text fields
            clearFields();

            System.out.println("Successfully added new Storage: " + brand + " " + model + " (" + type + ", " + capacity + ")");

        } catch (Exception e) {
            System.err.println("Error adding Storage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        brandField.clear();
        modelField.clear();
        typeField.clear();
        capacityField.clear();
        interfaceField.clear();
        formFactorField.clear();
        readSpeedField.clear();
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