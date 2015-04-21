package org.mbtest.mountebank;

import lombok.AllArgsConstructor;
import org.mbtest.mountebank.utils.TarGzExtractor;
import org.mbtest.mountebank.utils.ZipExtractor;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
public class Extractor {
    private TarGzExtractor tarGzExtractor;
    private ZipExtractor zipExtractor;

    public void extract(File targetFile, File targetDirectory) throws IOException {
        if(isTarGzFile(targetFile)) {
            tarGzExtractor.extract(targetFile, targetDirectory);
        }
        if(isAZipFile(targetFile)) {
            zipExtractor.extract(targetFile, targetDirectory);
        }
    }

    private boolean isAZipFile(File targetFile) {
        return targetFile.getPath().endsWith(".zip");
    }

    private boolean isTarGzFile(File targetFile) {
        return targetFile.getPath().endsWith(".tar.gz");
    }
}
