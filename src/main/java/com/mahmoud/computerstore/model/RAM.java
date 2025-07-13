package com.mahmoud.computerstore.model;

/**
 * Represents a RAM (Random Access Memory) component.
 * Includes type, speed, capacity, and configuration.
 */
public class RAM {
    private int id;            // Unique identifier
    private String brand;      // Manufacturer
    private String model;      // Model name or number
    private String type;       // Memory type (e.g., DDR4, DDR5)
    private String capacity;   // Capacity per module (e.g., 8GB)
    private String speed;      // Memory speed (e.g., 3200MHz)
    private int modules;       // Number of modules in the kit
    private String voltage;    // Operating voltage (e.g., 1.35V)

    //Constructs a RAM object with specified attributes
    public RAM(int id,
               String brand,
               String model,
               String type,
               String capacity,
               String speed,
               int modules,
               String voltage) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.capacity = capacity;
        this.speed = speed;
        this.modules = modules;
        this.voltage = voltage;
    }

    // --- Getters ---
    public int getId()            { return id; }
    public String getBrand()      { return brand; }
    public String getModel()      { return model; }
    public String getType()       { return type; }
    public String getCapacity()   { return capacity; }
    public String getSpeed()      { return speed; }
    public int getModules()       { return modules; }
    public String getVoltage()    { return voltage; }

    @Override
    public String toString() {
        return "RAM ID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nType: " + type +
                "\nCapacity: " + capacity +
                "\nSpeed: " + speed +
                "\nModules: " + modules +
                "\nVoltage: " + voltage;
    }
}
