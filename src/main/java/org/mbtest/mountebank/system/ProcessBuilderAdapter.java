package org.mbtest.mountebank.system;

import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public class ProcessBuilderAdapter {

    private String arg;
    private CommandFactory commandFactory;

    public ProcessBuilderAdapter(final CommandFactory commandFactory, final String arg) {
        this.commandFactory = commandFactory;
        this.arg = arg;
    }

    public Process start() throws IOException, MojoExecutionException {
        final String command = this.commandFactory.getCommand();
        final ProcessBuilder processBuilder = new ProcessBuilder().command(command, arg);
        return processBuilder.start();
    }
}
