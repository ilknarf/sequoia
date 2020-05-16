package dev.flum.sequoia.builder;

import java.io.IOException;
import java.io.Reader;

import org.yaml.snakeyaml.Yaml;

import java.util.LinkedHashMap;

/**
 * Parser parses the YAML input into a processable graph.
 */
public class Parser {
    Yaml yaml;

    public Parser() {
        yaml = new Yaml();
    }

    /**
     *
     * @param reader, an io.Reader
     * @return program, a LinkedHashMap
     */
    public LinkedHashMap<String, Object> load(Reader reader) throws IOException {
        Object o =  yaml.load(reader);

        if (o instanceof LinkedHashMap) {
            return (LinkedHashMap<String, Object>) o;
        } else {
            throw new IOException("YAML format invalid. Create new starter first");
        }
    }
}
