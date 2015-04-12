package org.mbtest.mountebank;

import lombok.Getter;

public enum OS {
    OSX("osx", "Mac OS X");

    @Getter
    private String key;
    @Getter
    private String value;

    OS(String key, String value) {
        this.key = key;
        this.value = value;
    }
}