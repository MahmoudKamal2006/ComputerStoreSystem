package com.mahmoud.computerstore.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to load and save CSV files.
 */
public class CSVLoader {

    /**
     * Reads a CSV file from the resources folder and returns the rows as List<String[]>
     *
     * @param filePath path from the resources root (e.g., "/data/cpu.csv")
     * @return list of rows, each row is a String array of fields
     */
    public static List<String[]> readCSV(String filePath) {
        List<String[]> rows = new ArrayList<>();

        try (InputStream is = CSVLoader.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1); // -1 keeps empty strings
                rows.add(fields);
            }

        } catch (IOException | NullPointerException e) {
            System.err.println("Error reading CSV file: " + filePath);
            e.printStackTrace();
        }

        return rows;
    }

    /**
     * Writes a list of String[] rows to a CSV file.
     *
     * @param filePath file path on disk (e.g., "src/main/resources/data/cpu.csv")
     * @param data     list of string arrays (rows)
     */
    public static void writeCSV(String filePath, List<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + filePath);
            e.printStackTrace();
        }
    }
}
