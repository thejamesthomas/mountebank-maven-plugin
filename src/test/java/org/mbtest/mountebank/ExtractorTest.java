package org.mbtest.mountebank;

import org.junit.Ignore;
import org.junit.Test;
import org.mbtest.mountebank.utils.TarGzExtractor;
import org.mbtest.mountebank.utils.ZipExtractor;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ExtractorTest {
    @Ignore
    @Test
    public void shouldExtract() throws IOException {
        Extractor extractor = new Extractor(new TarGzExtractor(), new ZipExtractor());
        File targetFile = new File("src/test/resources/mountebank-v1.2.56-darwin-x64.tar.gz");
        File targetDirectory = new File("target");
        extractor.extract(targetFile, targetDirectory);
    }

    @Test
    public void shouldExtractWithATarGzExtractorWhenGivenATarGzFile() throws IOException {
        TarGzExtractor tarGzExtractor = mock(TarGzExtractor.class);
        File targetDirectory = mock(File.class);
        File targetFile = mock(File.class);
        when(targetFile.getPath()).thenReturn("testing.tar.gz");

        Extractor extractor = new Extractor(tarGzExtractor, mock(ZipExtractor.class));
        extractor.extract(targetFile, targetDirectory);

        verify(tarGzExtractor, times(1)).extract(targetFile, targetDirectory);
    }

    @Test
    public void shouldExtractWithAZipExtractorWhenGivenAZipFile() throws IOException {
        ZipExtractor zipExtractor = mock(ZipExtractor.class);
        File targetDirectory = mock(File.class);
        File targetFile = mock(File.class);
        when(targetFile.getPath()).thenReturn("testing.zip");

        Extractor extractor = new Extractor(mock(TarGzExtractor.class), zipExtractor);
        extractor.extract(targetFile, targetDirectory);

        verify(zipExtractor, times(1)).extract(targetFile, targetDirectory);
    }
}