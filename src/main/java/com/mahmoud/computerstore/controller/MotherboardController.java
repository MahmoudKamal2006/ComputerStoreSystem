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
        networkColumn.setCellValueFactory(new PropertyValueFactory<>("networkController"));

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
        System.out.println("Add Motherboard button clicked - Coming Soon!");
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