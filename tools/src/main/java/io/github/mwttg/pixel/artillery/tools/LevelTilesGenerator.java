package io.github.mwttg.pixel.artillery.tools;

import io.github.mwttg.pixel.artillery.common.ReadFile;
import io.github.mwttg.pixel.artillery.tools.level.InputDefinition;
import io.github.mwttg.pixel.artillery.tools.level.LevelGenerator;
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
        "/Users/mwittig/development/private/pixel-artillery/examples/src/main/resources/files/"
            + "example02/";
    final var definitionFilename = "input-definition.json";
    final var inputDefinition =
        ReadFile.jsonFrom(directory + definitionFilename, InputDefinition.class);
    final var blockFile = new File(directory + inputDefinition.levelBlocks().filename());

    final var generator = new LevelGenerator(inputDefinition, blockFile);
    final var levelData = generator.create();

    // final var destination = currentDirectory + "/output.json";
    final var destination = directory + "/output.json";
    levelData.writeToFile(destination);
  }
}
