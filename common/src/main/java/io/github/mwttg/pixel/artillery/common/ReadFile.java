package io.github.mwttg.pixel.artillery.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Functions for reading files (json or text).
 */
public final class ReadFile {

  private static final Logger LOG = LoggerFactory.getLogger(ReadFile.class);

  private ReadFile() {
  }

  /**
   * Reads a .json file from the resource folder and transforms the json to a POJO.
   *
   * @param filename the path and filename inside the resource folder
   * @param typeRef  the type of the pojo
   * @param <T>      the type of the resulting POJO
   * @return the json as POJO
   */
  public static <T> T jsonFromResources(final String filename, final TypeReference<T> typeRef) {
    final var file = new File(
        Objects.requireNonNull(ReadFile.class.getClassLoader().getResource(filename),
            "Something went wrong during opening file '%s'.".formatted(filename)).getFile());
    final var objectMapper = new ObjectMapper();
    objectMapper.registerModule(new Jdk8Module());

    try {
      return objectMapper.readValue(file, typeRef);
    } catch (final IOException exception) {
      LOG.error("Could not read/transform .json file '{}' in resource folder. Exception was: ",
          filename, exception);
    }

    throw new RuntimeException(
        "Loading .json file '%s' from resource folder and transforming it to a POJO '%s' failed"
            .formatted(filename, typeRef.getType().getTypeName()));
  }

  /**
   * Reads a .json file and transforms it to a POJO.
   *
   * @param filename the filename including/with the path
   * @param clazz    the Java class of the resulting POJO
   * @param <T>      the type of the resulting POJO
   * @return the json as POJO
   */
  public static <T> T jsonFrom(final String filename, final Class<T> clazz) {
    final var file = new File(filename);
    final var objectMapper = new ObjectMapper();
    objectMapper.registerModule(new Jdk8Module());

    try {
      return objectMapper.readValue(file, clazz);
    } catch (final IOException exception) {
      LOG.error("Could not read/transform .json file '{}'. Exception was: ", filename, exception);
    }

    throw new RuntimeException(
        "Loading .json file '%s' and transforming it to a POJO '%s' failed".formatted(
            filename, clazz.getCanonicalName()));
  }

  /**
   * Reads a text file from the resource folder.
   *
   * <p>Note</p>
   * Normally when reading from resource folder, a starting '/' for the filename is not valid (see
   * {@link ReadFile#jsonFromResources(String, TypeReference)}). Unfortunately we are using
   * {@link Paths} in this method and now the starting '/' is required!
   *
   * @param filename the path and filename inside the resource folder
   * @return a list of all lines of the text file
   */
  public static List<String> fromResources(final String filename) {
    final var file = Objects.requireNonNull(ReadFile.class.getResource(filename),
        "Something went wrong during opening file '%s'.".formatted(filename)).getPath();
    final var path = Paths.get(file);

    try {
      return Files.readAllLines(path, StandardCharsets.UTF_8);
    } catch (final IOException exception) {
      LOG.error("Could not read file '{}'. Exception was: ", filename, exception);
    }

    throw new RuntimeException(
        "Loading file '%s' from resources folder failed!".formatted(filename));
  }
}
