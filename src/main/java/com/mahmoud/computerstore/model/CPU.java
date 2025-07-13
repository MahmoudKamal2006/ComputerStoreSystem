package com.mahmoud.computerstore.model;

/**
 * Represents a CPU (Central Processing Unit) component.
 * Holds both essential and extended specs for compatibility checking.
 */
public class CPU {
    private int id;                       // Unique identifier
    private String brand;                 // Manufacturer (e.g., Intel, AMD)
    private String model;                 // Model name or number
    private String socket;                // Socket type (e.g., LGA 1700, AM5)
    private int tdp;                      // Thermal Design Power in Watts
    private String chipsetCompatibility;  // Compatible chipsets (e.g., Z790, B650)
    private String pcieVersion;           // Supported PCIe version (e.g., PCIe 5.0)
    private String memorySupport;         // Supported memory types/speeds (e.g., DDR5-5600)
    private int powerConsumption;         // Typical power consumption in Watts

    //Constructs a CPU object with specified attributes.
    public CPU(int id,
               String brand,
               String model,
               String socket,
               int tdp,
               String chipsetCompatibility,
               String pcieVersion,
               String memorySupport,
               int powerConsumption) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.socket = socket;
        this.tdp = tdp;
        this.chipsetCompatibility = chipsetCompatibility;
        this.pcieVersion = pcieVersion;
        this.memorySupport = memorySupport;
        this.powerConsumption = powerConsumption;
    }

    // --- Getters ---
    public int getId()                     { return id; }
    public String getBrand()               { return brand; }
    public String getModel()               { return model; }
    public String getSocket()              { return socket; }
    public int getTdp()                    { return tdp; }
    public String getChipsetCompatibility(){ return chipsetCompatibility; }
    public String getPcieVersion()         { return pcieVersion; }
    public String getMemorySupport()       { return memorySupport; }
    public int getPowerConsumption()       { return powerConsumption; }

    @Override
    public String toString() {
        return "CPU ID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nSocket: " + socket +
                "\nTDP: " + tdp + "W" +
                "\nChipset Compatibility: " + chipsetCompatibility +
                "\nPCIe Version: " + pcieVersion +
                "\nMemory Support: " + memorySupport +
                "\nPower Consumption: " + powerConsumption + "W";
    }
}
