package io.github.mwttg.pixel.artillery.examples.example03;

import io.github.mwttg.pixel.artillery.examples.FileReader;
import io.github.mwttg.pixel.artillery.framework.core.TextureLoader;
import io.github.mwttg.pixel.artillery.framework.core.VertexArrayObject;
import io.github.mwttg.pixel.artillery.framework.core.render.MatrixStack;
import io.github.mwttg.pixel.artillery.framework.core.render.textured.TexturedRenderer;
import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import java.io.IOException;
import java.util.List;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private final MatrixStack matrixStack;
  private final TexturedRenderer texturedRenderer;

  private final int vaoId;
  private final int textureId;
  private final List<AnimationStep> animationSteps;

  public MainLoop(final Configuration configuration) throws IOException {
    // create OpenGL ids
    final var animation = FileReader.createFrom("files/example03/animation.json", Animation.class);
    this.vaoId =
        VertexArrayObject.create(animation.tilesGeometry(), animation.textureCoordinates());
    this.textureId = TextureLoader.createFrom("files/example03/animation.png");
    this.animationSteps =
        List.of(new AnimationStep(0, 6), new AnimationStep(6, 6), new AnimationStep(12, 6),
            new AnimationStep(18, 6));

    // MatrixStack & Renderer
    final var modelMatrix = new Matrix4f().translate(10, 10, 0);
    final var viewMatrix =
        (new Matrix4f()).setLookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    final var projectionMatrix = createOrtho2DMatrix(configuration);
    this.matrixStack = new MatrixStack(modelMatrix, viewMatrix, projectionMatrix);
    this.texturedRenderer = new TexturedRenderer();
  }

  public void loop(final long gameWindowId) {

    var last = System.currentTimeMillis();
    var index = 0;

    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);


      final var now = System.currentTimeMillis();
      if (now - last > 500L) {
        last = now;
        index++;
      }

      if (index > animationSteps.size() - 1) {
        index = 0;
      }

      final var step = animationSteps.get(index);

      texturedRenderer.draw(matrixStack, vaoId, textureId, step.startIndex(), step.count());

      GLFW.glfwPollEvents();
      GLFW.glfwSwapBuffers(gameWindowId);
    }
  }

  private Matrix4f createOrtho2DMatrix(final Configuration configuration) {
    final var near = configuration.viewPortConfiguration().nearPlane();
    final var far = configuration.viewPortConfiguration().farPlane();
    return new Matrix4f().setOrtho(0.0f, 20.0f, 0.0f, 15.0f, near, far);
  }
}
