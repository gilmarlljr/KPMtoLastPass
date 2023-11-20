package me.gilmort.convert.passwordmanager.kpm.model;

import me.gilmort.convert.passwordmanager.lastpass.models.GenericCsvRows;
import me.gilmort.convert.passwordmanager.lastpass.models.LastPass;

import java.util.ArrayList;
import java.util.List;

public record KasperskyPasswordManager(List<Websites> websites,List<Apps> apps,List<Notes> notes) {
    public LastPass toLastPass(){
        var websites = this.websites.stream()
                .map(website -> new GenericCsvRows(website.url(), website.login(), website.pass(), website.comment(), website.name(), "","0",""))
                .toList()
                ;
        var apps = this.apps.stream()
                .map(app -> new GenericCsvRows(app.name(), app.login(), app.pass(), app.comment(), app.name(), "","0",""))
                .toList()
                ;
        var notes = this.notes.stream()
                .map(note -> new GenericCsvRows("http://sn", "", "", note.text(), note.name(), "","0",""))
                .toList()
                ;
        var rows = new ArrayList<>(websites);
        rows.addAll(apps);
        rows.addAll(notes);
        return new LastPass(rows);
    }
}

