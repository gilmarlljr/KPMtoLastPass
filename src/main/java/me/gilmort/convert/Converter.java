package me.gilmort.convert;

import me.gilmort.convert.passwordmanager.kpm.KPMSerializer;
import me.gilmort.convert.passwordmanager.lastpass.LastPassSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Converter {

    public static void convertKPMToLastPassCSV(File inputFile, File outputFile) throws IOException {
        var kpm = KPMSerializer.deserialize(new FileInputStream(inputFile));
        LastPassSerializer.serialize(outputFile,kpm.toLastPass());
    }
}
