package org.mbtest.mountebank.system;

import java.io.IOException;

public class ProcessBuilderWrapper {

    public Process start(final String command, final String arg) throws IOException {
        final ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command, arg);
        return processBuilder.start();
    }
}
