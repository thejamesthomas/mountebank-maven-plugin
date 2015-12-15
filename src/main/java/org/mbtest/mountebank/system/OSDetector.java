package org.mbtest.mountebank.system;

import static org.mbtest.mountebank.utils.OS.OS_NAME;

public class OSDetector {

    public String getCurrentOS() {
        return System.getProperty(OS_NAME);
    }
}
