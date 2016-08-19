# mountebank-maven-plugin
A Maven Plugin to help integrate Mountebank into the Maven lifecycle.

Usage:

      <properties>
          <mountebank.home>/home/timw/apps/mountebank-v1.4.2-linux-x64</mountebank.home>
	</properties>
	
    <build>
		<plugins>
			<plugin>
				<groupId>org.mbtest.mountebank</groupId>
				<artifactId>mountebank-maven-plugin</artifactId>
				<version>1.0-SNAPSHOT</version>
				<configuration>
					<mountebankFolder>${mountebank.home}</mountebankFolder>
				</configuration>
				<executions>
					<execution>
						<id>start Mountebank</id>
						<phase>pre-integration-test</phase>
						<goals>
							<goal>start</goal>
						</goals>
					</execution>
					<execution>
						<id>stop Mountebank</id>
						<phase>post-integration-test</phase>
						<goals>
							<goal>stop</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
