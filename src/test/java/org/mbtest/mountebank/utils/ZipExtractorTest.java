package org.mbtest.mountebank.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ZipExtractorTest {
    @Ignore
    @Test
    public void shouldExtractAZip() throws IOException {
        File inputFile = new File("src/test/resources/mountebank-v1.2.56-darwin-x64.zip");
        File targetDirectory = new File("target");

        ZipExtractor.extract(inputFile, targetDirectory);
    }
}