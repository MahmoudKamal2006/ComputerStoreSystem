package com.mahmoud.computerstore.model;

/**
 * Represents a motherboard component.
 * Includes key specifications for compatibility with other PC parts.
 */
public class Motherboard {
    private int id;                     // Unique identifier
    private String brand;               // Manufacturer
    private String model;               // Model name or number
    private String socket;              // CPU socket type (e.g., LGA 1700)
    private String chipset;             // Chipset model (e.g., Z790)
    private String formFactor;          // Form factor (e.g., ATX, Micro-ATX)
    private int memorySlots;            // Number of RAM slots
    private String maxMemory;           // Maximum supported RAM (e.g., 128GB)
    private String memoryType;          // Supported memory type (e.g., DDR5)
    private String pcieSlots;           // PCIe slot types (e.g., "1x PCIe 5.0 x16, 2x PCIe 4.0 x1")
    private String storageInterfaces;   // Supported storage types (e.g., SATA, M.2)
    private String usbPorts;            // USB ports and types (e.g., "2x USB 3.2, 4x USB 2.0")
    private String network;             // Networking options (e.g., Ethernet, Wi-Fi 6)

    //Constructs a Motherboard object with specified attributes.
    public Motherboard(int id,
                       String brand,
                       String model,
                       String socket,
                       String chipset,
                       String formFactor,
                       int memorySlots,
                       String maxMemory,
                       String memoryType,
                       String pcieSlots,
                       String storageInterfaces,
                       String usbPorts,
                       String network) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.socket = socket;
        this.chipset = chipset;
        this.formFactor = formFactor;
        this.memorySlots = memorySlots;
        this.maxMemory = maxMemory;
        this.memoryType = memoryType;
        this.pcieSlots = pcieSlots;
        this.storageInterfaces = storageInterfaces;
        this.usbPorts = usbPorts;
        this.network = network;
    }

    // --- Getters ---
    public int getId()                   { return id; }
    public String getBrand()             { return brand; }
    public String getModel()             { return model; }
    public String getSocket()            { return socket; }
    public String getChipset()           { return chipset; }
    public String getFormFactor()        { return formFactor; }
    public int getMemorySlots()          { return memorySlots; }
    public String getMaxMemory()         { return maxMemory; }
    public String getMemoryType()        { return memoryType; }
    public String getPcieSlots()         { return pcieSlots; }
    public String getStorageInterfaces() { return storageInterfaces; }
    public String getUsbPorts()          { return usbPorts; }
    public String getNetwork()           { return network; }

    //  The code below is no longer needed, this was used for debugging and initial testing

    @Override
    public String toString() {
        return "Motherboard ID: " + id +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nSocket: " + socket +
                "\nChipset: " + chipset +
                "\nForm Factor: " + formFactor +
                "\nMemory Slots: " + memorySlots +
                "\nMax Memory: " + maxMemory +
                "\nMemory Type: " + memoryType +
                "\nPCIe Slots: " + pcieSlots +
                "\nStorage Interfaces: " + storageInterfaces +
                "\nUSB Ports: " + usbPorts +
                "\nNetwork: " + network;
    }
}
