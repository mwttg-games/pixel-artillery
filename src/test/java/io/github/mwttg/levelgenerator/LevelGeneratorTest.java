package io.github.mwttg.levelgenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import org.testng.annotations.Test;

public class LevelGeneratorTest {

  /**
   * Generates the output file (for testing purpose at the moment
   * because no main ;)
   */

  @Test
  public void testStuff() throws IOException, URISyntaxException {
    final var currentDirectory = System.getProperty("user.dir");

    final var subject = new LevelGenerator();
    subject.create(currentDirectory + "/integration-test-files/",
        "valid-levelgenerator-definition.json");
  }
}