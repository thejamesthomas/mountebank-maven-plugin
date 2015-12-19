package org.mbtest.mountebank.system;

import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public class ProcessBuilderAdapter {

    private ProcessBuilderWrapper processBuilderWrapper;
    private CommandFactory commandFactory;

    public ProcessBuilderAdapter(final ProcessBuilderWrapper processBuilderWrapper, final CommandFactory commandFactory) {
        this.processBuilderWrapper = processBuilderWrapper;
        this.commandFactory = commandFactory;
    }

    public Process start(final String arg) throws IOException, MojoExecutionException {
        final String command = this.commandFactory.getCommand();
        return this.processBuilderWrapper.start(command, arg);
    }
}
