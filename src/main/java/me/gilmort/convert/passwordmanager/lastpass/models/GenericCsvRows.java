package me.gilmort.convert.passwordmanager.lastpass.models;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public record GenericCsvRows(String url, String username, String password, String extra, String name, String grouping,
                             String fav, String totp) {
    public String[] getCSVRow() {
        return new String[]{url, username, password, extra, name, grouping, fav, totp};
    }

    public static String[] getHeaders() {
        return new String[]{"url","username","password","extra","name","grouping","fav","totp"};
    }
}
