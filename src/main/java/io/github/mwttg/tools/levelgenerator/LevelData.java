package io.github.mwttg.tools.levelgenerator;

import io.github.mwttg.tools.common.OutputFile;

/**
 * Record for the data for OpenGL (is written to a json file).
 */
public record LevelData(Float[] tilesGeometry, Float[] textureCoordinates) implements OutputFile {
}
