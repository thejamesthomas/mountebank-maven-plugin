package org.mbtest.mountebank;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbtest.mountebank.system.MountebankCommandArgs;
import org.mbtest.mountebank.system.ProcessBuilderAdapter;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RunnerTest {

    @Mock
    private ProcessBuilderAdapter processBuilderAdapter;

    @Mock
    private Process process;

    @InjectMocks
    private Runner testSubject;

    @Test
    public void start() throws IOException, MojoExecutionException {
        when(this.processBuilderAdapter.start(MountebankCommandArgs.START)).thenReturn(this.process);

        this.testSubject.startMountebank();
        final Process expected = Runner.getProcess();
        assertThat(expected).isSameAs(this.process);
    }

    @Test
    public void stop() throws IOException, MojoExecutionException {
        when(this.processBuilderAdapter.start(MountebankCommandArgs.STOP)).thenReturn(this.process);

        this.testSubject.stopMountebank();
        final Process expected = Runner.getProcess();
        assertThat(expected).isSameAs(this.process);
    }


}