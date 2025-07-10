package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.Motherboard;
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

public class MotherboardController implements Initializable {

    // TableView and columns
    @FXML
    private TableView<Motherboard> motherboardTable;

    @FXML
    private TableColumn<Motherboard, Integer> idColumn;

    @FXML
    private TableColumn<Motherboard, String> brandColumn;

    @FXML
    private TableColumn<Motherboard, String> modelColumn;

    @FXML
    private TableColumn<Motherboard, String> socketColumn;

    @FXML
    private TableColumn<Motherboard, String> chipsetColumn;

    @FXML
    private TableColumn<Motherboard, String> formFactorColumn;

    @FXML
    private TableColumn<Motherboard, Integer> memorySlotsColumn;

    @FXML
    private TableColumn<Motherboard, String> memoryTypeColumn;

    @FXML
    private TableColumn<Motherboard, String> networkColumn;

    // TextFields
    @FXML
    private TextField idField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField socketField;

    @FXML
    private TextField chipsetField;

    @FXML
    private TextField formFactorField;

    @FXML
    private TextField memorySlotsField;

    @FXML
    private TextField memoryTypeField;

    @FXML
    private TextField networkField;

    private DataService dataService;
    private ObservableList<Motherboard> motherboardList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        socketColumn.setCellValueFactory(new PropertyValueFactory<>("socket"));
        chipsetColumn.setCellValueFactory(new PropertyValueFactory<>("chipset"));
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        memorySlotsColumn.setCellValueFactory(new PropertyValueFactory<>("memorySlots"));
        memoryTypeColumn.setCellValueFactory(new PropertyValueFactory<>("memoryType"));
        networkColumn.setCellValueFactory(new PropertyValueFactory<>("network"));

        loadMotherboardData();
    }

    private void loadMotherboardData() {
        try {
            motherboardList = FXCollections.observableArrayList(dataService.loadMotherboards());
            motherboardTable.setItems(motherboardList);
            System.out.println("Loaded " + motherboardList.size() + " motherboards into table");
        } catch (Exception e) {
            System.err.println("Error loading motherboard data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addMotherboard(ActionEvent event) {
        try {
            // Get values from text fields
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            String socket = socketField.getText().trim();
            String chipset = chipsetField.getText().trim();
            String formFactor = formFactorField.getText().trim();
            String memoryType = memoryTypeField.getText().trim();
            String network = networkField.getText().trim();

            // Validate required fields
            if (brand.isEmpty() || model.isEmpty() || socket.isEmpty()) {
                System.err.println("Brand, Model, and Socket are required fields!");
                return;
            }

            // Default value for numeric field
            int memorySlots = 4; // Default memory slots

            try {
                if (!memorySlotsField.getText().trim().isEmpty()) {
                    memorySlots = Integer.parseInt(memorySlotsField.getText().trim());
                }
            } catch (NumberFormatException e) {
                System.err.println("Memory Slots must be a valid number!");
                return;
            }

            // Generate new ID
            int newId = motherboardList.size() + 1;

            // Create new Motherboard object with all fields (including defaults for missing UI fields)
            Motherboard newMotherboard = new Motherboard(
                    newId,
                    brand,
                    model,
                    socket,
                    chipset.isEmpty() ? "B760" : chipset,
                    formFactor.isEmpty() ? "ATX" : formFactor,
                    memorySlots,
                    "128GB", // Default maxMemory (not in UI)
                    memoryType.isEmpty() ? "DDR5" : memoryType,
                    "1x PCIe 5.0 x16, 2x PCIe 4.0 x1", // Default pcieSlots (not in UI)
                    "6x SATA III, 2x M.2", // Default storageInterfaces (not in UI)
                    "4x USB 3.2, 2x USB 2.0", // Default usbPorts (not in UI)
                    network.isEmpty() ? "Ethernet" : network
            );

            // Add to list and table
            motherboardList.add(newMotherboard);
            motherboardTable.refresh();

            // Save to CSV file
            try {
                dataService.saveMotherboards(motherboardList);
                System.out.println("Successfully saved Motherboard to CSV file!");
            } catch (Exception saveException) {
                System.err.println("Error saving to CSV: " + saveException.getMessage());
                saveException.printStackTrace();
            }

            // Clear the text fields
            clearFields();

            System.out.println("Successfully added new Motherboard: " + brand + " " + model + " (" + socket + ")");

        } catch (Exception e) {
            System.err.println("Error adding Motherboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        brandField.clear();
        modelField.clear();
        socketField.clear();
        chipsetField.clear();
        formFactorField.clear();
        memorySlotsField.clear();
        memoryTypeField.clear();
        networkField.clear();
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