package org.mbtest.mountebank.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TarGzExtractorTest {
    @Ignore
    @Test
    public void shouldExtractATarGz() throws IOException {
        File inputFile = new File("src/test/resources/mountebank-v1.2.56-darwin-x64.tar.gz");
        File targetDirectory = new File("target");

        TarGzExtractor.extract(inputFile, targetDirectory);
    }
}