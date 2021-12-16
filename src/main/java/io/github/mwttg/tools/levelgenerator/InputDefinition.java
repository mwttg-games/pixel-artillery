package io.github.mwttg.tools.levelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mwttg.tools.LevelTilesGenerator;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * This represents the definition file for the {@link LevelTilesGenerator}.
 *
 * @param textureAtlas     the texture atlas
 * @param levelBlocks      the level blocks
 * @param tileIndexByColor the map for linking a block to a tile (texture)
 */
public record InputDefinition(TextureAtlas textureAtlas, LevelBlocks levelBlocks,
                              Map<String, Integer> tileIndexByColor) {

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

