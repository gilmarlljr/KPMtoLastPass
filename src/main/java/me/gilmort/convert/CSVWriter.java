package me.gilmort.convert;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVWriter {
    public static File writeCSV(File csvOutputFile, List<String[]> data) throws IOException {
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            data.stream()
                    .map(CSVWriter::convertToCSV)
                    .forEach(pw::println);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvOutputFile))) {
            for (String[] row : data) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    line.append(row[i]);
                    if (i < row.length - 1) {
                        line.append(",");
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
        }
        return csvOutputFile;
    }

    private static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(CSVWriter::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
