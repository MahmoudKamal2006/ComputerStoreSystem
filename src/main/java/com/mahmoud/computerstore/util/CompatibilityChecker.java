package com.mahmoud.computerstore.util;

import com.mahmoud.computerstore.model.*;

public class CompatibilityChecker {

    // CPU and Motherboard match by socket
    public static boolean isCompatible(CPU cpu, Motherboard motherboard) {
        return cpu.getSocket().trim().equalsIgnoreCase(motherboard.getSocket().trim());
    }

    // CPU and Cooling match by checking if the cooler supports the CPU socket
    public static boolean isCompatible(CPU cpu, Cooling cooler) {
        String[] compatibleSockets = cooler.getSocketCompatibility().split(",");
        for (String socket : compatibleSockets) {
            if (socket.trim().equalsIgnoreCase(cpu.getSocket().trim())) {
                return true;
            }
        }
        return false;
    }

    // RAM and Motherboard match by RAM type
    public static boolean isCompatible(CPU cpu, RAM ram, Motherboard motherboard) {
        return ram.getType().trim().equalsIgnoreCase(motherboard.getMemoryType().trim());
    }

    // PSU is compatible if wattage is sufficient
    public static boolean isCompatible(Build build, PSU psu) {
        int totalPower = 0;

        if (build.getCpu() != null) {
            totalPower += build.getCpu().getPowerConsumption();
        }

        if (build.getGpu() != null) {
            totalPower += build.getGpu().getTdp();
        }

        if (build.getRam() != null) {
            totalPower += build.getRam().getModules() * 5; // estimate 5W per RAM stick
        }

        totalPower += 50; // Estimate for storage, fans, etc.

        return psu.getWattage() >= totalPower;
    }
}
