package org.mbtest.mountebank;

import org.junit.Ignore;
import org.junit.Test;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;
import org.mbtest.mountebank.utils.NodeDirectoryVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import static org.mbtest.mountebank.utils.OS.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class RunnerTest {
    @Ignore
    @Test
    public void shouldRunMountebank() throws IOException, InterruptedException {
            Runner runner = new Runner(Runtime.getRuntime(), new NodeDirectoryFinder(new NodeDirectoryVisitor(), FileSystems.getDefault()));
        runner.runMountebank(new File("target/mountebank-v1.2.56-darwin-x64"));
        runner.stopMountebank();
    }

    @Test
    public void shouldDestroyTheProcessWhenStoppingMountebank() throws IOException, InterruptedException {
        Process process = mock(Process.class);

        Runtime runtime = mock(Runtime.class);
        when(runtime.exec(anyString(), any(String[].class), any(File.class))).thenReturn(process);

        File targetDirectory = mock(File.class);
        when(targetDirectory.list()).thenReturn(new String[]{});

        NodeDirectoryFinder nodeDirectoryFinder = mock(NodeDirectoryFinder.class);
        when(nodeDirectoryFinder.findNodeDirectory(targetDirectory.getPath())).thenReturn("node-v0.10.37-darwin-x64");

        Runner runner = new Runner(runtime, nodeDirectoryFinder);

        runner.runMountebank(targetDirectory);
        runner.stopMountebank();

        verify(process, times(1)).destroy();
    }

    @Test
    public void shouldRunTheCorrectCommandWhenRunningAnOSXArchiveOnOSX() throws IOException, InterruptedException {
        System.setProperty(OS_NAME, OSX.getName());

        File targetDirectory = mock(File.class);
        when(targetDirectory.list()).thenReturn(new String[]{"mb", "mountebank", "node-v0.10.37-darwin-x64"});
        when(targetDirectory.getPath()).thenReturn("target");

        NodeDirectoryFinder nodeDirectoryFinder = mock(NodeDirectoryFinder.class);
        when(nodeDirectoryFinder.findNodeDirectory(targetDirectory.getPath())).thenReturn("node-v0.10.37-darwin-x64");

        Runtime runtime = mock(Runtime.class);
        Runner runner = new Runner(runtime, nodeDirectoryFinder);

        runner.runMountebank(targetDirectory);

        verify(runtime, times(1)).exec(eq("./node-v0.10.37-darwin-x64/bin/node /mountebank/bin/mb"), any(String[].class), any(File.class));
    }

    @Test
    public void shouldRunTheCorrectCommandWhenRunningAWindowsArchiveOnWindows() throws IOException, InterruptedException {
        System.setProperty(OS_NAME, WIN_x64.getName());

        Runtime runtime = mock(Runtime.class);
        Runner runner = new Runner(runtime, mock(NodeDirectoryFinder.class));
        File targetDirectory = mock(File.class);
        when(targetDirectory.list()).thenReturn(new String[]{"mb.cmd", "mountebank", "node.exe"});
        when(targetDirectory.getPath()).thenReturn("target");

        runner.runMountebank(targetDirectory);

        verify(runtime, times(1)).exec(eq("node.exe mountebank/bin/mb"), any(String[].class), any(File.class));
    }
}