package org.mbtest.mountebank.system;

import org.apache.maven.plugin.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;
import org.mbtest.mountebank.utils.OS;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandFactoryTest {

    @Mock
    private NodeDirectoryFinder nodeDirectoryFinder;

    @Mock
    private OSDetector osDetector;

    @Mock
    private Log logger;

    @Mock
    private File mountebankHome;

    @InjectMocks
    private CommandFactory testSubject;

    @Test
    public void get_command_linux_x64() throws Exception {
        when(this.mountebankHome.getAbsolutePath()).thenReturn("/tmp");
        when(this.osDetector.getCurrentOS()).thenReturn(OS.LINUX_x64.getName());
        final String actual = this.testSubject.getCommand(this.mountebankHome);

        assertThat(actual, equalTo("/tmp/mb"));
    }

    @Test
    public void get_command_linux_x86() throws Exception {
        when(this.mountebankHome.getAbsolutePath()).thenReturn("/tmp");
        when(this.osDetector.getCurrentOS()).thenReturn(OS.LINUX_x86.getName());
        final String actual = this.testSubject.getCommand(this.mountebankHome);

        assertThat(actual, equalTo("/tmp/mb"));
    }

    @Test
    public void get_command_windows_x64() throws Exception {
        when(this.mountebankHome.getAbsolutePath()).thenReturn("C:\\temp");
        when(this.osDetector.getCurrentOS()).thenReturn(OS.WIN_x64.getName());
        final String actual = this.testSubject.getCommand(this.mountebankHome);

        assertThat(actual, equalTo("C:\\temp/mb.cmd"));
    }

    @Test
    public void get_command_windows_x86() throws Exception {
        when(this.mountebankHome.getAbsolutePath()).thenReturn("C:\\temp");
        when(this.osDetector.getCurrentOS()).thenReturn(OS.WIN_x86.getName());
        final String actual = this.testSubject.getCommand(this.mountebankHome);

        assertThat(actual, equalTo("C:\\temp/mb.cmd"));
    }

    @Test
    public void get_command_windows_mac() throws Exception {
        final File nodeDirectory = mock(File.class);

        when(this.mountebankHome.getPath()).thenReturn("/tmp");
        when(this.nodeDirectoryFinder.findNodeDirectory("/tmp")).thenReturn(nodeDirectory);
        when(nodeDirectory.getName()).thenReturn("tmp/mountebank");
        when(this.osDetector.getCurrentOS()).thenReturn(OS.OSX.getName());
        final String actual = this.testSubject.getCommand(this.mountebankHome);

        assertThat(actual, equalTo("./tmp/mountebank/bin/node /mountebank/bin/mb"));
    }
}