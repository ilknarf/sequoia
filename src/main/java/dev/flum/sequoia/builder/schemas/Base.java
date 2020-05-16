package dev.flum.sequoia.builder.schemas;

import java.util.LinkedHashMap;

public class Base {

    String name;
    String content;

    public Base(LinkedHashMap<String, Object> m) throws ClassCastException {
        if (!m.containsKey("content")) {
            content = "";
        } else {
            content = (String) m.get("content");
        }

        if (!m.containsKey("name")) {
            name = "";
        } else {
            name = (String) m.get("name");
        }
    }
}
