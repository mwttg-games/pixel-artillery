package io.github.mwttg.pixel.artillery.tools.level;

import io.github.mwttg.pixel.artillery.tools.LevelTilesGenerator;
import io.github.mwttg.pixel.artillery.tools.common.TextureAtlas;
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
}

