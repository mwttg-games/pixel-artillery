package io.github.mwttg.tilesoperatortest.test2.levelloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public record LevelData(float[] tilesGeometry, float[] textureCoordinates) {

  public static LevelData createFrom(final String filename) throws IOException {
    final var file = new File(
        Objects.requireNonNull(
            LevelData.class.getClassLoader().getResource(filename)).getFile());
    final var objectMapper = new ObjectMapper();
    return objectMapper.readValue(file, LevelData.class);
  }
}
