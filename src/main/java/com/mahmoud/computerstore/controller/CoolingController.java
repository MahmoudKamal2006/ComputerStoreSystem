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
    private TableColumn<Cooling, String> fanSizeColumn;

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
        rpmColumn.setCellValueFactory(new PropertyValueFactory<>("maxRpm"));

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
        System.out.println("Add Cooling button clicked - Coming Soon!");
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