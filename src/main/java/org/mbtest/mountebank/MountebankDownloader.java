package org.mbtest.mountebank;

import java.io.IOException;

public class MountebankDownloader {
    private Downloader downloader;
    private DownloadPageScraper downloadPageScraper;

    public MountebankDownloader(Downloader downloader, DownloadPageScraper downloadPageScraper) {
        this.downloader = downloader;
        this.downloadPageScraper = downloadPageScraper;
    }

    public void downloadLatest() throws IOException {
        String url = downloadPageScraper.getLatestBinaryUrl();

        downloader.download(url);
    }
}
