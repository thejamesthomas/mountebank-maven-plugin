package org.mbtest.mountebank.system;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;

import java.io.File;
import java.io.IOException;

import static org.mbtest.mountebank.utils.OS.*;

public class CommandFactory {

    private NodeDirectoryFinder nodeDirectoryFinder;
    private File mountebankHome;
    private OSDetector osDetector;
    private Log logger;

    public CommandFactory(final NodeDirectoryFinder nodeDirectoryFinder, final File mountebankHome,
                          final OSDetector osDetector, final Log logger) {
        this.nodeDirectoryFinder = nodeDirectoryFinder;
        this.mountebankHome = mountebankHome;
        this.osDetector = osDetector;
        this.logger = logger;
    }

    public String getCommand() throws IOException, MojoExecutionException {
        String currentOs = this.osDetector.getCurrentOS();
        this.logger.info("Current OS is: " + currentOs);
        final String command;

        if (OSX.isOS(currentOs)) {
            File nodeDirectory = nodeDirectoryFinder.findNodeDirectory(this.mountebankHome.getPath());
            //command =  "./" + nodeDirectory.getName() + "/bin/node /mountebank/bin/mb";
	    command =  this.mountebankHome.getAbsolutePath() + "/" + "mb";
        }
        else if (WIN_x64.isOS(currentOs)) {
            command =  this.mountebankHome.getAbsolutePath() + "/" + "mb.cmd";
        }
        else if (LINUX_x64.isOS(currentOs)) {
            command = this.mountebankHome.getAbsolutePath() + "/" + "mb";
        }
        else {
            throw new MojoExecutionException(currentOs + " support not implemented");
        }

        this.logger.info("Command is: " + command);
        return command;
    }
}
