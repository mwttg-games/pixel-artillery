package io.github.mwttg.levelgenerator;

/**
 * This is one part of the input of the LevelGenerator (output .json file).
 *
 * @param tileSize describes the size of a tile eg. 32 (for 32x32)
 * @param filename the filename of the TextureAtlas which contains the tiles (in resource folder?)
 */
public record TextureAtlas(int tileSize, String filename) {
}
