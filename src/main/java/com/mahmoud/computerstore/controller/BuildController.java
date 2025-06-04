package com.mahmoud.computerstore.controller;

import com.mahmoud.computerstore.model.*;
import com.mahmoud.computerstore.service.DataService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles logic for building a custom PC step by step with compatibility.
 */
public class BuildController {

    private final DataService dataService;
    private final Build currentBuild;

    private final List<CPU> allCpus;
    private final List<Motherboard> allMotherboards;
    private final List<RAM> allRam;
    private final List<GPU> allGpus;
    private final List<Storage> allStorage;
    private final List<PSU> allPsus;
    private final List<Case> allCases;
    private final List<Cooling> allCooling;

    public BuildController() {
        this.dataService = new DataService();
        this.currentBuild = new Build(null, null, null, null, null, null, null, null);

        this.allCpus = dataService.loadCPUs();
        this.allMotherboards = dataService.loadMotherboards();
        this.allRam = dataService.loadRAM();
        this.allGpus = dataService.loadGPUs();
        this.allStorage = dataService.loadStorage();
        this.allPsus = dataService.loadPSUs();
        this.allCases = dataService.loadCases();
        this.allCooling = dataService.loadCooling();
    }

    // ---------------- CPU ----------------
    public List<CPU> getAvailableCPUs() {
        return allCpus;
    }

    public void selectCPU(CPU cpu) {
        currentBuild.setCpu(cpu);
    }

    // ---------------- Motherboard ----------------
    public List<Motherboard> getCompatibleMotherboards() {
        CPU cpu = currentBuild.getCpu();
        if (cpu == null) return List.of();
        return allMotherboards.stream()
                .filter(mb -> mb.getSocket().equalsIgnoreCase(cpu.getSocket()))
                .collect(Collectors.toList());
    }

    public void selectMotherboard(Motherboard mb) {
        currentBuild.setMotherboard(mb);
    }

    // ---------------- Cooler ----------------
    public List<Cooling> getCompatibleCooling() {
        CPU cpu = currentBuild.getCpu();
        if (cpu == null) return List.of();
        return allCooling.stream()
                .filter(cooler -> cooler.getSocketCompatibility().toLowerCase().contains(cpu.getSocket().toLowerCase()))
                .collect(Collectors.toList());
    }

    public void selectCooling(Cooling cooling) {
        currentBuild.setCooling(cooling);
    }

    // ---------------- RAM ----------------
    public List<RAM> getCompatibleRAM() {
        Motherboard mb = currentBuild.getMotherboard();
        if (mb == null) return List.of();
        return allRam.stream()
                .filter(ram -> ram.getType().equalsIgnoreCase(mb.getMemoryType()))
                .collect(Collectors.toList());
    }

    public void selectRAM(RAM ram) {
        currentBuild.setRam(ram);
    }

    // ---------------- Storage ----------------
    public List<Storage> getAvailableStorage() {
        return allStorage;
    }

    public void selectStorage(Storage storage) {
        currentBuild.setStorage(storage);
    }

    // ---------------- GPU ----------------
    public List<GPU> getAvailableGPUs() {
        return allGpus;
    }

    public void selectGPU(GPU gpu) {
        currentBuild.setGpu(gpu);
    }

    // ---------------- Case ----------------
    public List<Case> getAvailableCases() {
        return allCases;
    }

    public void selectCase(Case pcCase) {
        currentBuild.setPcCase(pcCase);
    }

    // ---------------- PSU ----------------
    public List<PSU> getCompatiblePSUs() {
        CPU cpu = currentBuild.getCpu();
        GPU gpu = currentBuild.getGpu();
        if (cpu == null || gpu == null) return allPsus;

        int requiredPower = cpu.getTdp() + gpu.getTdp() + 150; // +150 for other components
        return allPsus.stream()
                .filter(psu -> psu.getWattage() >= requiredPower)
                .collect(Collectors.toList());
    }

    public void selectPSU(PSU psu) {
        currentBuild.setPsu(psu);
    }

    // ---------------- Final Build ----------------
    public Build getCurrentBuild() {
        return currentBuild;
    }
}
