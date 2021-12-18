package io.github.mwttg.pixel.artillery.framework.core.render;

import org.joml.Matrix4f;

/**
 * The Matrix Stack containing model-, view- and projection- matrix.
 */
public record MatrixStack(Matrix4f modelMatrix, Matrix4f viewMatrix, Matrix4f projectionMatrix) {
}
