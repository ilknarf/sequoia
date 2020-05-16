package dev.flum.sequoia.builder;

import java.io.FileReader;

import java.io.IOException;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParserTest {

    @Test
    public void parseNestedYamlShouldBeNested() throws Exception {
        Parser parser = new Parser();

        FileReader fr = new FileReader("src/test/resources/example.yml");

        Object o = parser.load(fr);

        Assertions.assertNotNull(o, "yaml parsing not null");
    }

    @Test
    public void invalidYamlThrowsError() throws Exception {
        Parser parser = new Parser();

        FileReader fr = new FileReader("src/test/resources/example2.yml");

        Assertions.assertThrows(IOException.class, () -> parser.load(fr));
    }
}
