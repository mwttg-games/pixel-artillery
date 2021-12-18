package io.github.mwttg.pixel.artillery.tools.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Indicates the used class/record can be written as a .json file.
 */
public interface OutputFile {

  /**
   * Writes the data to a json file.
   *
   * @param destination path and filename
   * @throws IOException if IO error happens
   */
  default void writeToFile(final String destination) throws IOException {
    final var outputFile = new File(destination);
    final var objectMapper = new ObjectMapper();
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, this);
  }
}
