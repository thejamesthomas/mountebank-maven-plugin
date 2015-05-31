package org.mbtest.mountebank;

import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.mbtest.mountebank.utils.NodeDirectoryFinder;
import org.mbtest.mountebank.utils.NodeDirectoryVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

@Mojo(name = "run")
public class RunMojo extends AbstractMojo {
    @Setter
    @Parameter(property = "mountebankFolder", defaultValue = "/tmp")
    private File _mountebankFolder;

    private final Runner runner;

    public RunMojo() {
        NodeDirectoryVisitor visitor = new NodeDirectoryVisitor();
        NodeDirectoryFinder finder = new NodeDirectoryFinder(visitor, FileSystems.getDefault());

        runner = new Runner(Runtime.getRuntime(), finder);
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            getLog().info("Starting Mountebank");
            runner.runMountebank(_mountebankFolder);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }
}
