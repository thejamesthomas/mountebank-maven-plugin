package org.mbtest.mountebank;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.mbtest.mountebank.system.CommandFactory;
import org.mbtest.mountebank.system.OSDetector;
import org.mbtest.mountebank.system.ProcessBuilderAdapter;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;

import java.io.File;
import java.io.IOException;

public class Runner {

    private static Process mbProcess;
    private NodeDirectoryFinder nodeDirectoryFinder;
    private Log logger;

    public Runner(final NodeDirectoryFinder nodeDirectoryFinder, final Log logger) {
        this.nodeDirectoryFinder = nodeDirectoryFinder;
        this.logger = logger;
    }

    public void startMountebank(final File targetDirectory) throws IOException, MojoExecutionException {
        this.run(targetDirectory, "start");
    }

    public void stopMountebank(final File targetDirectory) throws IOException, MojoExecutionException {
        this.run(targetDirectory, "stop");
    }

    private void run(final File targetDirectory, final String arg) throws IOException, MojoExecutionException {
        final CommandFactory commandFactory = new CommandFactory(targetDirectory, this.nodeDirectoryFinder, new OSDetector(), this.logger);
        final ProcessBuilderAdapter processBuilderAdapter = new ProcessBuilderAdapter(commandFactory, arg);
        mbProcess = processBuilderAdapter.start();
    }
}
