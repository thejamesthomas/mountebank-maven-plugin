package org.mbtest.mountebank;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mbtest.mountebank.utils.OS;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class Scraper {
    public final static String DOWNLOAD_PAGE_URL = "http://www.mbtest.org/docs/install";

    public String getBinaryUrlFromDownloadPage(Connection connect) throws IOException {
        Document document = connect.get();
        Elements anchors = document.select("a[href*=\"s3.amazonaws.com\"]");

        HashMap<String, String> binaryUrls = getUrlsToBinaries(anchors);
        return getUrlForCurrentOperatingSystem(binaryUrls);
    }

    private String getUrlForCurrentOperatingSystem(HashMap<String, String> binaryUrls) {
        String currentOs = System.getProperty(OS.OS_NAME);
        String currentArch = System.getProperty(OS.OS_ARCH);
        String osKey = null;
        for(OS os : OS.values()) {
            if((os.getName().contains(currentOs) || currentOs.contains(os.getName())) && os.getArch().equals(currentArch)) {
                osKey = os.getKey();
                break;
            }
        }
        return binaryUrls.get(osKey);
    }

    private HashMap<String, String> getUrlsToBinaries(Elements anchors) {
        Collection<Element> elements = getElementsWithLinksToBinaries(anchors);
        return getLinksFromElements(elements);
    }

    private HashMap<String, String> getLinksFromElements(Collection<Element> elements) {
        Collection<HashMap.SimpleEntry<String, String>> keysToHrefs = Collections2.transform(newArrayList(elements), new Function<Element, HashMap.SimpleEntry<String, String>>() {
            public HashMap.SimpleEntry<String, String> apply(Element element) {
                return new HashMap.SimpleEntry<String, String>(element.text(), element.attr("href"));
            }
        });
        HashMap<String, String> keysToHrefsMap = newHashMap();
        for (HashMap.SimpleEntry<String, String> keyToHref : keysToHrefs) {
            keysToHrefsMap.put(keyToHref.getKey(), keyToHref.getValue());
        }

        return keysToHrefsMap;
    }

    private Collection<Element> getElementsWithLinksToBinaries(Elements anchors) {
        return Collections2.filter(anchors, new Predicate<Element>() {
            public boolean apply(Element anchor) {
                return hrefDoesNotContain(anchor, ".pkg")
                        && hrefDoesNotContain(anchor, ".rpm")
                        && hrefDoesNotContain(anchor, ".deb")
                        && hrefDoesNotContain(anchor, "npm.tar.gz");
            }
        });
    }

    private boolean hrefDoesNotContain(Element link, String string) {
        return !link.attr("href").contains(string);
    }
}
