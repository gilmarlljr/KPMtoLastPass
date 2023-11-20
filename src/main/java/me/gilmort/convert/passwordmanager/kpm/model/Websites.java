package me.gilmort.convert.passwordmanager.kpm.model;

import java.lang.reflect.Constructor;

public record Websites(String name,
                       String url,
                       String loginName,
                       String login,
                       String pass,
                       String comment
) {

}
