package com.mahmoud.computerstore.model;

/**
 * Represents a full PC build with one selected component of each type.
 * Components can be null initially and set one by one.
 */
public class Build {

    private CPU cpu;
    private GPU gpu;
    private RAM ram;
    private Motherboard motherboard;
    private Storage storage;
    private PSU psu;
    private Case pcCase;
    private Cooling cooling;

    // Default constructor, allows creating empty build
    public Build() {
        this.cpu = null;
        this.gpu = null;
        this.ram = null;
        this.motherboard = null;
        this.storage = null;
        this.psu = null;
        this.pcCase = null;
        this.cooling = null;
    }

    // Constructor with all components (keep this for when you want to create complete builds)
    public Build(CPU cpu, GPU gpu, RAM ram, Motherboard motherboard,
                 Storage storage, PSU psu, Case pcCase, Cooling cooling) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.motherboard = motherboard;
        this.storage = storage;
        this.psu = psu;
        this.pcCase = pcCase;
        this.cooling = cooling;
    }

    // --- Getters ---
    public CPU getCpu() { return cpu; }
    public GPU getGpu() { return gpu; }
    public RAM getRam() { return ram; }
    public Motherboard getMotherboard() { return motherboard; }
    public Storage getStorage() { return storage; }
    public PSU getPsu() { return psu; }
    public Case getPcCase() { return pcCase; }
    public Cooling getCooling() { return cooling; }

    // --- Setters ---
    public void setCpu(CPU cpu) { this.cpu = cpu; }
    public void setGpu(GPU gpu) { this.gpu = gpu; }
    public void setRam(RAM ram) { this.ram = ram; }
    public void setMotherboard(Motherboard motherboard) { this.motherboard = motherboard; }
    public void setStorage(Storage storage) { this.storage = storage; }
    public void setPsu(PSU psu) { this.psu = psu; }
    public void setPcCase(Case pcCase) { this.pcCase = pcCase; }
    public void setCooling(Cooling cooling) { this.cooling = cooling; }

    // Check if build is complete
    public boolean isComplete() {
        return cpu != null && gpu != null && ram != null && motherboard != null &&
                storage != null && psu != null && pcCase != null && cooling != null;
    }

    // Get completion percentage
    public double getCompletionPercentage() {
        int completed = 0;
        if (cpu != null) completed++;
        if (gpu != null) completed++;
        if (ram != null) completed++;
        if (motherboard != null) completed++;
        if (storage != null) completed++;
        if (psu != null) completed++;
        if (pcCase != null) completed++;
        if (cooling != null) completed++;
        return (completed / 8.0) * 100;
    }

//  The code below is no longer needed, this was used for debugging and initial testing

//    @Override
//    public String toString() {
//        return "PC Build Summary:\n" +
//                "CPU: " + (cpu != null ? cpu.getBrand() + " " + cpu.getModel() : "Not selected") + "\n" +
//                "Motherboard: " + (motherboard != null ? motherboard.getBrand() + " " + motherboard.getModel() : "Not selected") + "\n" +
//                "Cooling: " + (cooling != null ? cooling.getBrand() + " " + cooling.getModel() : "Not selected") + "\n" +
//                "RAM: " + (ram != null ? ram.getBrand() + " " + ram.getModel() : "Not selected") + "\n" +
//                "Storage: " + (storage != null ? storage.getBrand() + " " + storage.getModel() : "Not selected") + "\n" +
//                "GPU: " + (gpu != null ? gpu.getBrand() + " " + gpu.getModel() : "Not selected") + "\n" +
//                "Case: " + (pcCase != null ? pcCase.getBrand() + " " + pcCase.getModel() : "Not selected") + "\n" +
//                "PSU: " + (psu != null ? psu.getBrand() + " " + psu.getModel() : "Not selected") + "\n" +
//                "Completion: " + String.format("%.0f%%", getCompletionPercentage());
//    }
}