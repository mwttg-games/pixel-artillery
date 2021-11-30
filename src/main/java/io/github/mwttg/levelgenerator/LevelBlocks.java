package io.github.mwttg.levelgenerator;

/**
 * This is one part of the input of the LevelGenerator (output .json file).
 *
 * @param blockSize describes the size of a block for example 5 (stands for 5x5 pixels)
 * @param filename  the filename of the level blocks file which contains the blocks
 */
public record LevelBlocks(int blockSize, String filename) {
}
