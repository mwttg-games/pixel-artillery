package io.github.mwttg.pixel.artillery.examples.example04;

import io.github.mwttg.pixel.artillery.framework.entity.Entity;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.Sprite;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.SpriteAnimation;
import io.github.mwttg.pixel.artillery.framework.entity.movable.MoveTuple;
import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private final Matrix4f viewMatrix;
  private final Matrix4f projectionMatrix;
  private final Entity player;
  private final Entity level;

  public MainLoop(final Configuration configuration) {
    final var playerIdleJsonFile = "files/example04/player-idle.json";
    final var playerIdleTextureFile = "files/example04/player-idle.png";
    final var playerIdleDrawable = new SpriteAnimation(playerIdleJsonFile, playerIdleTextureFile);
    final var playerLeftJsonFile = "files/example04/player-left.json";
    final var playerLeftTextureFile = "files/example04/player-left.png";
    final var playerLeftDrawable = new SpriteAnimation(playerLeftJsonFile, playerLeftTextureFile);
    final var playerRightJsonFile = "files/example04/player-right.json";
    final var playerRightTextureFile = "files/example04/player-right.png";
    final var playerRightDrawable =
        new SpriteAnimation(playerRightJsonFile, playerRightTextureFile);

    final var playerModelMatrix = new Matrix4f().translate(10, 7, 0);
    this.player = new Entity.EntityBuilder()
        .addDrawable("idle", playerIdleDrawable)
        .addDrawable("left", playerLeftDrawable)
        .addDrawable("right", playerRightDrawable)
        .setDrawableName("idle")
        .addModelMatrix(playerModelMatrix)
        .build();

    final var levelJsonFile = "files/example04/level.json";
    final var levelTextureFile = "files/example04/texture-atlas.png";
    final var levelDrawable = new Sprite(levelJsonFile, levelTextureFile);
    final var levelModelMatrix = new Matrix4f().translate(0, 0, -1);
    this.level = new Entity.EntityBuilder()
        .addDrawable(levelDrawable)
        .addModelMatrix(levelModelMatrix)
        .build();

    this.viewMatrix = createViewMatrix();
    this.projectionMatrix = createOrtho2DMatrix(configuration);
  }

  public void loop(final long gameWindowId) {

    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      // logic
      final var playerMovement = playerMovement(gameWindowId, player.getModel());
      player.setMovement(playerMovement);

      // clear
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      // render
      level.draw(viewMatrix, projectionMatrix);
      player.draw(viewMatrix, projectionMatrix);

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

  private MoveTuple playerMovement(final long gameWindowId, final Matrix4f playerModelMatrix) {
    if (GLFW.glfwGetKey(gameWindowId, GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS) {
      viewMatrix.translate(0.08f, 0f, 0f, viewMatrix);
      return new MoveTuple("left", playerModelMatrix.translate(-0.08f, 0f, 0f, playerModelMatrix));
    } else if (GLFW.glfwGetKey(gameWindowId, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS) {
      viewMatrix.translate(-0.08f, 0f, 0f, viewMatrix);
      return new MoveTuple("right", playerModelMatrix.translate(0.08f, 0f, 0f, playerModelMatrix));
    } else {
      return new MoveTuple("idle", playerModelMatrix);
    }
  }
}
