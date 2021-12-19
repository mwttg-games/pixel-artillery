package io.github.mwttg.pixel.artillery.common;

/**
 * A data class for animations (at the moment only 'textures on planes' can be animated).
 */
public record SpriteAnimationData(SpriteData spriteData, int animationSteps, int delayInMs)
    implements WriteToFile {
}
