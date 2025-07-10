package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.GPU;
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

public class GPUController implements Initializable {

    // TableView and columns
    @FXML
    private TableView<GPU> gpuTable;

    @FXML
    private TableColumn<GPU, Integer> idColumn;

    @FXML
    private TableColumn<GPU, String> brandColumn;

    @FXML
    private TableColumn<GPU, String> modelColumn;

    @FXML
    private TableColumn<GPU, String> interfaceColumn;

    @FXML
    private TableColumn<GPU, Integer> tdpColumn;

    @FXML
    private TableColumn<GPU, String> vramColumn;

    @FXML
    private TableColumn<GPU, String> powerConnectorsColumn;

    @FXML
    private TableColumn<GPU, Integer> lengthColumn;

    // TextFields
    @FXML
    private TextField idField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField interfaceField;

    @FXML
    private TextField tdpField;

    @FXML
    private TextField vramField;

    @FXML
    private TextField powerConnectorsField;

    @FXML
    private TextField lengthField;

    private DataService dataService;
    private ObservableList<GPU> gpuList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        // CORRECTED PropertyValueFactory mappings
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        interfaceColumn.setCellValueFactory(new PropertyValueFactory<>("interfaceType"));
        tdpColumn.setCellValueFactory(new PropertyValueFactory<>("tdp"));
        vramColumn.setCellValueFactory(new PropertyValueFactory<>("vram"));
        powerConnectorsColumn.setCellValueFactory(new PropertyValueFactory<>("powerConnectors"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        loadGPUData();
    }

    private void loadGPUData() {
        try {
            gpuList = FXCollections.observableArrayList(dataService.loadGPUs());
            gpuTable.setItems(gpuList);
            System.out.println("Loaded " + gpuList.size() + " GPUs into table");
        } catch (Exception e) {
            System.err.println("Error loading GPU data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void addGpu(ActionEvent event) {
        try {
            // Get values from text fields
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            String interfaceType = interfaceField.getText().trim();
            String vram = vramField.getText().trim();
            String powerConnectors = powerConnectorsField.getText().trim();

            // Validate required fields
            if (brand.isEmpty() || model.isEmpty()) {
                System.err.println("Brand and Model are required fields!");
                return;
            }

            // Default Values
            int tdp = 150; // Default TDP for GPU
            int length = 280; // Default length in mm

            try {
                if (!tdpField.getText().trim().isEmpty()) {
                    tdp = Integer.parseInt(tdpField.getText().trim());
                }
                if (!lengthField.getText().trim().isEmpty()) {
                    length = Integer.parseInt(lengthField.getText().trim());
                }
            } catch (NumberFormatException e) {
                System.err.println("TDP and Length must be valid numbers!");
                return;
            }

            // Generate new ID
            int newId = gpuList.size() + 1;

            // Create new GPU object with all fields
            GPU newGpu = new GPU(
                    newId,
                    brand,
                    model,
                    interfaceType.isEmpty() ? "PCIe 4.0 x16" : interfaceType,
                    tdp,
                    vram.isEmpty() ? "8GB" : vram,
                    powerConnectors.isEmpty() ? "1x 8-pin" : powerConnectors,
                    length
            );

            // Add to list and table
            gpuList.add(newGpu);
            gpuTable.refresh();

            // Save to CSV file
            try {
                dataService.saveGPUs(gpuList);
                System.out.println("Successfully saved GPU to CSV file!");
            } catch (Exception saveException) {
                System.err.println("Error saving to CSV: " + saveException.getMessage());
                saveException.printStackTrace();
            }

            // Clear the text fields
            clearFields();

            System.out.println("Successfully added new GPU: " + brand + " " + model);

        } catch (Exception e) {
            System.err.println("Error adding GPU: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        brandField.clear();
        modelField.clear();
        interfaceField.clear();
        tdpField.clear();
        vramField.clear();
        powerConnectorsField.clear();
        lengthField.clear();
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
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