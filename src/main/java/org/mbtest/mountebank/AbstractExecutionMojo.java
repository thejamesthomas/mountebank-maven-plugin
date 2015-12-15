package org.mbtest.mountebank;

import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;
import org.mbtest.mountebank.utils.NodeDirectoryVisitor;

import java.io.File;
import java.nio.file.FileSystems;

public abstract class AbstractExecutionMojo extends AbstractMojo {

    @Setter
    @Parameter (property = "mountebankFolder", defaultValue = "/tmp")
    private File mountebankFolder;

    private NodeDirectoryFinder nodeDirectoryFinder;

    private final Runner runner;

    public AbstractExecutionMojo() {
        NodeDirectoryVisitor visitor = new NodeDirectoryVisitor();
        NodeDirectoryFinder finder = new NodeDirectoryFinder(visitor, FileSystems.getDefault());

        runner = new Runner(finder, this.getLog());
    }

    protected File getMountebankFolder() {
        return mountebankFolder;
    }

    protected Runner getRunner() {
        return runner;
    }


}
