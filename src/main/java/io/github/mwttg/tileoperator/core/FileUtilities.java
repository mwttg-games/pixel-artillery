package io.github.mwttg.tileoperator.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read a text file.
 */
public final class FileUtilities {

  private static final Logger LOG = LoggerFactory.getLogger(FileUtilities.class);
  private static final String FILE_NOT_FOUND =
      "No InputStream for file: '%s' available (file not found).";
  private static final String ERROR_MESSAGE =
      "Something went wrong during trying to read the file: '%s'.";

  /**
   * Reads a text file from the resource folder. Used for shader files.
   *
   * @param filename path to the resource file
   * @return the file's content
   */
  public static List<String> readFromResources(final String filename) {
    LOG.debug("Read file: '{}'.", filename);

    final var classLoader = ClassLoader.getSystemClassLoader();
    try (final InputStream inputStream = classLoader.getResourceAsStream(filename)) {
      if (inputStream == null) {
        final var message = FILE_NOT_FOUND.formatted(filename);
        LOG.error(message);
        throw new IllegalArgumentException(message);
      }

      try (final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
           final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
        return bufferedReader
            .lines()
            .collect(Collectors.toList());
      }
    } catch (final IOException e) {
      final var message = ERROR_MESSAGE.formatted(filename);
      LOG.error(message, e);
      throw new RuntimeException(message);
    }
  }
}
