package me.gilmort.convert.passwordmanager.lastpass;

import me.gilmort.convert.CSVWriter;
import me.gilmort.convert.passwordmanager.lastpass.models.GenericCsvRows;
import me.gilmort.convert.passwordmanager.lastpass.models.LastPass;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastPassSerializer {
    public static void serialize(File outputFile, LastPass lastPass) throws IOException {
        List<String[]> data = new ArrayList<>(Collections.singleton(GenericCsvRows.getHeaders()));
        data.addAll(lastPass.rows().stream().map(GenericCsvRows::getCSVRow).toList());
        CSVWriter.writeCSV(outputFile, data);
    }
}
