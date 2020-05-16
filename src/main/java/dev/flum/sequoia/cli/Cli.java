package dev.flum.sequoia.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Paths;
import java.util.concurrent.Callable;


/**
 * Cli creates the object that directly parses command-line
 * arguments for the CLI
 */
@Command(name = "sequoia", mixinStandardHelpOptions = true,
        version = "new 1.0", description =
        "sequoia command line")
public class Cli implements Callable<Integer> {

    @Parameters (index = "0", description = "new or build")
    String command;

    @Parameters(index = "1", description = "directory to copy project seed", defaultValue = ".")
    String destination;

    @Override
    public Integer call() {
        if (command.equals("build")) {
            try {
                String userDir;
                SiteGenerator.generate((userDir = System.getProperty("user.dir")),
                        destination, Paths.get(userDir, "source.yml").toString());
            } catch(Exception e) {
                System.err.println(e + "\nCheck if in project root or project structure corrupted");
            }
        } else if (command.equals("new")) {
            try {
                Copier.Copy(destination);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e + "\nUnable to copy project");
            }
        } else {
            System.err.println("Invalid command");
        }

        return 0;
    }
}
