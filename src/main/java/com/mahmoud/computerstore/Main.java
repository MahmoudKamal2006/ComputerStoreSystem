package com.mahmoud.computerstore;

import com.mahmoud.computerstore.controller.BuildController;
import com.mahmoud.computerstore.model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BuildController builder = new BuildController();

        // 1. Show all CPUs
        System.out.println("Available CPUs:");
        List<CPU> cpus = builder.getAvailableCPUs();
        for (int i = 0; i < cpus.size(); i++) {
            System.out.println(i + ": " + cpus.get(i).getModel() + " (" + cpus.get(i).getSocket() + ")");
        }

        // Select the first CPU
        CPU selectedCpu = cpus.get(0);
        builder.selectCPU(selectedCpu);
        System.out.println("\nSelected CPU: " + selectedCpu.getModel());

        // 2. Show compatible motherboards
        System.out.println("\nCompatible Motherboards:");
        List<Motherboard> motherboards = builder.getCompatibleMotherboards();
        for (Motherboard mb : motherboards) {
            System.out.println("- " + mb.getModel() + " (" + mb.getSocket() + ")");
        }

        if (!motherboards.isEmpty()) {
            builder.selectMotherboard(motherboards.get(0));
        }

        // 3. Show compatible RAM
        System.out.println("\nCompatible RAM:");
        List<RAM> rams = builder.getCompatibleRAM();
        for (RAM ram : rams) {
            System.out.println("- " + ram.getModel() + " (" + ram.getType() + ")");
        }

        if (!rams.isEmpty()) {
            builder.selectRAM(rams.get(0));
        }

        // 4. Add GPU, Storage, Cooler, Case, PSU (pick the first available)
        if (!builder.getAvailableGPUs().isEmpty()) {
            builder.selectGPU(builder.getAvailableGPUs().get(0));
        }

        if (!builder.getAvailableStorage().isEmpty()) {
            builder.selectStorage(builder.getAvailableStorage().get(0));
        }

        if (!builder.getCompatibleCooling().isEmpty()) {
            builder.selectCooling(builder.getCompatibleCooling().get(0));
        }

        if (!builder.getAvailableCases().isEmpty()) {
            builder.selectCase(builder.getAvailableCases().get(0));
        }

        if (!builder.getCompatiblePSUs().isEmpty()) {
            builder.selectPSU(builder.getCompatiblePSUs().get(0));
        }

        // 5. Print final build summary
        System.out.println("\n✅ Final PC Build:");
        System.out.println(builder.getCurrentBuild());
    }
}
