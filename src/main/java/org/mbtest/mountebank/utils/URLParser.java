package org.mbtest.mountebank.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser {

    private static final String PROTOCOL = "protocol";
    private static final String HOST = "host";
    private static final String FILE = "file";

    public static URL parse(String url) throws MalformedURLException {
        String protocol = getProtocol(url);
        String host = getHost(url);
        String file = getFile(url);
        return new URL(protocol, host, file);
    }

    private static String getProtocol(String inputUrl) {
        final String regex = String.format("^(?<%s>\\w+).+", PROTOCOL);

        return getMatcher(inputUrl, regex).group(PROTOCOL);
    }

    private static String getHost(String inputUrl) {
        final String regex = String.format("^\\w+://(?<%s>[\\w.]+).+", HOST);

        return getMatcher(inputUrl, regex).group(HOST);
    }

    private static String getFile(String inputUrl) {
        final String regex = String.format("^\\w+://[\\w.]+(?<%s>.+)", FILE);

        return getMatcher(inputUrl, regex).group(FILE);
    }

    private static Matcher getMatcher(String inputUrl, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(inputUrl);
        match.find();
        return match;
    }
}
