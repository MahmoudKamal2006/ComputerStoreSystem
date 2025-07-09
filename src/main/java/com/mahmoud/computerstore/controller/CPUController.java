package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.CPU;
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

public class CPUController implements Initializable {

    // TableView and columns
    @FXML
    private TableView<CPU> cpuTable;

    @FXML
    private TableColumn<CPU, Integer> idColumn;

    @FXML
    private TableColumn<CPU, String> brandColumn;

    @FXML
    private TableColumn<CPU, String> modelColumn;

    @FXML
    private TableColumn<CPU, String> socketColumn;

    @FXML
    private TableColumn<CPU, Integer> tdpColumn;

    @FXML
    private TableColumn<CPU, String> chipsetColumn;

    @FXML
    private TableColumn<CPU, String> pcieColumn;

    @FXML
    private TableColumn<CPU, String> memoryColumn;

    @FXML
    private TableColumn<CPU, Integer> powerColumn;

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
    private TextField tdpField;

    @FXML
    private TextField chipsetField;

    private DataService dataService;
    private ObservableList<CPU> cpuList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        // Set up table columns to bind to CPU object properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        socketColumn.setCellValueFactory(new PropertyValueFactory<>("socket"));
        tdpColumn.setCellValueFactory(new PropertyValueFactory<>("tdp"));
        chipsetColumn.setCellValueFactory(new PropertyValueFactory<>("chipsetCompatibility"));
        pcieColumn.setCellValueFactory(new PropertyValueFactory<>("pcieVersion"));
        memoryColumn.setCellValueFactory(new PropertyValueFactory<>("memorySupport"));
        powerColumn.setCellValueFactory(new PropertyValueFactory<>("powerConsumption"));

        // Load data into table
        loadCPUData();
    }

    private void loadCPUData() {
        try {
            // Load CPUs from CSV files using DataService
            cpuList = FXCollections.observableArrayList(dataService.loadCPUs());
            cpuTable.setItems(cpuList);
            System.out.println("Loaded " + cpuList.size() + " CPUs into table");
        } catch (Exception e) {
            System.err.println("Error loading CPU data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addCpu(ActionEvent event) {
        System.out.println("💻 Add CPU button clicked - Coming Soon!");
        // TODO: You can implement adding new CPUs here
        // For example:
        // String brand = brandField.getText();
        // String model = modelField.getText();
        // Create new CPU object and add to list
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