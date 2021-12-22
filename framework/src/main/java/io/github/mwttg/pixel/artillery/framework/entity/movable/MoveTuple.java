package io.github.mwttg.pixel.artillery.framework.entity.movable;

import org.joml.Matrix4f;

/**
 * This data class is used for the Movement. It links the model matrix and the animation name.
 * With this e.g. it's possible to use for the left movement a different animation than for the
 * right movement.
 */
public record MoveTuple(String name, Matrix4f model) {
}
