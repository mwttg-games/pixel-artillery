package io.github.mwttg.pixel.artillery.tools.animation;

import io.github.mwttg.pixel.artillery.tools.common.OutputFile;

/**
 * Record for the data for OpenGL (is written to a json file).
 */
public record Animation(Float[] tilesGeometry, Float[] textureCoordinates) implements OutputFile {
}
