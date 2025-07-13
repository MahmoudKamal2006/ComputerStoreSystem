package com.mahmoud.computerstore.model;

/**
 * Represents a PC Case (Chassis).
 * Includes physical dimensions and supported hardware specifications.
 */
public class Case {
    private int id;                     // Unique identifier
    private String brand;               // Manufacturer
    private String model;               // Model name or number
    private String formFactorSupport;   // Supported motherboard sizes (e.g., ATX, Micro-ATX)
    private int maxGpuLength;           // Max supported GPU length (in mm)
    private String driveBays;           // Drive bay layout (e.g., "2x 3.5\", 2x 2.5\"")
    private String fanSupport;          // Fan support info (e.g., "3x 120mm, 1x 140mm rear")
    private String radiatorSupport;     // Liquid cooling radiator support (e.g., "240mm top")

    //Constructs a Case object with specified attributes
    public Case(int id,
                String brand,
                String model,
                String formFactorSupport,
                int maxGpuLength,
                String driveBays,
                String fanSupport,
                String radiatorSupport) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.formFactorSupport = formFactorSupport;
        this.maxGpuLength = maxGpuLength;
        this.driveBays = driveBays;
        this.fanSupport = fanSupport;
        this.radiatorSupport = radiatorSupport;
    }

    // --- Getters ---
    public int getId()                   { return id; }
    public String getBrand()             { return brand; }
    public String getModel()             { return model; }
    public String getFormFactorSupport() { return formFactorSupport; }
    public int getMaxGpuLength()         { return maxGpuLength; }
    public String getDriveBays()         { return driveBays; }
    public String getFanSupport()        { return fanSupport; }
    public String getRadiatorSupport()   { return radiatorSupport; }

    //  The code below is no longer needed, this was used for debugging and initial testing

    @Override
    public String toString() {
        return "Case ID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nForm Factor Support: " + formFactorSupport +
                "\nMax GPU Length: " + maxGpuLength + "mm" +
                "\nDrive Bays: " + driveBays +
                "\nFan Support: " + fanSupport +
                "\nRadiator Support: " + radiatorSupport;
    }
}
