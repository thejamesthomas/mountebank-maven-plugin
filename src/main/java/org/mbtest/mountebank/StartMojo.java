package org.mbtest.mountebank;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.IOException;

@Mojo(name = "start", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class StartMojo extends AbstractExecutionMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            this.getLog().info("Starting Mountebank...");
            this.getRunner().startMountebank();
        } catch (IOException e) {
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }
}
