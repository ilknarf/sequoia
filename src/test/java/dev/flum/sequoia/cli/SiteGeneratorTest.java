package dev.flum.sequoia.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class SiteGeneratorTest {

    @Test
    public void siteGeneratorDoesNotFail() {
        
        Assertions.assertDoesNotThrow(() -> SiteGenerator.generate("src/main/resources/new-site/theme",
                Paths.get("src/main/resources/new-site/public").toFile().getAbsolutePath(),
                "src/main/resources/new-site/source.yml"));
    }
}
