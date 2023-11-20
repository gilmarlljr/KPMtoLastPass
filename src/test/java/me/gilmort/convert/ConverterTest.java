package me.gilmort.convert;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void convertKPMToLastPassCSV() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource("export_sample.txt").getFile());
        File outputFile = new File("output.csv");
        Converter.convertKPMToLastPassCSV(inputFile,outputFile);
    }


}