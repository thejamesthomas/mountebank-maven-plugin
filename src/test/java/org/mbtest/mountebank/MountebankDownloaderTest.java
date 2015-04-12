package org.mbtest.mountebank;

import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MountebankDownloaderTest {
    @Test
    public void shouldUseADownloaderWhenDownloadingTheLatest() throws IOException {
        Downloader downloader = mock(Downloader.class);
        DownloadPageScraper downloadPageScraper = mock(DownloadPageScraper.class);
        when(downloadPageScraper.getLatestBinaryUrl()).thenReturn("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz");

        MountebankDownloader mountebankDownloader = new MountebankDownloader(downloader, downloadPageScraper);
        mountebankDownloader.downloadLatest();

        verify(downloader, times(1)).download("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz");
    }
}