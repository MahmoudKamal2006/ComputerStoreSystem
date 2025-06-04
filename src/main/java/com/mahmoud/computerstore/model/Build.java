package com.mahmoud.computerstore.model;

/**
 * Represents a full PC build with one selected component of each type.
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

    @Override
    public String toString() {
        return "PC Build Summary:\n" +
                "CPU: " + (cpu != null ? cpu.getModel() : "None") + "\n" +
                "Motherboard: " + (motherboard != null ? motherboard.getModel() : "None") + "\n" +
                "Cooling: " + (cooling != null ? cooling.getModel() : "None") + "\n" +
                "RAM: " + (ram != null ? ram.getModel() : "None") + "\n" +
                "Storage: " + (storage != null ? storage.getModel() : "None") + "\n" +
                "GPU: " + (gpu != null ? gpu.getModel() : "None") + "\n" +
                "Case: " + (pcCase != null ? pcCase.getModel() : "None") + "\n" +
                "PSU: " + (psu != null ? psu.getModel() : "None");
    }
}
