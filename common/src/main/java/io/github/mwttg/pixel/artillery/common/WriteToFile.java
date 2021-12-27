package io.github.mwttg.pixel.artillery.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An interface, that indicates the model class can be written to a .json file.
 */
public interface WriteToFile {

  Logger LOG = LoggerFactory.getLogger(WriteToFile.class);

  /**
   * Writes the model class e.g. {@link SpriteData} or {@link SpriteAnimationData} data to a json
   * file.
   *
   * @param filename path and filename
   */
  default void writeToFile(final String filename) {
    final var outputFile = new File(filename);
    final var objectMapper = new ObjectMapper();
    objectMapper.registerModule(new Jdk8Module());

    try {
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, this);
    } catch (final IOException exception) {
      LOG.error("Can't write data to .json file '{}'. Exception was: ", filename, exception);
    }
  }
}
