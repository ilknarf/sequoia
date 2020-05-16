package dev.flum.sequoia.builder.schemas;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Text is the theme schema that just appends each of its
 * properties.
 */
public class Text {

    List<Key> keys;

    /**
     * Creates a Text object, which wraps around each
     * of the values of the given LinkedHashMap
     * @param m
     */
    public Text(LinkedHashMap<String, Object> m) {
        keys = new ArrayList<>();

        for (String key : m.keySet()) {
            keys.add(new Key((String) m.get(key)));
        }
    }

    static class Key {
        String body;

        Key(String body) {
            this.body = body;
        }
    }

    public List<Key> keys() {
        return keys;
    }
}
