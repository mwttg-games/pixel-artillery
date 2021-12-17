package io.github.mwttg.tilesoperatortest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileReader {

  private FileReader() {
  }

  public static <T> T createFrom(final String filename, final Class<T> clazz) throws IOException {
    final var file = new File(
        Objects.requireNonNull(
            FileReader.class.getClassLoader().getResource(filename)).getFile());
    final var objectMapper = new ObjectMapper();
    return objectMapper.readValue(file, clazz);
  }
}
