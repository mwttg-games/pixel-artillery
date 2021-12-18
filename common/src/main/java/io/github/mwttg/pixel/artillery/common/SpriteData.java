package io.github.mwttg.pixel.artillery.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Tuple class for vertices and texture coordinates.
 */
public record SpriteData(float[] vertices, float[] textureCoordinates) {

  private static final Logger LOG = LoggerFactory.getLogger(SpriteData.class);

  /**
   * Helper function, because someone forgot to implement a float stream in Java (for double
   * it exists).
   *
   * @param items the collection of {@link Float} numbers
   * @return an array of float (primitives) numbers
   */
  public static float[] toFloatArray(final Collection<Float> items) {
    final var result = new float[items.size()];
    var index = 0;
    for (final Float number : items) {
      result[index++] = number;
    }

    return result;
  }

  /**
   * Writes the sprite data to a json file.
   *
   * @param filename path and filename
   */
  public void writeToFile(final String filename) {
    final var outputFile = new File(filename);
    final var objectMapper = new ObjectMapper();
    try {
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, this);
    } catch (final IOException exception) {
      LOG.error("Can't write Sprite data to .json file '{}'. Exception was: ", filename, exception);
    }
  }
}
