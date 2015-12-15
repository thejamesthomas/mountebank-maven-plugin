package org.mbtest.mountebank;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Ignore;
import org.junit.Test;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;
import org.mbtest.mountebank.utils.NodeDirectoryVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import static org.mbtest.mountebank.utils.OS.*;
import static org.mockito.Mockito.*;

@Ignore
public class RunnerTest {

    @Test
    public void shouldRunMountebank() throws IOException, InterruptedException, MojoExecutionException {
        Runner runner = new Runner(new NodeDirectoryFinder(new NodeDirectoryVisitor(), FileSystems.getDefault()), null);
        runner.startMountebank(new File("target/mountebank-v1.2.56-darwin-x64"));
    }

    @Test
    public void shouldDestroyTheProcessWhenStoppingMountebank() throws IOException, InterruptedException, MojoExecutionException {
        Process process = mock(Process.class);

        File targetDirectory = mock(File.class);
        when(targetDirectory.list()).thenReturn(new String[]{});

        NodeDirectoryFinder nodeDirectoryFinder = mock(NodeDirectoryFinder.class);
        File nodeDirectory = mock(File.class);
        when(nodeDirectoryFinder.findNodeDirectory(targetDirectory.getPath())).thenReturn(nodeDirectory);

        Runner runner = new Runner(nodeDirectoryFinder, null);

        runner.startMountebank(targetDirectory);

        verify(process, times(1)).destroy();
    }

    @Test
    public void shouldRunTheCorrectCommandWhenRunningAnOSXArchiveOnOSX() throws IOException, InterruptedException, MojoExecutionException {
        System.setProperty(OS_NAME, OSX.getName());

        File targetDirectory = mock(File.class);
        when(targetDirectory.list()).thenReturn(new String[]{"mb", "mountebank", "node-v0.10.37-darwin-x64"});
        when(targetDirectory.getPath()).thenReturn("target");

        NodeDirectoryFinder nodeDirectoryFinder = mock(NodeDirectoryFinder.class);
        File nodeDirectory = mock(File.class);
        when(nodeDirectoryFinder.findNodeDirectory(targetDirectory.getPath())).thenReturn(nodeDirectory);

        Runner runner = new Runner(nodeDirectoryFinder, null);
        runner.startMountebank(targetDirectory);
    }

    @Test
    public void shouldRunTheCorrectCommandWhenRunningAWindowsArchiveOnWindows() throws IOException, InterruptedException, MojoExecutionException {
        System.setProperty(OS_NAME, WIN_x64.getName());

        Runner runner = new Runner(mock(NodeDirectoryFinder.class), null);
        File targetDirectory = mock(File.class);
        when(targetDirectory.list()).thenReturn(new String[]{"mb.cmd", "mountebank", "node.exe"});
        when(targetDirectory.getPath()).thenReturn("target");

        runner.startMountebank(targetDirectory);
    }
}