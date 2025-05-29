package com.mahmoud.computerstore.model;

/**
 * Represents a storage device (SSD or HDD).
 * Contains capacity, type, speed, and interface details.
 */
public class Storage {
    private int id;                // Unique identifier
    private String brand;          // Manufacturer
    private String model;          // Model name or number
    private String type;           // Type of storage (e.g., SSD, HDD)
    private String capacity;       // Capacity (e.g., 1TB)
    private String interfaceType;  // Interface (e.g., SATA, NVMe)
    private String formFactor;     // Form factor (e.g., 2.5", M.2)
    private String readSpeed;      // Read speed (e.g., 3500MB/s)
    private String writeSpeed;     // Write speed (e.g., 3000MB/s)

    /**
     * Constructs a Storage object with specified attributes.
     */
    public Storage(int id,
                   String brand,
                   String model,
                   String type,
                   String capacity,
                   String interfaceType,
                   String formFactor,
                   String readSpeed,
                   String writeSpeed) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.capacity = capacity;
        this.interfaceType = interfaceType;
        this.formFactor = formFactor;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
    }

    // --- Getters ---
    public int getId()               { return id; }
    public String getBrand()         { return brand; }
    public String getModel()         { return model; }
    public String getType()          { return type; }
    public String getCapacity()      { return capacity; }
    public String getInterfaceType() { return interfaceType; }
    public String getFormFactor()    { return formFactor; }
    public String getReadSpeed()     { return readSpeed; }
    public String getWriteSpeed()    { return writeSpeed; }

    @Override
    public String toString() {
        return "Storage ID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nType: " + type +
                "\nCapacity: " + capacity +
                "\nInterface: " + interfaceType +
                "\nForm Factor: " + formFactor +
                "\nRead Speed: " + readSpeed +
                "\nWrite Speed: " + writeSpeed;
    }
}
