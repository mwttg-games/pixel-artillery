package io.github.mwttg.pixel.artillery.framework.core;

/**
 * Helper Class for creating shader program ids.
 */
public final class ShaderFactory {

  private ShaderFactory() {
  }

  /**
   * Creates a shader program for geometry and texture data.
   *
   * @return shader program id
   */
  public static int createTextureShader() {
    return ShaderProgram.create("/shaders/textured/vertex-shader.glsl",
        "/shaders/textured/fragment-shader.glsl");
  }
}
