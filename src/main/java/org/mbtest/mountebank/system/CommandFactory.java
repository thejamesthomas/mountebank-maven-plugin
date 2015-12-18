package org.mbtest.mountebank.system;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;

import java.io.File;
import java.io.IOException;

import static org.mbtest.mountebank.utils.OS.*;

public class CommandFactory {

    private NodeDirectoryFinder nodeDirectoryFinder;
    private OSDetector osDetector;
    private Log logger;

    public CommandFactory(final NodeDirectoryFinder nodeDirectoryFinder,
                          final OSDetector osDetector, Log logger) {
        this.nodeDirectoryFinder = nodeDirectoryFinder;
        this.osDetector = osDetector;
        this.logger = logger;
    }

    public String getCommand(final File mountebankHome) throws IOException, MojoExecutionException {
        String currentOs = this.osDetector.getCurrentOS();
        this.logger.info("Current OS is: " + currentOs);
        final String command;

        if (OSX.isOS(currentOs)) {
            File nodeDirectory = nodeDirectoryFinder.findNodeDirectory(mountebankHome.getPath());
            command =  "./" + nodeDirectory.getName() + "/bin/node /mountebank/bin/mb";
        }
        else if (WIN_x64.isOS(currentOs)) {
            command =  mountebankHome.getAbsolutePath() + "/" + "mb.cmd";
        }
        else if (LINUX_x64.isOS(currentOs) || LINUX_x86.isOS(currentOs)) {
            command = mountebankHome.getAbsolutePath() + "/" + "mb";
        }
        else {
            throw new MojoExecutionException(currentOs + " support not implemented");
        }

        this.logger.info("Command is: " + command);
        return command;
    }
}
