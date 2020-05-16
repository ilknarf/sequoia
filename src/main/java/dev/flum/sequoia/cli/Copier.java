package dev.flum.sequoia.cli;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

public class Copier {

    /**
     * Copy copies a project layout to a target directory.
     * @param location
     * @throws IOException when unable to copy files.
     */
    public static void Copy(String location) throws IOException {
        Path p = Paths.get(location);

        if (!p.isAbsolute()) {
            p = Paths.get(System.getProperty("user.dir"), location);
        }

        File f = p.toFile();

        if (!f.exists()) {
            f.mkdirs();
        }

        if (f.isFile()) {
            throw new FileSystemException(location + " is a file, not a directory");
        }

        // Using Apache commons to help copy files.
        FileUtils.copyDirectory(new File("src/main/resources/new-site"), f);

        System.out.println("new site created at " + f.toString());
    }
}
