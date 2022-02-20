package io.github.mwttg.pixel.artillery.examples;

import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import org.joml.Matrix4f;

public class Matrix {

  public static Matrix4f createOrtho2DMatrix(final Configuration configuration) {
    final var near = configuration.viewPortConfiguration().nearPlane();
    final var far = configuration.viewPortConfiguration().farPlane();
    return new Matrix4f().setOrtho(0.0f, 20.0f, 0.0f, 15.0f, near, far);
  }

  public static Matrix4f createViewMatrix() {
    return new Matrix4f().setLookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
  }
}
