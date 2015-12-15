package org.mbtest.mountebank;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.IOException;

@Mojo(name = "stop")
public class StopMojo extends AbstractExecutionMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            this.getLog().info("Stopping Mountebank...");
            this.getRunner().stopMountebank(this.getMountebankFolder());
        } catch (IOException e) {
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }
}
