package me.gilmort;

import me.gilmort.convert.Converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0 || args.length > 3 || args[0].equalsIgnoreCase("help")) {
            printHelp();
            return;
        }

        var inputFile = new File(args[0]);
        var outputFile = new File(args.length > 1 ? args[1] : "output.csv");

        try {
            Converter.convertKPMToLastPassCSV(inputFile, outputFile);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }



    private static void printHelp() {
        System.out.println("Usage: java -jar KPMtoLastPass.jar <inputFile> [<outputFile>]");
        System.out.println("    - inputFile: is the KasperskyPasswordManager file with the exported data");
        System.out.println("    - outputFile: is the file that will be used on the LastPass");
        System.out.println("   or: java -jar KPMtoLastPass.jar help");
        System.out.println();
        System.out.println("If no outputFile is specified, the default is 'output.csv'.");
        System.out.println("Example: java -jar KPMtoLastPass.jar input.txt output.csv");
    }
}