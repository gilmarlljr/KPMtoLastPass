package me.gilmort.convert.passwordmanager.kpm;

import me.gilmort.convert.passwordmanager.kpm.model.Apps;
import me.gilmort.convert.passwordmanager.kpm.model.KasperskyPasswordManager;
import me.gilmort.convert.passwordmanager.kpm.model.Notes;
import me.gilmort.convert.passwordmanager.kpm.model.Websites;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;

public class KPMSerializer {
    public static KasperskyPasswordManager deserialize(InputStream inputStream) throws IOException {
        var websites = new ArrayList<Websites>();

        var apps = new ArrayList<Apps>();
        var notes = new ArrayList<Notes>();
        try (InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {

            String line;
            var auxStrings = new LinkedList<String>();
            while (!(line = reader.readLine()).equals("Applications")) {
                if (line.startsWith("Website name")) {
                    auxStrings.add(0,line.substring("Website name:".length()).trim());
                }
                if (line.startsWith("Website URL:")) {
                    auxStrings.add(1,line.substring("Website URL:".length()).trim());
                }
                if (line.startsWith("Login name:")) {
                    auxStrings.add( 2,line.substring("Login name:".length()).trim());
                }
                if (line.startsWith("Login:")) {
                    auxStrings.add(3,line.substring("Login:".length()).trim());
                }
                if (line.startsWith("Password:")) {
                    auxStrings.add(4,line.substring("Password:".length()).trim());
                }
                if (line.startsWith("Comment:")) {
                    auxStrings.add(5,line.substring("Comment:".length()).trim());
                }
                if(line.trim().equals("---")){
                    websites.add(fromStringParameters(Websites.class,auxStrings.toArray(new String[0])));
                    auxStrings = new LinkedList<>();
                }
            }
            //Read Lines from Apps to Notes
            while (!(line = reader.readLine()).equals("Notes")) {
                if (line.startsWith("Application")) {
                    auxStrings.add(0,line.substring("Application:".length()).trim());
                }
                if (line.startsWith("Login name:")) {
                    auxStrings.add(1,line.substring("Login name:".length()).trim());
                }
                if (line.startsWith("Login:")) {
                    auxStrings.add(2,line.substring("Login:".length()).trim());
                }
                if (line.startsWith("Password:")) {
                    auxStrings.add(3,line.substring("Password:".length()).trim());
                }
                if (line.startsWith("Comment:")) {
                    auxStrings.add(4,line.substring("Comment:".length()).trim());
                }
                if(line.trim().equals("---")){
                    apps.add(fromStringParameters(Apps.class,auxStrings.toArray(new String[0])));
                    auxStrings = new LinkedList<>();
                }
            }
            //Read Lines from Apps to Notes
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name")) {
                    auxStrings.add(0,line.substring("Name:".length()).trim());
                }
                if (line.startsWith("Text:")) {
                    auxStrings.add(1,line.substring("Text:".length()).trim());
                }
                if(line.trim().equals("---")){
                    notes.add(fromStringParameters(Notes.class,auxStrings.toArray(new String[0])));
                    auxStrings = new LinkedList<>();
                }

            }
        } catch (Exception e) {
            throw new IOException("Error reading lines from resource", e);
        }
        return new KasperskyPasswordManager(websites, apps, notes);
    }

    public static <T> T fromStringParameters(Class<T> clazz, String[] params) throws Exception {
        var constructor = clazz.getDeclaredConstructors()[0];
        if(constructor.getParameterTypes().length != params.length){
            throw new Exception("Params size ("+params.length+") is invalid for "+ clazz.getSimpleName());
        }
        return (T) constructor.newInstance(params);
    }
}
