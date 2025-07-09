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
        System.out.println("Add Storage button clicked - Coming Soon!");
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