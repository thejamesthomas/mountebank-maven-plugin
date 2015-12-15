package org.mbtest.mountebank.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;

public class NodeDirectoryFinder {

    private final NodeDirectoryVisitor directoryVisitor;
    private final FileSystem fileSystem;

    public NodeDirectoryFinder(NodeDirectoryVisitor directoryVisitor, FileSystem fileSystem) {
        this.directoryVisitor = directoryVisitor;
        this.fileSystem = fileSystem;
    }

    public File findNodeDirectory(String targetDirectoryPath) throws IOException {
        Files.walkFileTree(fileSystem.getPath(targetDirectoryPath), directoryVisitor);
        return directoryVisitor.getResult();
    }
}