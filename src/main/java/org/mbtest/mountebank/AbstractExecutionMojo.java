package org.mbtest.mountebank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.mbtest.mountebank.system.CommandFactory;
import org.mbtest.mountebank.system.OSDetector;
import org.mbtest.mountebank.system.ProcessBuilderAdapter;
import org.mbtest.mountebank.system.ProcessBuilderWrapper;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;
import org.mbtest.mountebank.utils.NodeDirectoryVisitor;

import java.io.File;
import java.nio.file.FileSystems;

public abstract class AbstractExecutionMojo extends AbstractMojo {

    @Setter
    @Getter(AccessLevel.PROTECTED)
    @Parameter(property = "mountebankFolder", defaultValue = "/tmp")
    private File mountebankFolder;

    private final Runner runner;

    public AbstractExecutionMojo() {
        final NodeDirectoryVisitor visitor = new NodeDirectoryVisitor();
        final NodeDirectoryFinder finder = new NodeDirectoryFinder(visitor, FileSystems.getDefault());
        final CommandFactory commandFactory = new CommandFactory(finder, new OSDetector(), this.getLog());
        final ProcessBuilderWrapper processBuilderWrapper = new ProcessBuilderWrapper();
        final ProcessBuilderAdapter processBuilderAdapter = new ProcessBuilderAdapter(processBuilderWrapper, commandFactory);
        runner = new Runner(processBuilderAdapter);
    }

    protected Runner getRunner() {
        return runner;
    }


}
