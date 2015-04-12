package org.mbtest.mountebank;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

public class Downloader {

    private File file;

    public Downloader(File file) {
        this.file = file;
    }

    public void download(String url) throws IOException {
        FileUtils.copyURLToFile(URLParser.parse(url), this.file);
    }
}
