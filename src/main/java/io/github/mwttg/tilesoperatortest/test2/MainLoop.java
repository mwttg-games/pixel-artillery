package io.github.mwttg.tilesoperatortest.test2;

import io.github.mwttg.tileoperator.core.TextureLoader;
import io.github.mwttg.tileoperator.core.VertexArrayObject;
import io.github.mwttg.tileoperator.core.render.MatrixStack;
import io.github.mwttg.tileoperator.core.render.textured.TexturedRenderer;
import io.github.mwttg.tileoperator.window.Configuration;
import io.github.mwttg.tilesoperatortest.test2.levelloader.LevelData;
import java.io.IOException;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {
  private final MatrixStack matrixStack;
  private final TexturedRenderer texturedRenderer;

  private final int vaoId;
  private final int textureId;
  private final int size;

  public MainLoop(final Configuration configuration) throws IOException {
    // create OpenGL ids
    final var levelData = LevelData.createFrom("files/test2/level.json");
    this.vaoId =
        VertexArrayObject.create(levelData.tilesGeometry(), levelData.textureCoordinates());
    this.textureId = TextureLoader.createFrom("files/test2/texture-atlas.png");
    this.size = levelData.tilesGeometry().length / 3;

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

      texturedRenderer.draw(matrixStack, vaoId, textureId, size);

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
