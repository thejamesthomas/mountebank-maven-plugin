package org.mbtest.mountebank;

import org.junit.Test;
import org.mbtest.mountebank.utils.FileUtilsWrapper;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class DownloaderTest {
    @Test
    public void shouldUseAFileUtilsWrapperWhenDownloading() throws IOException {
        File file = mock(File.class);
        FileUtilsWrapper fileUtilsWrapper = mock(FileUtilsWrapper.class);
        Downloader mountebankDownloader = new Downloader(fileUtilsWrapper);

        mountebankDownloader.download("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz", file);

        verify(fileUtilsWrapper, times(1)).download("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz", file);
    }
}