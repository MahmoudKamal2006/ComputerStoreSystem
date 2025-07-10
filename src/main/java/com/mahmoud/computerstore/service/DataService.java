package com.mahmoud.computerstore.service;

import com.mahmoud.computerstore.model.*;
import com.mahmoud.computerstore.util.CSVLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to load and save component data using CSV files.
 * Includes error handling and debugging output.
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

    // Load Methods, made with ChatGPT

    // ---------- CPU ----------
    public List<CPU> loadCPUs() {
        List<CPU> list = new ArrayList<>();
        List<String[]> rows = CSVLoader.readCSV(CPU_PATH);
        System.out.println("Loading CPUs: found " + rows.size() + " rows");

        for (String[] row : rows) {
            try {
                if (row.length >= 9) {
                    list.add(new CPU(
                            Integer.parseInt(row[0].trim()),
                            row[1].trim(), row[2].trim(), row[3].trim(),
                            Integer.parseInt(row[4].trim()),
                            row[5].trim(), row[6].trim(), row[7].trim(),
                            Integer.parseInt(row[8].trim())
                    ));
                } else {
                    System.err.println("CPU row has insufficient columns: " + String.join(",", row));
                }
            } catch (Exception e) {
                System.err.println("Error parsing CPU row: " + String.join(",", row) + " - " + e.getMessage());
            }
        }
        System.out.println("Successfully loaded " + list.size() + " CPUs");
        return list;
    }

    // ---------- GPU ----------
    public List<GPU> loadGPUs() {
        List<GPU> list = new ArrayList<>();
        List<String[]> rows = CSVLoader.readCSV(GPU_PATH);
        System.out.println("Loading GPUs: found " + rows.size() + " rows");

        for (String[] row : rows) {
            try {
                if (row.length >= 8) {
                    list.add(new GPU(
                            Integer.parseInt(row[0].trim()),
                            row[1].trim(), row[2].trim(), row[3].trim(),
                            Integer.parseInt(row[4].trim()),
                            row[5].trim(), row[6].trim(),
                            Integer.parseInt(row[7].trim())
                    ));
                } else {
                    System.err.println("GPU row has insufficient columns: " + String.join(",", row));
                }
            } catch (Exception e) {
                System.err.println("Error parsing GPU row: " + String.join(",", row) + " - " + e.getMessage());
            }
        }
        System.out.println("Successfully loaded " + list.size() + " GPUs");
        return list;
    }

    // ---------- RAM ----------
    public List<RAM> loadRAM() {
        List<RAM> list = new ArrayList<>();
        List<String[]> rows = CSVLoader.readCSV(RAM_PATH);
        System.out.println("Loading RAM: found " + rows.size() + " rows");

        for (String[] row : rows) {
            try {
                if (row.length >= 8) {
                    list.add(new RAM(
                            Integer.parseInt(row[0].trim()),
                            row[1].trim(), row[2].trim(), row[3].trim(),
                            row[4].trim(), row[5].trim(),
                            Integer.parseInt(row[6].trim()), row[7].trim()
                    ));
                } else {
                    System.err.println("RAM row has insufficient columns: " + String.join(",", row));
                }
            } catch (Exception e) {
                System.err.println("Error parsing RAM row: " + String.join(",", row) + " - " + e.getMessage());
            }
        }
        System.out.println("Successfully loaded " + list.size() + " RAM modules");
        return list;
    }

    // ---------- Motherboard ----------
    public List<Motherboard> loadMotherboards() {
        List<Motherboard> list = new ArrayList<>();
        List<String[]> rows = CSVLoader.readCSV(MOTHERBOARD_PATH);
        System.out.println("Loading Motherboards: found " + rows.size() + " rows");

        for (String[] row : rows) {
            try {
                if (row.length >= 12) {
                    list.add(new Motherboard(
                            Integer.parseInt(row[0].trim()),
                            row[1].trim(), row[2].trim(), row[3].trim(), row[4].trim(),
                            row[5].trim(), Integer.parseInt(row[6].trim()), row[7].trim(), row[8].trim(),
                            row[9].trim(), row[10].trim(), row[11].trim(),
                            row.length > 12 ? row[12].trim() : "Ethernet" // Default network if missing
                    ));
                } else {
                    System.err.println("Motherboard row has insufficient columns: " + String.join(",", row));
                }
            } catch (Exception e) {
                System.err.println("Error parsing Motherboard row: " + String.join(",", row) + " - " + e.getMessage());
            }
        }
        System.out.println("Successfully loaded " + list.size() + " Motherboards");
        return list;
    }

    // ---------- Storage ----------
    public List<Storage> loadStorage() {
        List<Storage> list = new ArrayList<>();
        List<String[]> rows = CSVLoader.readCSV(STORAGE_PATH);
        System.out.println("Loading Storage: found " + rows.size() + " rows");

        for (String[] row : rows) {
            try {
                if (row.length >= 9) {
                    list.add(new Storage(
                            Integer.parseInt(row[0].trim()),
                            row[1].trim(), row[2].trim(), row[3].trim(),
                            row[4].trim(), row[5].trim(), row[6].trim(),
                            row[7].trim(), row[8].trim()
                    ));
                } else {
                    System.err.println("Storage row has insufficient columns: " + String.join(",", row));
                }
            } catch (Exception e) {
                System.err.println("Error parsing Storage row: " + String.join(",", row) + " - " + e.getMessage());
            }
        }
        System.out.println("Successfully loaded " + list.size() + " Storage devices");
        return list;
    }

    // ---------- PSU ----------
    public List<PSU> loadPSUs() {
        List<PSU> list = new ArrayList<>();
        List<String[]> rows = CSVLoader.readCSV(PSU_PATH);
        System.out.println("Loading PSUs: found " + rows.size() + " rows");

        for (String[] row : rows) {
            try {
                if (row.length >= 8) {
                    // Combine remaining columns for connectors
                    String connectors = "";
                    for (int i = 7; i < row.length; i++) {
                        connectors += row[i].trim();
                        if (i < row.length - 1) connectors += ",";
                    }

                    list.add(new PSU(
                            Integer.parseInt(row[0].trim()),
                            row[1].trim(), row[2].trim(), Integer.parseInt(row[3].trim()),
                            row[4].trim(), row[5].trim(), row[6].trim(), connectors
                    ));
                } else {
                    System.err.println("PSU row has insufficient columns: " + String.join(",", row));
                }
            } catch (Exception e) {
                System.err.println("Error parsing PSU row: " + String.join(",", row) + " - " + e.getMessage());
            }
        }
        System.out.println("Successfully loaded " + list.size() + " PSUs");
        return list;
    }

    // ---------- Case ----------
    public List<Case> loadCases() {
        List<Case> list = new ArrayList<>();
        List<String[]> rows = CSVLoader.readCSV(CASE_PATH);
        System.out.println("Loading Cases: found " + rows.size() + " rows");

        for (String[] row : rows) {
            try {
                if (row.length >= 8) {
                    list.add(new Case(
                            Integer.parseInt(row[0].trim()),
                            row[1].trim(), row[2].trim(), row[3].trim(),
                            Integer.parseInt(row[4].trim()),
                            row[5].trim(), row[6].trim(), row[7].trim()
                    ));
                } else {
                    System.err.println("Case row has insufficient columns: " + String.join(",", row));
                }
            } catch (Exception e) {
                System.err.println("Error parsing Case row: " + String.join(",", row) + " - " + e.getMessage());
            }
        }
        System.out.println("Successfully loaded " + list.size() + " Cases");
        return list;
    }

    // ---------- Cooling ----------
    public List<Cooling> loadCooling() {
        List<Cooling> list = new ArrayList<>();
        List<String[]> rows = CSVLoader.readCSV(COOLING_PATH);
        System.out.println("Loading Cooling: found " + rows.size() + " rows");

        for (String[] row : rows) {
            try {
                if (row.length >= 10) {
                    // Combine socket compatibility columns (columns 4-6 in your current data)
                    String socketCompatibility = row[4].trim() + "," + row[5].trim() + "," + row[6].trim();

                    list.add(new Cooling(
                            Integer.parseInt(row[0].trim()),
                            row[1].trim(), row[2].trim(), row[3].trim(),
                            socketCompatibility,
                            Integer.parseInt(row[7].trim()),
                            row[8].trim(), row[9].trim(),
                            Integer.parseInt(row[10].trim())
                    ));
                } else {
                    System.err.println("Cooling row has insufficient columns: " + String.join(",", row));
                }
            } catch (Exception e) {
                System.err.println("Error parsing Cooling row: " + String.join(",", row) + " - " + e.getMessage());
            }
        }
        System.out.println("Successfully loaded " + list.size() + " Cooling solutions");
        return list;
    }



    // Save methods, made manually with the help of Claude AI to clone

    // ----------CPU---------
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

    // ---------GPU---------
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

    // ---------Case---------
    public void saveCases(List<Case> list) {
        List<String[]> rows = new ArrayList<>();
        for (Case c : list) {
            rows.add(new String[]{
                    String.valueOf(c.getId()),
                    c.getBrand(),
                    c.getModel(),
                    c.getFormFactorSupport(),
                    String.valueOf(c.getMaxGpuLength()),
                    c.getDriveBays(),
                    c.getFanSupport(),
                    c.getRadiatorSupport()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/case.csv", rows);
    }

    // ---------Cooling---------
    public void saveCooling(List<Cooling> list) {
        List<String[]> rows = new ArrayList<>();
        for (Cooling c : list) {
            rows.add(new String[]{
                    String.valueOf(c.getId()),
                    c.getBrand(),
                    c.getModel(),
                    c.getType(),
                    c.getSocketCompatibility(),
                    String.valueOf(c.getFanSize()),
                    c.getRadiatorSize(),
                    c.getNoiseLevel(),
                    String.valueOf(c.getRpm())
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/cooling.csv", rows);
    }

    //---------Motherboard---------
    public void saveMotherboards(List<Motherboard> list) {
        List<String[]> rows = new ArrayList<>();
        for (Motherboard mb : list) {
            rows.add(new String[]{
                    String.valueOf(mb.getId()),
                    mb.getBrand(),
                    mb.getModel(),
                    mb.getSocket(),
                    mb.getChipset(),
                    mb.getFormFactor(),
                    String.valueOf(mb.getMemorySlots()),
                    mb.getMaxMemory(),
                    mb.getMemoryType(),
                    mb.getPcieSlots(),
                    mb.getStorageInterfaces(),
                    mb.getUsbPorts(),
                    mb.getNetwork()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/motherboard.csv", rows);
    }

    // ---------PSU---------
    public void savePSUs(List<PSU> list) {
        List<String[]> rows = new ArrayList<>();
        for (PSU psu : list) {
            // Split connectors back into individual components for CSV storage
            String[] connectorParts = psu.getConnectors().split(",");

            // Create base row with main PSU data
            List<String> rowData = new ArrayList<>();
            rowData.add(String.valueOf(psu.getId()));
            rowData.add(psu.getBrand());
            rowData.add(psu.getModel());
            rowData.add(String.valueOf(psu.getWattage()));
            rowData.add(psu.getEfficiencyRating());
            rowData.add(psu.getModular());
            rowData.add(psu.getFormFactor());

            // Add connector parts as separate columns
            for (String connector : connectorParts) {
                rowData.add(connector.trim());
            }

            rows.add(rowData.toArray(new String[0]));
        }
        CSVLoader.writeCSV("src/main/resources/data/psu.csv", rows);
    }

    // ---------RAM---------
    public void saveRAM(List<RAM> list) {
        List<String[]> rows = new ArrayList<>();
        for (RAM ram : list) {
            rows.add(new String[]{
                    String.valueOf(ram.getId()),
                    ram.getBrand(),
                    ram.getModel(),
                    ram.getType(),
                    ram.getCapacity(),
                    ram.getSpeed(),
                    String.valueOf(ram.getModules()),
                    ram.getVoltage()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/ram.csv", rows);
    }

    // ---------Storage---------
    public void saveStorage(List<Storage> list) {
        List<String[]> rows = new ArrayList<>();
        for (Storage storage : list) {
            rows.add(new String[]{
                    String.valueOf(storage.getId()),
                    storage.getBrand(),
                    storage.getModel(),
                    storage.getType(),
                    storage.getCapacity(),
                    storage.getInterfaceType(),
                    storage.getFormFactor(),
                    storage.getReadSpeed(),
                    storage.getWriteSpeed()
            });
        }
        CSVLoader.writeCSV("src/main/resources/data/storage.csv", rows);
    }
}