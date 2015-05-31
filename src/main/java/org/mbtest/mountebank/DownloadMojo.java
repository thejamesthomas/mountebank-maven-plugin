package org.mbtest.mountebank;

import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.jsoup.Jsoup;
import org.mbtest.mountebank.utils.FileUtilsWrapper;

import java.io.File;
import java.io.IOException;

@Mojo(name = "download")
public class DownloadMojo extends AbstractMojo {
    @Setter
    @Parameter(property = "mountebankArchiveLocation", defaultValue = "/tmp/mountebank.tar.gz")
    private File _mountebankArchiveLocation;

    private final Downloader downloader;
    private final Scraper scraper;

    public DownloadMojo() {
        scraper = new Scraper(Jsoup.connect(Scraper.DEFAULT_DOWNLOAD_PAGE_URL));
        downloader = new Downloader(new FileUtilsWrapper());
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            getLog().info("Getting Mountebank download url");
            String downloadUrl = scraper.getBinaryUrlFromDownloadPage();

            getLog().info("Downloading Mountebank binary archive from '" + downloadUrl + "'");
            downloader.download(downloadUrl, _mountebankArchiveLocation);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }
}
