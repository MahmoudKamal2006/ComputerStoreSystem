package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.Case;
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

public class CaseController implements Initializable {

    // TableView and columns
    @FXML
    private TableView<Case> caseTable;

    @FXML
    private TableColumn<Case, Integer> idColumn;

    @FXML
    private TableColumn<Case, String> brandColumn;

    @FXML
    private TableColumn<Case, String> modelColumn;

    @FXML
    private TableColumn<Case, String> formFactorColumn;

    @FXML
    private TableColumn<Case, Integer> maxGpuLengthColumn;

    @FXML
    private TableColumn<Case, String> driveBaysColumn;

    @FXML
    private TableColumn<Case, String> fanSupportColumn;

    @FXML
    private TableColumn<Case, String> radiatorSupportColumn;

    // TextFields
    @FXML
    private TextField idField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField formFactorField;

    @FXML
    private TextField maxGpuLengthField;

    @FXML
    private TextField driveBaysField;

    @FXML
    private TextField fanSupportField;

    @FXML
    private TextField radiatorSupportField;

    private DataService dataService;
    private ObservableList<Case> caseList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataService = new DataService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactorSupport"));
        maxGpuLengthColumn.setCellValueFactory(new PropertyValueFactory<>("maxGpuLength"));
        driveBaysColumn.setCellValueFactory(new PropertyValueFactory<>("driveBays"));
        fanSupportColumn.setCellValueFactory(new PropertyValueFactory<>("fanSupport"));
        radiatorSupportColumn.setCellValueFactory(new PropertyValueFactory<>("radiatorSupport"));

        loadCaseData();
    }

    private void loadCaseData() {
        try {
            caseList = FXCollections.observableArrayList(dataService.loadCases());
            caseTable.setItems(caseList);
            System.out.println("Loaded " + caseList.size() + " cases into table");
        } catch (Exception e) {
            System.err.println("Error loading case data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/inventory_view.fxml", "Computer Store - Inventory", event);
    }

    @FXML
    private void addCase(ActionEvent event) {
        System.out.println("Add Case button clicked - Coming Soon!");
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