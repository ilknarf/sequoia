package dev.flum.sequoia.cli;

import dev.flum.sequoia.builder.Constructor;
import dev.flum.sequoia.builder.Parser;
import dev.flum.sequoia.exceptions.InvalidlyShapedDeclarationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SiteGenerator {

    /**
     * SiteGenerator creates a website layout inside a target directory, using the classes in
     * dev.flum.sequoia.builder.
     * @param templateLocation
     * @param destination
     * @param source
     * @throws IOException
     */
    public static void generate (String templateLocation, String destination, String source) throws IOException {

        try {
            Constructor constructor = new Constructor(templateLocation);

            Parser parser = new Parser();

            LinkedHashMap<String, Object> site = parser.load(new FileReader(source));

            HashMap<String, String> siteRender = constructor.build(site);

            int i = 0;
            for (String key : siteRender.keySet()) {

                File f = Paths.get(destination, key, "index.html").toFile();

                File p;

                if (! (p = f.getParentFile()).exists()) {
                    p.mkdirs();
                }
                if (p.isFile()) {
                    throw new IOException("directory " + p.toString() + " exists as file");
                }

                FileWriter fw = new FileWriter(f);

                fw.write(siteRender.get(key));

                fw.close();

                i++;
            }

            System.out.println("Successfully created " + i + " pages!");

        } catch (InvalidlyShapedDeclarationException e) {
            System.err.println(e.toString());

        }
    }
}
