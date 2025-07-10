package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PCBuilderController implements Initializable {

    // FXML Elements
    @FXML private Label titleLabel;
    @FXML private ProgressBar progressBar;
    @FXML private Label selectionTitleLabel;
    @FXML private Label stepInfoLabel;

    // Build Summary Labels
    @FXML private Label cpuLabel;
    @FXML private Label motherboardLabel;
    @FXML private Label coolingLabel;
    @FXML private Label ramLabel;
    @FXML private Label storageLabel;
    @FXML private Label gpuLabel;
    @FXML private Label caseLabel;
    @FXML private Label psuLabel;

    // Component Selection
    @FXML private VBox componentListVBox;
    @FXML private ToggleGroup componentToggleGroup;
    @FXML private ScrollPane componentScrollPane;

    // Navigation Buttons
    @FXML private Button backButton;
    @FXML private Button nextButton;
    @FXML private Button skipButton;
    @FXML private Button homeButton;

    // Controller Logic
    private BuildController buildController;
    private int currentStep = 1;
    private final int TOTAL_STEPS = 8;

    // Step constants
    private static final int STEP_CPU = 1;
    private static final int STEP_MOTHERBOARD = 2;
    private static final int STEP_COOLING = 3;
    private static final int STEP_RAM = 4;
    private static final int STEP_STORAGE = 5;
    private static final int STEP_GPU = 6;
    private static final int STEP_CASE = 7;
    private static final int STEP_PSU = 8;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildController = new BuildController();
        initializeUI();
        loadCurrentStep();
    }

    private void initializeUI() {
        // Initially disable back button (we're on step 1)
        backButton.setDisable(true);

        // Hide skip button initially (will show for optional components)
        skipButton.setVisible(false);

        updateStepDisplay();
    }

    private void updateStepDisplay() {
        // Update title and progress
        String componentName = getComponentNameForStep(currentStep);
        titleLabel.setText("PC Builder - Step " + currentStep + " of " + TOTAL_STEPS + ": " + componentName);
        progressBar.setProgress((double) currentStep / TOTAL_STEPS);
        selectionTitleLabel.setText("Select " + componentName + ":");
        stepInfoLabel.setText(getStepInfoText(currentStep));

        // Update navigation buttons
        backButton.setDisable(currentStep == 1);
        skipButton.setVisible(isStepOptional(currentStep));

        // Update build summary
        updateBuildSummary();
    }

    private void loadCurrentStep() {
        // Clear previous components
        componentListVBox.getChildren().clear();
        componentToggleGroup.getToggles().clear();

        switch (currentStep) {
            case STEP_CPU:
                loadCPUs();
                break;
            case STEP_MOTHERBOARD:
                loadCompatibleMotherboards();
                break;
            case STEP_COOLING:
                loadCompatibleCooling();
                break;
            case STEP_RAM:
                loadCompatibleRAM();
                break;
            case STEP_STORAGE:
                loadAvailableStorage();
                break;
            case STEP_GPU:
                loadAvailableGPUs();
                break;
            case STEP_CASE:
                loadAvailableCases();
                break;
            case STEP_PSU:
                loadCompatiblePSUs();
                break;
        }

        // Enable/disable next button based on selection
        updateNextButtonState();
    }

    // Component Loading Methods
    private void loadCPUs() {
        List<CPU> cpus = buildController.getAvailableCPUs();
        for (CPU cpu : cpus) {
            RadioButton radioButton = createComponentRadioButton(
                    cpu.getBrand() + " " + cpu.getModel() + " (" + cpu.getSocket() + ")",
                    cpu
            );
            componentListVBox.getChildren().add(radioButton);
        }
    }

    private void loadCompatibleMotherboards() {
        List<Motherboard> motherboards = buildController.getCompatibleMotherboards();
        if (motherboards.isEmpty()) {
            Label noCompatible = new Label("No compatible motherboards found!");
            noCompatible.setStyle("-fx-text-fill: red;");
            componentListVBox.getChildren().add(noCompatible);
            return;
        }

        for (Motherboard mb : motherboards) {
            RadioButton radioButton = createComponentRadioButton(
                    mb.getBrand() + " " + mb.getModel() + " (" + mb.getSocket() + ", " + mb.getFormFactor() + ")",
                    mb
            );
            componentListVBox.getChildren().add(radioButton);
        }
    }

    private void loadCompatibleCooling() {
        List<Cooling> coolers = buildController.getCompatibleCooling();
        for (Cooling cooler : coolers) {
            RadioButton radioButton = createComponentRadioButton(
                    cooler.getBrand() + " " + cooler.getModel() + " (" + cooler.getType() + ")",
                    cooler
            );
            componentListVBox.getChildren().add(radioButton);
        }
    }

    private void loadCompatibleRAM() {
        List<RAM> rams = buildController.getCompatibleRAM();
        if (rams.isEmpty()) {
            Label noCompatible = new Label("No compatible RAM found!");
            noCompatible.setStyle("-fx-text-fill: red;");
            componentListVBox.getChildren().add(noCompatible);
            return;
        }

        for (RAM ram : rams) {
            RadioButton radioButton = createComponentRadioButton(
                    ram.getBrand() + " " + ram.getModel() + " " + ram.getCapacity() + "GB " + ram.getType(),
                    ram
            );
            componentListVBox.getChildren().add(radioButton);
        }
    }

    private void loadAvailableStorage() {
        List<Storage> storages = buildController.getAvailableStorage();
        for (Storage storage : storages) {
            RadioButton radioButton = createComponentRadioButton(
                    storage.getBrand() + " " + storage.getModel() + " " + storage.getCapacity() + "GB " + storage.getType(),
                    storage
            );
            componentListVBox.getChildren().add(radioButton);
        }
    }

    private void loadAvailableGPUs() {
        List<GPU> gpus = buildController.getAvailableGPUs();
        for (GPU gpu : gpus) {
            RadioButton radioButton = createComponentRadioButton(
                    gpu.getBrand() + " " + gpu.getModel() + " " + gpu.getVram(),
                    gpu
            );
            componentListVBox.getChildren().add(radioButton);
        }
    }

    private void loadAvailableCases() {
        List<Case> cases = buildController.getAvailableCases();
        for (Case pcCase : cases) {
            RadioButton radioButton = createComponentRadioButton(
                    pcCase.getBrand() + " " + pcCase.getModel() + " (" + pcCase.getFormFactorSupport() + ")",
                    pcCase
            );
            componentListVBox.getChildren().add(radioButton);
        }
    }

    private void loadCompatiblePSUs() {
        List<PSU> psus = buildController.getCompatiblePSUs();
        if (psus.isEmpty()) {
            Label noCompatible = new Label("No compatible PSUs found!");
            noCompatible.setStyle("-fx-text-fill: red;");
            componentListVBox.getChildren().add(noCompatible);
            return;
        }

        for (PSU psu : psus) {
            RadioButton radioButton = createComponentRadioButton(
                    psu.getBrand() + " " + psu.getModel() + " " + psu.getWattage() + "W " + psu.getEfficiencyRating(),
                    psu
            );
            componentListVBox.getChildren().add(radioButton);
        }
    }

    // Helper method to create radio buttons
    private RadioButton createComponentRadioButton(String text, Object component) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setToggleGroup(componentToggleGroup);
        radioButton.setStyle("-fx-text-fill: #446777;");
        radioButton.setUserData(component);

        // Add listener to enable/disable next button
        radioButton.setOnAction(e -> updateNextButtonState());

        return radioButton;
    }

    private void updateNextButtonState() {
        Toggle selectedToggle = componentToggleGroup.getSelectedToggle();
        nextButton.setDisable(selectedToggle == null && !isStepOptional(currentStep));
    }

    private void updateBuildSummary() {
        Build currentBuild = buildController.getCurrentBuild();

        // Update each component label
        cpuLabel.setText(currentBuild.getCpu() != null ?
                "✅ CPU: " + currentBuild.getCpu().getBrand() + " " + currentBuild.getCpu().getModel() + " (" + currentBuild.getCpu().getSocket() + ")" :
                (currentStep == STEP_CPU ? "🔲 CPU: [Select CPU]" : "⏳ CPU: [Pending...]"));

        motherboardLabel.setText(currentBuild.getMotherboard() != null ?
                "✅ Motherboard: " + currentBuild.getMotherboard().getBrand() + " " + currentBuild.getMotherboard().getModel() :
                (currentStep == STEP_MOTHERBOARD ? "🔲 Motherboard: [Select Motherboard]" : "⏳ Motherboard: [Pending...]"));

        coolingLabel.setText(currentBuild.getCooling() != null ?
                "✅ Cooling: " + currentBuild.getCooling().getBrand() + " " + currentBuild.getCooling().getModel() :
                (currentStep == STEP_COOLING ? "🔲 Cooling: [Select Cooling]" : "⏳ Cooling: [Pending...]"));

        ramLabel.setText(currentBuild.getRam() != null ?
                "✅ RAM: " + currentBuild.getRam().getBrand() + " " + currentBuild.getRam().getModel() :
                (currentStep == STEP_RAM ? "🔲 RAM: [Select RAM]" : "⏳ RAM: [Pending...]"));

        storageLabel.setText(currentBuild.getStorage() != null ?
                "✅ Storage: " + currentBuild.getStorage().getBrand() + " " + currentBuild.getStorage().getModel() :
                (currentStep == STEP_STORAGE ? "🔲 Storage: [Select Storage]" : "⏳ Storage: [Pending...]"));

        gpuLabel.setText(currentBuild.getGpu() != null ?
                "✅ GPU: " + currentBuild.getGpu().getBrand() + " " + currentBuild.getGpu().getModel() :
                (currentStep == STEP_GPU ? "🔲 GPU: [Select GPU]" : "⏳ GPU: [Pending...]"));

        caseLabel.setText(currentBuild.getPcCase() != null ?
                "✅ Case: " + currentBuild.getPcCase().getBrand() + " " + currentBuild.getPcCase().getModel() :
                (currentStep == STEP_CASE ? "🔲 Case: [Select Case]" : "⏳ Case: [Pending...]"));

        psuLabel.setText(currentBuild.getPsu() != null ?
                "✅ PSU: " + currentBuild.getPsu().getBrand() + " " + currentBuild.getPsu().getModel() :
                (currentStep == STEP_PSU ? "🔲 PSU: [Select PSU]" : "⏳ PSU: [Pending...]"));
    }

    // Navigation Methods
    @FXML
    private void nextStep() {
        // Save current selection
        saveCurrentSelection();

        if (currentStep < TOTAL_STEPS) {
            currentStep++;
            updateStepDisplay();
            loadCurrentStep();
        } else {
            // Build complete - show summary
            showBuildComplete();
        }
    }

    @FXML
    private void goBack() {
        if (currentStep > 1) {
            currentStep--;
            updateStepDisplay();
            loadCurrentStep();
        }
    }

    @FXML
    private void skipComponent() {
        if (isStepOptional(currentStep)) {
            if (currentStep < TOTAL_STEPS) {
                currentStep++;
                updateStepDisplay();
                loadCurrentStep();
            }
        }
    }

    @FXML
    private void goHome(ActionEvent event) {
        loadView("/com/mahmoud/computerstore/view/main_view.fxml", "Computer Store", event);
    }

    private void saveCurrentSelection() {
        Toggle selectedToggle = componentToggleGroup.getSelectedToggle();
        if (selectedToggle != null) {
            Object component = selectedToggle.getUserData();

            switch (currentStep) {
                case STEP_CPU:
                    buildController.selectCPU((CPU) component);
                    break;
                case STEP_MOTHERBOARD:
                    buildController.selectMotherboard((Motherboard) component);
                    break;
                case STEP_COOLING:
                    buildController.selectCooling((Cooling) component);
                    break;
                case STEP_RAM:
                    buildController.selectRAM((RAM) component);
                    break;
                case STEP_STORAGE:
                    buildController.selectStorage((Storage) component);
                    break;
                case STEP_GPU:
                    buildController.selectGPU((GPU) component);
                    break;
                case STEP_CASE:
                    buildController.selectCase((Case) component);
                    break;
                case STEP_PSU:
                    buildController.selectPSU((PSU) component);
                    break;
            }
        }
    }

    private void showBuildComplete() {
        // TODO: Implement build completion screen
        // For now, just show an alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Build Complete!");
        alert.setHeaderText("Your PC build is complete!");
        alert.setContentText(buildController.getCurrentBuild().toString());
        alert.showAndWait();
    }

    // Helper Methods
    private String getComponentNameForStep(int step) {
        switch (step) {
            case STEP_CPU: return "CPU";
            case STEP_MOTHERBOARD: return "Motherboard";
            case STEP_COOLING: return "Cooling";
            case STEP_RAM: return "RAM";
            case STEP_STORAGE: return "Storage";
            case STEP_GPU: return "GPU";
            case STEP_CASE: return "Case";
            case STEP_PSU: return "PSU";
            default: return "Component";
        }
    }

    private String getStepInfoText(int step) {
        switch (step) {
            case STEP_CPU: return "Step 1: Choose your CPU to determine socket compatibility";
            case STEP_MOTHERBOARD: return "Step 2: Choose a motherboard compatible with your CPU socket";
            case STEP_COOLING: return "Step 3: Choose cooling compatible with your CPU socket";
            case STEP_RAM: return "Step 4: Choose RAM compatible with your motherboard";
            case STEP_STORAGE: return "Step 5: Choose storage devices for your build";
            case STEP_GPU: return "Step 6: Choose a graphics card for your build";
            case STEP_CASE: return "Step 7: Choose a case that fits your motherboard";
            case STEP_PSU: return "Step 8: Choose a PSU with sufficient wattage";
            default: return "Select your component";
        }
    }

    private boolean isStepOptional(int step) {
        // Only cooling is optional (users might already have CPU cooler)
        return step == STEP_COOLING;
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