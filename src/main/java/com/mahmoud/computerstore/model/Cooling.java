package com.mahmoud.computerstore.model;

/**
 * Represents a CPU cooling solution (Air or Liquid).
 * Includes compatibility, noise level, and physical specs.
 */
public class Cooling {
    private int id;                      // Unique identifier
    private String brand;                // Manufacturer
    private String model;                // Model name or number
    private String type;                 // Type (e.g., Air, AIO Liquid)
    private String socketCompatibility;  // Compatible CPU sockets (e.g., "LGA 1700, AM4")
    private int fanSize;                 // Fan size in mm (e.g., 120)
    private String radiatorSize;         // Radiator size for liquid coolers (e.g., 240mm)
    private String noiseLevel;           // Noise level in decibels (e.g., 25 dB)
    private int rpm;                     // Fan speed in RPM

    //Constructs a Cooling object with specified attributes.
    public Cooling(int id,
                   String brand,
                   String model,
                   String type,
                   String socketCompatibility,
                   int fanSize,
                   String radiatorSize,
                   String noiseLevel,
                   int rpm) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.socketCompatibility = socketCompatibility;
        this.fanSize = fanSize;
        this.radiatorSize = radiatorSize;
        this.noiseLevel = noiseLevel;
        this.rpm = rpm;
    }

    // --- Getters ---
    public int getId()                   { return id; }
    public String getBrand()             { return brand; }
    public String getModel()             { return model; }
    public String getType()              { return type; }
    public String getSocketCompatibility() { return socketCompatibility; }
    public int getFanSize()              { return fanSize; }
    public String getRadiatorSize()      { return radiatorSize; }
    public String getNoiseLevel()        { return noiseLevel; }
    public int getRpm()                  { return rpm; }

    //  The code below is no longer needed, this was used for debugging and initial testing

    @Override
    public String toString() {
        return "Cooling ID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nType: " + type +
                "\nSocket Compatibility: " + socketCompatibility +
                "\nFan Size: " + fanSize + "mm" +
                "\nRadiator Size: " + radiatorSize +
                "\nNoise Level: " + noiseLevel +
                "\nRPM: " + rpm;
    }
}
