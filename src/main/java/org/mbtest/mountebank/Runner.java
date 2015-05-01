package org.mbtest.mountebank;

import org.mbtest.mountebank.utils.NodeDirectoryFinder;

import java.io.File;
import java.io.IOException;

import static org.mbtest.mountebank.utils.OS.*;

public class Runner {

    private final Runtime runtime;
    private Process mbProcess;
    private NodeDirectoryFinder nodeDirectoryFinder;

    public Runner(Runtime runtime, NodeDirectoryFinder nodeDirectoryFinder) {
        this.runtime = runtime;
        this.nodeDirectoryFinder = nodeDirectoryFinder;
    }

    public void runMountebank(final File targetDirectory) throws IOException, InterruptedException {
        String currentOs = System.getProperty(OS_NAME);

        if(OSX.isOS(currentOs)) {
            File nodeDirectory = new File(nodeDirectoryFinder.findNodeDirectory(targetDirectory.getPath()));
            String command = "./" + nodeDirectory.getName() + "/bin/node /mountebank/bin/mb";
            mbProcess = runtime.exec(command, new String[]{}, nodeDirectory);
        }
        if(WIN_x64.isOS(currentOs)) {
            String command = "node.exe mountebank/bin/mb";
            mbProcess = runtime.exec(command, new String[]{}, targetDirectory);
        }
    }

    public void stopMountebank() throws IOException {
        mbProcess.destroy();
    }
}
