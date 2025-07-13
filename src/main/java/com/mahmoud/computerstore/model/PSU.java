package com.mahmoud.computerstore.model;

/**
 * Represents a PSU (Power Supply Unit).
 * Contains power output, efficiency, form factor, and connector info.
 */
public class PSU {
    private int id;                  // Unique identifier
    private String brand;            // Manufacturer
    private String model;            // Model name or number
    private int wattage;             // Total power output in Watts
    private String efficiencyRating; // Efficiency certification (e.g., 80+ Gold)
    private String modular;          // Modularity type (e.g., Full, Semi, Non-Modular)
    private String formFactor;       // Form factor (e.g., ATX)
    private String connectors;       // Available power connectors (e.g., "24-pin, 8-pin CPU, 2x 6+2-pin PCIe")

    // Constructs a PSU object with specified attributes
    public PSU(int id,
               String brand,
               String model,
               int wattage,
               String efficiencyRating,
               String modular,
               String formFactor,
               String connectors) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.wattage = wattage;
        this.efficiencyRating = efficiencyRating;
        this.modular = modular;
        this.formFactor = formFactor;
        this.connectors = connectors;
    }

    // --- Getters ---
    public int getId()                  { return id; }
    public String getBrand()            { return brand; }
    public String getModel()            { return model; }
    public int getWattage()             { return wattage; }
    public String getEfficiencyRating() { return efficiencyRating; }
    public String getModular()          { return modular; }
    public String getFormFactor()       { return formFactor; }
    public String getConnectors()       { return connectors; }

    //  The code below is no longer needed, this was used for debugging and initial testing

    @Override
    public String toString() {
        return "PSU ID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nWattage: " + wattage + "W" +
                "\nEfficiency Rating: " + efficiencyRating +
                "\nModularity: " + modular +
                "\nForm Factor: " + formFactor +
                "\nConnectors: " + connectors;
    }
}
