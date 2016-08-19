package org.mbtest.mountebank.system;

import org.junit.After;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mbtest.mountebank.utils.OS.OS_NAME;

public class OSDetectorTest {

    private static final String CURRENT_OS = System.getProperty(OS_NAME);
    private static final String OS = "TEST_OS";
    private OSDetector testsubject = new OSDetector();

    @Test
    public void testGetCurrentOS() throws Exception {
        System.setProperty(OS_NAME, OS);
        assertThat(this.testsubject.getCurrentOS()).isEqualTo(OS);
    }

    @After
    public void after() {
        System.setProperty(OS_NAME, CURRENT_OS);
    }

}