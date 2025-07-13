package com.mahmoud.computerstore;

import com.mahmoud.computerstore.controller.BuildController;
import com.mahmoud.computerstore.model.*;

import java.util.List;
import java.util.Scanner;

/**
 * IMPORTANT NOTE
 * This class is no longer of use since it was used to test the project via the console (terminal)
 */

public class ConsoleTest {
    public static void main(String[] args) {
        BuildController builder = new BuildController();
        Scanner scanner = new Scanner(System.in);

        // 1. Show all CPUs
        System.out.println("Available CPUs:");
        List<CPU> cpus = builder.getAvailableCPUs();
        for (int i = 0; i < cpus.size(); i++) {
            System.out.println(i + ": " + cpus.get(i).getModel() + " (" + cpus.get(i).getSocket() + ")");
        }

        if (cpus.isEmpty()) {
            System.out.println("No CPUs found in inventory.");
            return;
        }

        System.out.print("Choose a CPU index: ");
        int cpuIndex = scanner.nextInt();
        if (cpuIndex < 0 || cpuIndex >= cpus.size()) {
            System.out.println("Invalid index.");
            return;
        }
        CPU selectedCpu = cpus.get(cpuIndex);
        builder.selectCPU(selectedCpu);
        System.out.println("\nSelected CPU: " + selectedCpu.getModel());

        // 2. Compatible motherboards
        System.out.println("\nCompatible Motherboards:");
        List<Motherboard> motherboards = builder.getCompatibleMotherboards();
        if (motherboards.isEmpty()) {
            System.out.println("No compatible motherboards found.");
            return;
        }
        for (int i = 0; i < motherboards.size(); i++) {
            System.out.println(i + ": " + motherboards.get(i).getModel() + " (" + motherboards.get(i).getSocket() + ")");
        }

        System.out.print("Choose a Motherboard index: ");
        int mbIndex = scanner.nextInt();
        if (mbIndex < 0 || mbIndex >= motherboards.size()) {
            System.out.println("Invalid index.");
            return;
        }
        builder.selectMotherboard(motherboards.get(mbIndex));

        // 3. Compatible cooling
        System.out.println("\nCompatible Cooling:");
        List<Cooling> coolers = builder.getCompatibleCooling();
        if (coolers.isEmpty()) {
            System.out.println("No compatible cooling found.");
        } else {
            for (int i = 0; i < coolers.size(); i++) {
                System.out.println(i + ": " + coolers.get(i).getModel());
            }
            System.out.print("Choose a Cooler index: ");
            int coolerIndex = scanner.nextInt();
            if (coolerIndex < 0 || coolerIndex >= coolers.size()) {
                System.out.println("Invalid index.");
                return;
            }
            builder.selectCooling(coolers.get(coolerIndex));
        }

        // 4. Compatible RAM
        System.out.println("\nCompatible RAM:");
        List<RAM> rams = builder.getCompatibleRAM();
        if (rams.isEmpty()) {
            System.out.println("No compatible RAM found.");
            return;
        }
        for (int i = 0; i < rams.size(); i++) {
            System.out.println(i + ": " + rams.get(i).getModel() + " (" + rams.get(i).getType() + ")");
        }

        System.out.print("Choose a RAM index: ");
        int ramIndex = scanner.nextInt();
        if (ramIndex < 0 || ramIndex >= rams.size()) {
            System.out.println("Invalid index.");
            return;
        }
        builder.selectRAM(rams.get(ramIndex));

        // 5. Storage
        System.out.println("\nAvailable Storage:");
        List<Storage> storageList = builder.getAvailableStorage();
        if (!storageList.isEmpty()) {
            for (int i = 0; i < storageList.size(); i++) {
                System.out.println(i + ": " + storageList.get(i).getModel());
            }
            System.out.print("Choose a Storage index: ");
            int storageIndex = scanner.nextInt();
            if (storageIndex < 0 || storageIndex >= storageList.size()) {
                System.out.println("Invalid index.");
                return;
            }
            builder.selectStorage(storageList.get(storageIndex));
        }

        // 6. GPU
        System.out.println("\nAvailable GPUs:");
        List<GPU> gpus = builder.getAvailableGPUs();
        if (!gpus.isEmpty()) {
            for (int i = 0; i < gpus.size(); i++) {
                System.out.println(i + ": " + gpus.get(i).getModel());
            }
            System.out.print("Choose a GPU index: ");
            int gpuIndex = scanner.nextInt();
            if (gpuIndex < 0 || gpuIndex >= gpus.size()) {
                System.out.println("Invalid index.");
                return;
            }
            builder.selectGPU(gpus.get(gpuIndex));
        }

        // 7. Case
        System.out.println("\nAvailable Cases:");
        List<Case> cases = builder.getAvailableCases();
        if (!cases.isEmpty()) {
            for (int i = 0; i < cases.size(); i++) {
                System.out.println(i + ": " + cases.get(i).getModel());
            }
            System.out.print("Choose a Case index: ");
            int caseIndex = scanner.nextInt();
            if (caseIndex < 0 || caseIndex >= cases.size()) {
                System.out.println("Invalid index.");
                return;
            }
            builder.selectCase(cases.get(caseIndex));
        }

        // 8. Compatible PSU
        System.out.println("\nCompatible PSUs:");
        List<PSU> psus = builder.getCompatiblePSUs();
        if (psus.isEmpty()) {
            System.out.println("No compatible PSUs found.");
        } else {
            for (int i = 0; i < psus.size(); i++) {
                System.out.println(i + ": " + psus.get(i).getModel() + " (" + psus.get(i).getWattage() + "W)");
            }
            System.out.print("Choose a PSU index: ");
            int psuIndex = scanner.nextInt();
            if (psuIndex < 0 || psuIndex >= psus.size()) {
                System.out.println("Invalid index.");
                return;
            }
            builder.selectPSU(psus.get(psuIndex));
        }

        // Final build summary
        System.out.println("\nFinal PC Build Summary:");
        System.out.println(builder.getCurrentBuild());
    }
}
