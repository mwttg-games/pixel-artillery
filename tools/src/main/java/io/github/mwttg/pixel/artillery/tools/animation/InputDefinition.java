package io.github.mwttg.pixel.artillery.tools.animation;

import io.github.mwttg.pixel.artillery.tools.common.TextureAtlas;

/**
 * The input parameters for creating an animation.
 */
public record InputDefinition(TextureAtlas textureAtlas, int animationSteps, float planeSize) {
}
