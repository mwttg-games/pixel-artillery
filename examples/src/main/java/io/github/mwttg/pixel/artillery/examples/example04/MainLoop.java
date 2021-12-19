package io.github.mwttg.pixel.artillery.examples.example04;

import io.github.mwttg.pixel.artillery.framework.entity.Entity;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.SpriteAnimation;
import io.github.mwttg.pixel.artillery.framework.entity.movable.SideScrollerMovement;
import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private final Matrix4f viewMatrix;
  private final Matrix4f projectionMatrix;
  private final Entity entity;

  public MainLoop(final Configuration configuration) {
    final var jsonFile = "files/example03/animation.json";
    final var textureFile = "files/example03/animation.png";
    final var drawable = new SpriteAnimation(jsonFile, textureFile);
    final var modelMatrix = new Matrix4f().translate(10, 10, 0);
    final var movable = new SideScrollerMovement();
    this.entity = new Entity.EntityBuilder()
        .addDrawable(drawable)
        .addModelMatrix(modelMatrix)
        .addMovable(movable)
        .build();

    this.viewMatrix = createViewMatrix();
    this.projectionMatrix = createOrtho2DMatrix(configuration);
  }

  public void loop(final long gameWindowId) {

    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      entity.move(gameWindowId);
      entity.draw(viewMatrix, projectionMatrix);

      GLFW.glfwPollEvents();
      GLFW.glfwSwapBuffers(gameWindowId);
    }
  }

  private Matrix4f createOrtho2DMatrix(final Configuration configuration) {
    final var near = configuration.viewPortConfiguration().nearPlane();
    final var far = configuration.viewPortConfiguration().farPlane();
    return new Matrix4f().setOrtho(0.0f, 20.0f, 0.0f, 15.0f, near, far);
  }

  private Matrix4f createViewMatrix() {
    return new Matrix4f().setLookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
  }
}
