package org.mbtest.mountebank.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessBuilderAdapterTest {

    private static final String COMMAND = "command";
    private static final String ARG = "arg";

    @Mock
    private ProcessBuilderWrapper processBuilderWrapper;

    @Mock
    private CommandFactory commandFactory;

    @Mock
    private Process process;

    @Mock
    private File mountebankHome;

    @InjectMocks
    private ProcessBuilderAdapter testSubject;

    @Test
    public void testStart() throws Exception {
        when(this.commandFactory.getCommand(this.mountebankHome)).thenReturn(COMMAND);
        when(this.processBuilderWrapper.start(COMMAND, ARG)).thenReturn(this.process);

        final Process result = this.testSubject.start(this.mountebankHome, ARG);
        assertThat(result).isSameAs(this.process);
    }
}