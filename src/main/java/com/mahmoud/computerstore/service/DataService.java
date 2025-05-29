package com.mahmoud.computerstore.service;

import com.mahmoud.computerstore.model.*;
import com.mahmoud.computerstore.util.CSVLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to load and save component data using CSV files.
 */
public class DataService {

    private static final String CPU_PATH = "/data/cpu.csv";
    private static final String GPU_PATH = "/data/gpu.csv";
    private static final String RAM_PATH = "/data/ram.csv";
    private static final String MOTHERBOARD_PATH = "/data/motherboard.csv";
    private static final String STORAGE_PATH = "/data/storage.csv";
    private static final String PSU_PATH = "/data/psu.csv";
    private static final String CASE_PATH = "/data/case.csv";
    private static final String COOLING_PATH = "/data/cooling.csv";

    // ---------- CPU ----------
    public List<CPU> loadCPUs() {
        List<CPU> list = new ArrayList<>();
        for (String[] row : CSVLoader.readCSV(CPU_PATH)) {
            try {
                list.add(new CPU(
                        Integer.parseInt(row[0]),
                        row[1], row[2], row[3],
                        Integer.parseInt(row[4]),
                        row[5], row[6], row[7],
                        Integer.parseInt(row[8])
                ));
            } catch (Exception ignored) {}
        }
        return list;
    }

