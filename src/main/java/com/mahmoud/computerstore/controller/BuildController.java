package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.*;
import com.mahmoud.computerstore.service.DataService;
import com.mahmoud.computerstore.util.CompatibilityChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuildController {

    private Build currentBuild;

    private List<CPU> cpus;
    private List<Motherboard> motherboards;
    private List<Cooling> coolers;
    private List<RAM> rams;
    private List<Storage> storageDevices;
    private List<GPU> gpus;
    private List<Case> cases;
    private List<PSU> psus;

    public BuildController() {
        DataService data = new DataService();
        cpus = data.loadCPUs();
        motherboards = data.loadMotherboards();
        coolers = data.loadCooling();
        rams = data.loadRAM();
        storageDevices = data.loadStorage();
        gpus = data.loadGPUs();
        cases = data.loadCases();
        psus = data.loadPSUs();
        currentBuild = new Build();
    }

    // Get all CPUs
    public List<CPU> getAvailableCPUs() {
        return cpus;
    }

    // Return all motherboards (used only for testing/debug)
    public List<Motherboard> getAllMotherboards() {
        return motherboards;
    }

    // Return only compatible motherboards
    public List<Motherboard> getCompatibleMotherboards() {
        CPU cpu = currentBuild.getCpu();
        if (cpu == null) return new ArrayList<>();
        return motherboards.stream()
                .filter(mb -> CompatibilityChecker.isCompatible(cpu, mb))
                .collect(Collectors.toList());
    }

    public List<Cooling> getCompatibleCooling() {
        CPU cpu = currentBuild.getCpu();
        if (cpu == null) return new ArrayList<>();
        return coolers.stream()
                .filter(cooler -> CompatibilityChecker.isCompatible(cpu, cooler))
                .collect(Collectors.toList());
    }

    public List<RAM> getCompatibleRAM() {
        CPU cpu = currentBuild.getCpu();
        Motherboard mb = currentBuild.getMotherboard();
        if (cpu == null || mb == null) return new ArrayList<>();
        return rams.stream()
                .filter(ram -> CompatibilityChecker.isCompatible(cpu, ram, mb))
                .collect(Collectors.toList());
    }

    public List<Storage> getAvailableStorage() {
        return storageDevices;
    }

    public List<GPU> getAvailableGPUs() {
        return gpus;
    }

    public List<Case> getAvailableCases() {
        return cases;
    }

    public List<PSU> getCompatiblePSUs() {
        return psus.stream()
                .filter(psu -> CompatibilityChecker.isCompatible(currentBuild, psu))
                .collect(Collectors.toList());
    }

    // Selection setters
    public void selectCPU(CPU cpu) {
        currentBuild.setCpu(cpu);
    }

    public void selectMotherboard(Motherboard motherboard) {
        currentBuild.setMotherboard(motherboard);
    }

    public void selectCooling(Cooling cooling) {
        currentBuild.setCooling(cooling);
    }

    public void selectRAM(RAM ram) {
        currentBuild.setRam(ram);
    }

    public void selectStorage(Storage storage) {
        currentBuild.setStorage(storage);
    }

    public void selectGPU(GPU gpu) {
        currentBuild.setGpu(gpu);
    }

    public void selectCase(Case pcCase) {
        currentBuild.setPcCase(pcCase);
    }

    public void selectPSU(PSU psu) {
        currentBuild.setPsu(psu);
    }

    public Build getCurrentBuild() {
        return currentBuild;
    }
}
