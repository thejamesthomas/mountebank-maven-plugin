package org.mbtest.mountebank.utils;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class URLParserTest {
    @Test
    public void shouldReturnAURLWithTheCorrectProtocol() throws MalformedURLException {
        String urlPath = "https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz";

        URL url = URLParser.parse(urlPath);

        assertThat(url.getProtocol()).isEqualTo("https");
    }

    @Test
    public void shouldReturnAURLWithTheCorrectAuthority() throws MalformedURLException {
        String urlPath = "https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz";

        URL url = URLParser.parse(urlPath);

        assertThat(url.getAuthority()).isEqualTo("s3.amazonaws.com");
    }

    @Test
    public void shouldReturnAURLWithTheCorrectPath() throws MalformedURLException {
        String urlPath = "https://s3.amazonaws.com/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz";

        URL url = URLParser.parse(urlPath);

        assertThat(url.getPath()).isEqualTo("/mountebank/v1.2/mountebank-v1.2.56-darwin-x64.tar.gz");
    }
}
