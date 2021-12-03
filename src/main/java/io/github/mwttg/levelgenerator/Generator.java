package io.github.mwttg.levelgenerator;

import java.io.File;
import java.io.IOException;

/**
 * Generates level data.
 */
public class Generator {

  /**
   * This is the main function ;) .
   */
  public static void main(String[] args) throws IOException {
    final var currentDirectory = System.getProperty("user.dir");
    final var directory = currentDirectory + "/integration-test-files/";
    final var definitionFilename = "valid-levelgenerator-definition.json";
    final var inputDefinition = InputDefinition.createFrom(directory + definitionFilename);
    final var blockFile = new File(directory + inputDefinition.levelBlocks().filename());

    final var generator = new LevelGenerator(inputDefinition, blockFile);
    final var levelData = generator.create();

    final var destination = currentDirectory + "/output.json";
    levelData.writeToFile(destination);
  }
}
