package io.github.mwttg.pixel.artillery.tools.animation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mwttg.pixel.artillery.tools.common.TextureAtlas;
import java.io.File;
import java.io.IOException;

/**
 * The input parameters for creating an animation.
 */
public record InputDefinition(TextureAtlas textureAtlas, int animationSteps, float planeSize) {

  /**
   * Creates the {@link InputDefinition} from a json file.
   *
   * @param filename the filename
   * @return {@link InputDefinition}
   * @throws IOException when I/O goes wrong
   */
  public static InputDefinition createFrom(final String filename) throws IOException {
    final var file = new File(filename);
    final var objectMapper = new ObjectMapper();

    return objectMapper.readValue(file, InputDefinition.class);
  }
}
