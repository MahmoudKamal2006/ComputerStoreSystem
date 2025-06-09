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

        if (cpus.isEmpty()) {
            System.out.println("❗ No CPUs found in inventory.");
            return;
        }

        // Select first CPU
        CPU selectedCpu = cpus.get(0);
        builder.selectCPU(selectedCpu);
        System.out.println("\nSelected CPU: " + selectedCpu.getModel());

        // 2. Compatible motherboards
        System.out.println("\nCompatible Motherboards:");
        List<Motherboard> motherboards = builder.getCompatibleMotherboards();
        if (motherboards.isEmpty()) {
            System.out.println("❗ No compatible motherboards found.");
            return;
        }
        for (Motherboard mb : motherboards) {
            System.out.println("- " + mb.getModel() + " (" + mb.getSocket() + ")");
        }
        builder.selectMotherboard(motherboards.get(0));

        // 3. Compatible cooling
        System.out.println("\nCompatible Cooling:");
        List<Cooling> coolers = builder.getCompatibleCooling();
        if (coolers.isEmpty()) {
            System.out.println("❗ No compatible cooling found.");
        } else {
            for (Cooling cooler : coolers) {
                System.out.println("- " + cooler.getModel());
            }
            builder.selectCooling(coolers.get(0));
        }

        // 4. Compatible RAM
        System.out.println("\nCompatible RAM:");
        List<RAM> rams = builder.getCompatibleRAM();
        if (rams.isEmpty()) {
            System.out.println("❗ No compatible RAM found.");
            return;
        }
        for (RAM ram : rams) {
            System.out.println("- " + ram.getModel() + " (" + ram.getType() + ")");
        }
        builder.selectRAM(rams.get(0));

        // 5. Storage
        List<Storage> storageList = builder.getAvailableStorage();
        if (!storageList.isEmpty()) {
            builder.selectStorage(storageList.get(0));
        }

        // 6. GPU
        List<GPU> gpus = builder.getAvailableGPUs();
        if (!gpus.isEmpty()) {
            builder.selectGPU(gpus.get(0));
        }

        // 7. Case
        List<Case> cases = builder.getAvailableCases();
        if (!cases.isEmpty()) {
            builder.selectCase(cases.get(0));
        }

        // 8. Compatible PSU
        List<PSU> psus = builder.getCompatiblePSUs();
        if (psus.isEmpty()) {
            System.out.println("❗ No compatible PSUs found.");
        } else {
            builder.selectPSU(psus.get(0));
        }

        // Final build summary
        System.out.println("\n✅ Final PC Build Summary:");
        System.out.println(builder.getCurrentBuild());
    }
}
