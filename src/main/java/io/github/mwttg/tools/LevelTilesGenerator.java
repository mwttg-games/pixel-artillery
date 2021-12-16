package io.github.mwttg.tools;

import io.github.mwttg.tools.levelgenerator.InputDefinition;
import io.github.mwttg.tools.levelgenerator.LevelGenerator;
import java.io.File;
import java.io.IOException;

/**
 * Generates level data.
 */
public class LevelTilesGenerator {

  /**
   * This is the main function ;) .
   */
  public static void main(String[] args) throws IOException {
    // final var currentDirectory = System.getProperty("user.dir");
    // final var directory = currentDirectory + "/integration-test-files/level";
    final var directory =
        "/Users/mwittig/development/private/tiles-operator-test/src/main/resources/files/test2/";
    final var definitionFilename = "input-definition.json";
    final var inputDefinition = InputDefinition.createFrom(directory + definitionFilename);
    final var blockFile = new File(directory + inputDefinition.levelBlocks().filename());

    final var generator = new LevelGenerator(inputDefinition, blockFile);
    final var levelData = generator.create();

    // final var destination = currentDirectory + "/output.json";
    final var destination = directory + "/level.json";
    levelData.writeToFile(destination);
  }
}
