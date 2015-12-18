package org.mbtest.mountebank;

import org.apache.maven.plugin.MojoExecutionException;
import org.mbtest.mountebank.system.MountebankCommandArgs;
import org.mbtest.mountebank.system.ProcessBuilderAdapter;

import java.io.File;
import java.io.IOException;

public class Runner {

    // making this static prevents the spawned process from
    // terminating when the Mojo finishes. Not sure why.
    private static Process mbProcess;

    private ProcessBuilderAdapter processBuilderAdapter;

    public Runner(final ProcessBuilderAdapter processBuilderAdapter) {
        this.processBuilderAdapter = processBuilderAdapter;
    }

    public void startMountebank(final File mountebankHome) throws IOException, MojoExecutionException {
        this.run(mountebankHome, MountebankCommandArgs.START);
    }

    public void stopMountebank(final File mountebankHome) throws IOException, MojoExecutionException {
        this.run(mountebankHome, MountebankCommandArgs.STOP);
    }

    private void run(final File mountebankHome, final String arg) throws IOException, MojoExecutionException {
        mbProcess = this.processBuilderAdapter.start(mountebankHome, arg);
    }

    protected static Process getProcess() {
        return mbProcess;
    }

}
