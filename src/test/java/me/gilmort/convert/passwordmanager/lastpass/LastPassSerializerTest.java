package me.gilmort.convert.passwordmanager.lastpass;

import me.gilmort.convert.passwordmanager.lastpass.models.GenericCsvRows;
import me.gilmort.convert.passwordmanager.lastpass.models.LastPass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LastPassSerializerTest {

    @Test
    void serialize() throws IOException {
        var lastPass = new LastPass(List.of(new GenericCsvRows("https://sitename", "user1", "password1", "algo a mais", "Test site", "test","0", "TMMNBXF73KLJGMZF")));
        LastPassSerializer.serialize(new File("output.csv"),lastPass);
    }
}