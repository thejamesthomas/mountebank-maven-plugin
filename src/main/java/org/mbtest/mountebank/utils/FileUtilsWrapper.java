package org.mbtest.mountebank.utils;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtilsWrapper {
    public void download(String source, File destination) throws IOException {
        FileUtils.copyURLToFile(URLParser.parse(source), destination);
    }
}