    public void saveCPUs(List<CPU> list) {
        List<String[]> rows = new ArrayList<>();
        for (CPU c : list) {
            rows.add(new String[]{
                    String.valueOf(c.getId()), c.getBrand(), c.getModel(), c.getSocket(),
                    String.valueOf(c.getTdp()), c.getChipsetCompatibility(), c.getPcieVersion(),
                    c.getMemorySupport(), String.valueOf(c.getPowerConsumption())
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/cpu.csv", rows);
    }

    // ---------- GPU ----------
    public List<GPU> loadGPUs() {
        List<GPU> list = new ArrayList<>();
        for (String[] row : CSVLoader.readCSV(GPU_PATH)) {
            try {
                list.add(new GPU(
                        Integer.parseInt(row[0]),
                        row[1], row[2], row[3],
                        Integer.parseInt(row[4]),
                        row[5], row[6],
                        Integer.parseInt(row[7])
                ));
            } catch (Exception ignored) {}
        }
        return list;
    }

    public void saveGPUs(List<GPU> list) {
        List<String[]> rows = new ArrayList<>();
        for (GPU g : list) {
            rows.add(new String[]{
                    String.valueOf(g.getId()), g.getBrand(), g.getModel(), g.getInterfaceType(),
                    String.valueOf(g.getTdp()), g.getVram(), g.getPowerConnectors(),
                    String.valueOf(g.getLength())
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/gpu.csv", rows);
    }

    // ---------- RAM ----------
    public List<RAM> loadRAM() {
        List<RAM> list = new ArrayList<>();
        for (String[] row : CSVLoader.readCSV(RAM_PATH)) {
            try {
                list.add(new RAM(
                        Integer.parseInt(row[0]),
                        row[1], row[2], row[3],
                        row[4], row[5],
                        Integer.parseInt(row[6]), row[7]
                ));
            } catch (Exception ignored) {}
        }
        return list;
    }

    public void saveRAM(List<RAM> list) {
        List<String[]> rows = new ArrayList<>();
        for (RAM r : list) {
            rows.add(new String[]{
                    String.valueOf(r.getId()), r.getBrand(), r.getModel(), r.getType(),
                    r.getCapacity(), r.getSpeed(), String.valueOf(r.getModules()), r.getVoltage()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/ram.csv", rows);
    }

    // ---------- Motherboard ----------
    public List<Motherboard> loadMotherboards() {
        List<Motherboard> list = new ArrayList<>();
        for (String[] row : CSVLoader.readCSV(MOTHERBOARD_PATH)) {
            try {
                list.add(new Motherboard(
                        Integer.parseInt(row[0]),
                        row[1], row[2], row[3], row[4],
                        row[5], Integer.parseInt(row[6]), row[7], row[8],
                        row[9], row[10], row[11], row[12]
                ));
            } catch (Exception ignored) {}
        }
        return list;
    }

    public void saveMotherboards(List<Motherboard> list) {
        List<String[]> rows = new ArrayList<>();
        for (Motherboard m : list) {
            rows.add(new String[]{
                    String.valueOf(m.getId()), m.getBrand(), m.getModel(), m.getSocket(), m.getChipset(),
                    m.getFormFactor(), String.valueOf(m.getMemorySlots()), m.getMaxMemory(), m.getMemoryType(),
                    m.getPcieSlots(), m.getStorageInterfaces(), m.getUsbPorts(), m.getNetwork()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/motherboard.csv", rows);
    }

    // ---------- Storage ----------
    public List<Storage> loadStorage() {
        List<Storage> list = new ArrayList<>();
        for (String[] row : CSVLoader.readCSV(STORAGE_PATH)) {
            try {
                list.add(new Storage(
                        Integer.parseInt(row[0]),
                        row[1], row[2], row[3],
                        row[4], row[5], row[6],
                        row[7], row[8]
                ));
            } catch (Exception ignored) {}
        }
        return list;
    }

    public void saveStorage(List<Storage> list) {
        List<String[]> rows = new ArrayList<>();
        for (Storage s : list) {
            rows.add(new String[]{
                    String.valueOf(s.getId()), s.getBrand(), s.getModel(), s.getType(),
                    s.getCapacity(), s.getInterfaceType(), s.getFormFactor(),
                    s.getReadSpeed(), s.getWriteSpeed()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/storage.csv", rows);
    }

    // ---------- PSU ----------
    public List<PSU> loadPSUs() {
        List<PSU> list = new ArrayList<>();
        for (String[] row : CSVLoader.readCSV(PSU_PATH)) {
            try {
                list.add(new PSU(
                        Integer.parseInt(row[0]),
                        row[1], row[2], Integer.parseInt(row[3]),
                        row[4], row[5], row[6], row[7]
                ));
            } catch (Exception ignored) {}
        }
        return list;
    }

    public void savePSUs(List<PSU> list) {
        List<String[]> rows = new ArrayList<>();
        for (PSU p : list) {
            rows.add(new String[]{
                    String.valueOf(p.getId()), p.getBrand(), p.getModel(),
                    String.valueOf(p.getWattage()), p.getEfficiencyRating(),
                    p.getModular(), p.getFormFactor(), p.getConnectors()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/psu.csv", rows);
    }

    // ---------- Case ----------
    public List<Case> loadCases() {
        List<Case> list = new ArrayList<>();
        for (String[] row : CSVLoader.readCSV(CASE_PATH)) {
            try {
                list.add(new Case(
                        Integer.parseInt(row[0]),
                        row[1], row[2], row[3],
                        Integer.parseInt(row[4]),
                        row[5], row[6], row[7]
                ));
            } catch (Exception ignored) {}
        }
        return list;
    }

    public void saveCases(List<Case> list) {
        List<String[]> rows = new ArrayList<>();
        for (Case c : list) {
            rows.add(new String[]{
                    String.valueOf(c.getId()), c.getBrand(), c.getModel(),
                    c.getFormFactorSupport(), String.valueOf(c.getMaxGpuLength()),
                    c.getDriveBays(), c.getFanSupport(), c.getRadiatorSupport()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/case.csv", rows);
    }

    // ---------- Cooling ----------
    public List<Cooling> loadCooling() {
        List<Cooling> list = new ArrayList<>();
        for (String[] row : CSVLoader.readCSV(COOLING_PATH)) {
            try {
                list.add(new Cooling(
                        Integer.parseInt(row[0]),
                        row[1], row[2], row[3], row[4],
                        Integer.parseInt(row[5]),
                        row[6], row[7],
                        Integer.parseInt(row[8])
                ));
            } catch (Exception ignored) {}
        }
        return list;
    }

    public void saveCooling(List<Cooling> list) {
        List<String[]> rows = new ArrayList<>();
        for (Cooling c : list) {
            rows.add(new String[]{
                    String.valueOf(c.getId()), c.getBrand(), c.getModel(), c.getType(),
                    c.getSocketCompatibility(), String.valueOf(c.getFanSize()), c.getRadiatorSize(),
                    c.getNoiseLevel(), String.valueOf(c.getRpm())
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/cooling.csv", rows);
    }
}
