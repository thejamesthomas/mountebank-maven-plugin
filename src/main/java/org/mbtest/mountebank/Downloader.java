package org.mbtest.mountebank;

import lombok.AllArgsConstructor;
import org.mbtest.mountebank.utils.FileUtilsWrapper;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
public class Downloader {
    private FileUtilsWrapper fileUtilsWrapper;

    public void download(String url, File file) throws IOException {
        fileUtilsWrapper.download(url, file);
    }
}
