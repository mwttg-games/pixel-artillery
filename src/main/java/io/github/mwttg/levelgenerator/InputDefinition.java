package io.github.mwttg.levelgenerator;

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
}
