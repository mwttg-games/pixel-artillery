package io.github.mwttg.pixel.artillery.tools.level;

import io.github.mwttg.pixel.artillery.tools.common.OutputFile;

/**
 * Record for the data for OpenGL (is written to a json file).
 */
public record LevelData(Float[] tilesGeometry, Float[] textureCoordinates) implements OutputFile {
}
