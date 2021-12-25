package io.github.mwttg.pixel.artillery.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mwttg.pixel.artillery.common.ReadFile;
import io.github.mwttg.pixel.artillery.tools.grid.Generator;
import io.github.mwttg.pixel.artillery.tools.grid.InputDefinition;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates kind a 2D array of the level. Can be used for checking where is walkable path and
 * where are solid blocks.
 */
public class GridGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(GridGenerator.class);

  /**
   * This is the main function ;) .
   */
  public static void main(String[] args) throws IOException {
    final var directory =
        "/Users/mwittig/development/private/pixel-artillery/examples/src/main/resources/files/"
            + "example05/";
    final var definitionFilename = "grid-definition.json";
    final var inputDefinition =
        ReadFile.jsonFrom(directory + definitionFilename, InputDefinition.class);
    final var blockFile = new File(directory + inputDefinition.levelBlocks().filename());

    final var generator = new Generator(inputDefinition, blockFile);
    final var result = generator.generate();

    final var destination = directory + "/output.json";
    writeToFile(result, destination);
  }

  private static void writeToFile(final List<List<Integer>> result, final String filename) {
    final var outputFile = new File(filename);
    final var objectMapper = new ObjectMapper();
    try {
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, result);
    } catch (final IOException exception) {
      LOG.error("Can't write data to .json file '{}'. Exception was: ", filename, exception);
    }
  }
}
