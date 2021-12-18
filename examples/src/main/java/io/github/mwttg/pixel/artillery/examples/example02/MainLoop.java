package io.github.mwttg.pixel.artillery.examples.example02;

import io.github.mwttg.pixel.artillery.framework.core.render.MatrixStack;
import io.github.mwttg.pixel.artillery.framework.core.render.textured.TexturedRenderer;
import io.github.mwttg.pixel.artillery.framework.core.sprites.Sprite;
import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {
  private final MatrixStack matrixStack;
  private final TexturedRenderer texturedRenderer;

  private final Sprite sprite;

  public MainLoop(final Configuration configuration) {
    final var jsonFile = "files/example02/level.json";
    final var textureFile = "files/example02/texture-atlas.png";
    this.sprite = Sprite.fromResources(jsonFile, textureFile);

    // MatrixStack & Renderer
    final var modelMatrix = new Matrix4f().translate(0, 0, 0);
    final var viewMatrix =
        (new Matrix4f()).setLookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    final var projectionMatrix = createOrtho2DMatrix(configuration);
    this.matrixStack = new MatrixStack(modelMatrix, viewMatrix, projectionMatrix);
    this.texturedRenderer = new TexturedRenderer();
  }

  public void loop(final long gameWindowId) {

    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      texturedRenderer.draw(matrixStack, sprite);

      GLFW.glfwPollEvents();
      GLFW.glfwSwapBuffers(gameWindowId);
    }
  }

  private Matrix4f createOrtho2DMatrix(final Configuration configuration) {
    final var near = configuration.viewPortConfiguration().nearPlane();
    final var far = configuration.viewPortConfiguration().farPlane();
    return new Matrix4f().setOrtho(0.0f, 40.0f, 0.0f, 30.0f, near, far);
  }
}
