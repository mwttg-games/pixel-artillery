package io.github.mwttg.levelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * This represents the definition file for the {@link LevelGenerator}.
 *
 * @param textureAtlas     the texture atlas
 * @param levelBlocks      the level blocks
 * @param tileIndexByColor the map for linking a block to a tile (texture)
 */
public record InputDefinition(TextureAtlas textureAtlas, LevelBlocks levelBlocks,
                              Map<String, Integer> tileIndexByColor) {

  static InputDefinition createFrom(final String filename) throws IOException {
    final var definitionFile = new File(filename);
    final var objectMapper = new ObjectMapper();

    return objectMapper.readValue(definitionFile, InputDefinition.class);
  }
}

