package io.github.mwttg.tools.levelgenerator;

/**
 * This is one part of the input of the LevelGenerator (output .json file).
 *
 * @param tileSize describes the size of a tile eg. 32 (for 32x32)
 * @param width    the width of the texture atlas
 * @param height   the height of the texture atlas
 */
public record TextureAtlas(int tileSize, int width, int height) {
}
