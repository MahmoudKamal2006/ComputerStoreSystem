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
        try {
            // Get values from text fields
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            String formFactorSupport = formFactorField.getText().trim();
            String driveBays = driveBaysField.getText().trim();
            String fanSupport = fanSupportField.getText().trim();
            String radiatorSupport = radiatorSupportField.getText().trim();

            // Validate required fields
            if (brand.isEmpty() || model.isEmpty()) {
                System.err.println("Brand and Model are required fields!");
                return;
            }

            // Default Values
            int maxGpuLength = 300; // Default max GPU length in mm

            try {
                if (!maxGpuLengthField.getText().trim().isEmpty()) {
                    maxGpuLength = Integer.parseInt(maxGpuLengthField.getText().trim());
                }
            } catch (NumberFormatException e) {
                System.err.println("Max GPU Length must be a valid number!");
                return;
            }

            // Generate new ID
            int newId = caseList.size() + 1;

            // Create new Case object with all fields
            Case newCase = new Case(
                    newId,
                    brand,
                    model,
                    formFactorSupport.isEmpty() ? "ATX, Micro-ATX, Mini-ITX" : formFactorSupport,
                    maxGpuLength,
                    driveBays.isEmpty() ? "2x 3.5\", 2x 2.5\"" : driveBays,
                    fanSupport.isEmpty() ? "3x 120mm front, 1x 120mm rear" : fanSupport,
                    radiatorSupport.isEmpty() ? "240mm top, 120mm rear" : radiatorSupport
            );

            // Add to list and table
            caseList.add(newCase);
            caseTable.refresh();

            // Save to CSV file
            try {
                dataService.saveCases(caseList);
                System.out.println("Successfully saved Case to CSV file!");
            } catch (Exception saveException) {
                System.err.println("Error saving to CSV: " + saveException.getMessage());
                saveException.printStackTrace();
            }

            // Clear the text fields
            clearFields();

            System.out.println("Successfully added new Case: " + brand + " " + model);

        } catch (Exception e) {
            System.err.println("Error adding Case: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        brandField.clear();
        modelField.clear();
        formFactorField.clear();
        maxGpuLengthField.clear();
        driveBaysField.clear();
        fanSupportField.clear();
        radiatorSupportField.clear();
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