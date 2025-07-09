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
    private TableColumn<GPU, String> typeColumn;

    @FXML
    private TableColumn<GPU, Integer> socketColumn;

    @FXML
    private TableColumn<GPU, String> fanSizeColumn;

    @FXML
    private TableColumn<GPU, String> radiatorColumn;

    @FXML
    private TableColumn<GPU, Integer> noiseColumn;

    @FXML
    private TableColumn<GPU, Integer> rpmColumn;

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

    private DataService dataService;
    private ObservableList<GPU> gpuList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("interfaceType"));
        socketColumn.setCellValueFactory(new PropertyValueFactory<>("tdp"));
        fanSizeColumn.setCellValueFactory(new PropertyValueFactory<>("vram"));
        radiatorColumn.setCellValueFactory(new PropertyValueFactory<>("powerConnectors"));
        noiseColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        rpmColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

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
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addGpu(ActionEvent event) {
        System.out.println("Add GPU button clicked - Coming Soon!");
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