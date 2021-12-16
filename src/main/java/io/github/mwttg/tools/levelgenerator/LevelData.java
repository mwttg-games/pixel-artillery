package io.github.mwttg.tools.levelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Tuple class for the data for OpenGL (is written to a json file).
 */
public record LevelData(Float[] tilesGeometry, Float[] textureCoordinates) {

  /**
   * Writes the data to a json file.
   *
   * @param destination path and filename
   * @throws IOException if IO error happens
   */
  public void writeToFile(final String destination) throws IOException {
    final var outputFile = new File(destination);
    final var objectMapper = new ObjectMapper();
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, this);
  }
}
