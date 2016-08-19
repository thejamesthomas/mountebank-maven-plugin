package org.mbtest.mountebank;

import org.apache.maven.plugin.MojoExecutionException;
import org.mbtest.mountebank.system.MountebankCommandArgs;
import org.mbtest.mountebank.system.ProcessBuilderAdapter;

import java.io.IOException;

public class Runner {

    // making this static prevents the spawned process from
    // terminating when the Mojo finishes. Not sure why.
    private static Process mbProcess;

    private ProcessBuilderAdapter processBuilderAdapter;

    public Runner(final ProcessBuilderAdapter processBuilderAdapter) {
        this.processBuilderAdapter = processBuilderAdapter;
    }

    public void startMountebank() throws IOException, MojoExecutionException {
        this.run(MountebankCommandArgs.START);
    }

    public void stopMountebank() throws IOException, MojoExecutionException {
        this.run(MountebankCommandArgs.STOP);
    }

    private void run(final String arg) throws IOException, MojoExecutionException {
        mbProcess = this.processBuilderAdapter.start(arg);
    }

    protected static Process getProcess() {
        return mbProcess;
    }

}
