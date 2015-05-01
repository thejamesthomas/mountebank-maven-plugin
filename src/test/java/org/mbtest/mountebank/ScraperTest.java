package org.mbtest.mountebank;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mbtest.mountebank.utils.OS.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScraperTest {

    private Document document;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        document = Jsoup.parse(new File("src/test/resources/mbtest.html"), "UTF-8");
        connection = mock(Connection.class);
        when(connection.get()).thenReturn(document);
    }

    @Ignore
    @Test
    public void shouldScrape() throws IOException {
        System.setProperty(OS_NAME, OSX.getName());
        System.setProperty(OS_ARCH, OSX.getArch());
        Scraper scraper = new Scraper();

        String url = scraper.getBinaryUrlFromDownloadPage(Jsoup.connect("http://www.mbtest.org/docs/install"));

        assertThat(url).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.103-darwin-x64.tar.gz");
    }

    @Test
    public void shouldGetUrlForOSXBinaryWhenOnOSX() throws IOException {
        System.setProperty(OS_NAME, OSX.getName());
        System.setProperty(OS_ARCH, OSX.getArch());

        Scraper pageScraper = new Scraper();
        String binaryUrl = pageScraper.getBinaryUrlFromDownloadPage(connection);

        assertThat(binaryUrl).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz");
    }

    @Test
    public void shouldGetUrlForLinux32BitBinaryWhenOnLinux32Bit() throws IOException {
        System.setProperty(OS_NAME, LINUX_x86.getName());
        System.setProperty(OS_ARCH, LINUX_x86.getArch());

        Scraper pageScraper = new Scraper();
        String binaryUrl = pageScraper.getBinaryUrlFromDownloadPage(connection);

        assertThat(binaryUrl).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-linux-x86.tar.gz");
    }

    @Test
    public void shouldGetUrlForLinux64BitBinaryWhenOnLinux64Bit() throws IOException {
        System.setProperty(OS_NAME, LINUX_x64.getName());
        System.setProperty(OS_ARCH, LINUX_x64.getArch());

        Scraper pageScraper = new Scraper();
        String binaryUrl = pageScraper.getBinaryUrlFromDownloadPage(connection);

        assertThat(binaryUrl).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-linux-x64.tar.gz");
    }

    @Test
    public void shouldGetUrlForWindows32BitBinaryWhenOnWindows32Bit() throws IOException {
        System.setProperty(OS_NAME, WIN_x86.getName());
        System.setProperty(OS_ARCH, WIN_x86.getArch());

        Scraper pageScraper = new Scraper();
        String binaryUrl = pageScraper.getBinaryUrlFromDownloadPage(connection);

        assertThat(binaryUrl).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-win-x86.zip");
    }

    @Test
    public void shouldGetUrlForWindows64BitBinaryWhenOnWindows64Bit() throws IOException {
        System.setProperty(OS_NAME, WIN_x64.getName());
        System.setProperty(OS_ARCH, WIN_x64.getArch());

        Scraper pageScraper = new Scraper();
        String binaryUrl = pageScraper.getBinaryUrlFromDownloadPage(connection);

        assertThat(binaryUrl).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-win-x64.zip");
    }

    @Test
    public void shouldGetUrlForWindows32BitBinaryWhenOnWindowsXP() throws IOException {
        System.setProperty(OS_NAME, "Windows XP");
        System.setProperty(OS_ARCH, WIN_x86.getArch());

        Scraper pageScraper = new Scraper();
        String binaryUrl = pageScraper.getBinaryUrlFromDownloadPage(connection);

        assertThat(binaryUrl).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-win-x86.zip");
    }

    @Test
    public void shouldGetUrlForWindows32BitBinaryWhenOnA32BitOperatingSystemWithWindowsInTheName() throws IOException {
        System.setProperty(OS_NAME, "WindowsThisIsNotARealVersion");
        System.setProperty(OS_ARCH, WIN_x86.getArch());

        Scraper pageScraper = new Scraper();
        String binaryUrl = pageScraper.getBinaryUrlFromDownloadPage(connection);

        assertThat(binaryUrl).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-win-x86.zip");
    }

    @Test
    public void shouldGetUrlForWindows64BitBinaryWhenOnA64BitOperatingSystemWithWindowsInTheName() throws IOException {
        System.setProperty(OS_NAME, "WindowsThisIsAlsoNotARealVersion");
        System.setProperty(OS_ARCH, WIN_x64.getArch());

        Scraper pageScraper = new Scraper();
        String binaryUrl = pageScraper.getBinaryUrlFromDownloadPage(connection);

        assertThat(binaryUrl).isEqualTo("https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-win-x64.zip");
    }
}