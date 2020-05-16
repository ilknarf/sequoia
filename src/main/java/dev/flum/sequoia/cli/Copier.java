package dev.flum.sequoia.cli;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemException;

import java.nio.file.Path;
import java.nio.file.Paths;


import org.zeroturnaround.zip.ZipUtil;

/**
 * Copier copies an example project structure into a new directory
 */
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

        // It is easier to get resource from inside jar as a zipped archive.
        try {

            InputStream i = Copier.class.getResourceAsStream("/new-site.zip");

            // System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
            ZipUtil.unpack(i, new File(location));

            i.close();



            // FileUtils.copyInputStreamToFile(i, Paths.get(location, "new-site.zip").toFile());

            // Using Apache commons to help copy files.
            // only works for packaged JAR
            // FileUtils.copyDirectory(Paths.get(starter.toString(), "/resources/new-site").toFile(),  f);

            System.out.println("new site created at " + location.toString());

            i.close();
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
