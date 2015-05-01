package org.mbtest.mountebank.utils;

import lombok.Getter;

public enum OS {
    OSX("osx", "Mac OS X", "x86_64"),
    LINUX_x86("linux x86", "Linux", "x86"),
    LINUX_x64("linux x64", "Linux", "x86_64"),
    WIN_x86("win x86", "Windows", "x86"),
    WIN_x64("win x64", "Windows", "x86_64");

    public final static String OS_NAME = "os.name";
    public final static String OS_ARCH = "os.arch";

    @Getter
    private String key;
    @Getter
    private String name;
    @Getter
    private String arch;

    OS(String key, String name, String arch) {
        this.key = key;
        this.name = name;
        this.arch = arch;
    }

    public boolean isOS(String compareTo) {
        return this.getName().contains(compareTo) || compareTo.contains(this.getName());
    }
}