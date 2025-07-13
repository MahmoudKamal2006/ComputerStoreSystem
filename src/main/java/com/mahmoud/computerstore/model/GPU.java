package com.mahmoud.computerstore.model;

/**
 * Represents a GPU (Graphics Processing Unit) component.
 * Holds essential specs for compatibility and performance checks.
 */
public class GPU {
    private int id;                 // Unique identifier
    private String brand;           // Manufacturer (e.g., NVIDIA, AMD)
    private String model;           // Model name or number
    private String interfaceType;   // Interface (e.g., PCIe 4.0 x16)
    private int tdp;                // Thermal Design Power in Watts
    private String vram;            // Video memory size (e.g., 8GB)
    private String powerConnectors; // Required power connectors (e.g., 6-pin, 8-pin)
    private int length;             // Physical length in millimeters

    //Constructs a GPU object with specified attributes.
    public GPU(int id,
               String brand,
               String model,
               String interfaceType,
               int tdp,
               String vram,
               String powerConnectors,
               int length) {
        this.id               = id;
        this.brand            = brand;
        this.model            = model;
        this.interfaceType    = interfaceType;
        this.tdp              = tdp;
        this.vram             = vram;
        this.powerConnectors  = powerConnectors;
        this.length           = length;
    }

    // --- Getters (no setters for immutability) ---
    public int getId()                   { return id; }
    public String getBrand()             { return brand; }
    public String getModel()             { return model; }
    public String getInterfaceType()     { return interfaceType; }
    public int getTdp()                  { return tdp; }
    public String getVram()              { return vram; }
    public String getPowerConnectors()   { return powerConnectors; }
    public int getLength()               { return length; }

    @Override
    public String toString() {
        return "GPU ID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nInterface: " + interfaceType +
                "\nTDP: " + tdp + "W" +
                "\nVRAM: " + vram +
                "\nPower Connectors: " + powerConnectors +
                "\nLength: " + length + "mm";
    }
}
