package org.mbtest.mountebank;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileDownloader {

    private String url;
    private File file;

    public FileDownloader(String url, File file) {
        this.url = url;
        this.file = file;
    }

    public void download() throws IOException {
        FileUtils.copyURLToFile(URLParser.parse(this.url), this.file);
    }
}
