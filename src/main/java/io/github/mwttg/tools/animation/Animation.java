package io.github.mwttg.tools.animation;

import io.github.mwttg.tools.common.OutputFile;

/**
 * Record for the data for OpenGL (is written to a json file).
 */
public record Animation(Float[] tilesGeometry, Float[] textureCoordinates) implements OutputFile {
}
