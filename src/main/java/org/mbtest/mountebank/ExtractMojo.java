package org.mbtest.mountebank;

import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.mbtest.mountebank.utils.TarGzExtractor;
import org.mbtest.mountebank.utils.ZipExtractor;

import java.io.File;
import java.io.IOException;

@Mojo(name = "extract")
public class ExtractMojo extends AbstractMojo {
    @Setter
    @Parameter(property = "mountebankFolder", defaultValue = "/tmp")
    private File targetDirectory;
    @Setter
    @Parameter(property = "mountebankArchiveLocation", defaultValue = "/tmp/mountebank.tar.gz")
    private File mountebankArchiveLocation;

    private final Extractor extractor;

    public ExtractMojo() {
        extractor = new Extractor(new TarGzExtractor(), new ZipExtractor());
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            getLog().info("Extracting mountebank archive '" + mountebankArchiveLocation.getAbsolutePath() + "' to directory '" + targetDirectory.getAbsolutePath() + "'");
            extractor.extract(mountebankArchiveLocation, targetDirectory);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }
}
