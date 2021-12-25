package io.github.mwttg.pixel.artillery.examples.example05;

import io.github.mwttg.pixel.artillery.framework.entity.Entity;
import io.github.mwttg.pixel.artillery.framework.entity.boundary.grid.Grid;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.Sprite;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.SpriteAnimation;
import io.github.mwttg.pixel.artillery.framework.entity.position.Position;
import io.github.mwttg.pixel.artillery.framework.entity.velocity.Velocity;
import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private final Matrix4f viewMatrix;
  private final Matrix4f projectionMatrix;
  private final Entity player;
  private final Entity level;
  private final Grid levelBoundary;

  private final PlayerMovementSystem playerMovementSystem;

  public MainLoop(final Configuration configuration) {
    final var playerIdleJsonFile = "files/player/blob/player-idle.json";
    final var playerIdleTextureFile = "files/player/blob/player-idle.png";
    final var playerIdleDrawable = new SpriteAnimation(playerIdleJsonFile, playerIdleTextureFile);
    final var playerLeftJsonFile = "files/player/blob/player-left.json";
    final var playerLeftTextureFile = "files/player/blob/player-left.png";
    final var playerLeftDrawable = new SpriteAnimation(playerLeftJsonFile, playerLeftTextureFile);
    final var playerRightJsonFile = "files/player/blob/player-right.json";
    final var playerRightTextureFile = "files/player/blob/player-right.png";
    final var playerRightDrawable =
        new SpriteAnimation(playerRightJsonFile, playerRightTextureFile);
    final var playerModelMatrix = new Matrix4f().translate(5, 5, 0);
    final var playerPosition = new Position(playerModelMatrix, 1.0f);
    final var playerVelocity = new Velocity(0.0f, 0.0f);
    this.player = new Entity.EntityBuilder()
        .addDrawable("idle", playerIdleDrawable)
        .addDrawable("left", playerLeftDrawable)
        .addDrawable("right", playerRightDrawable)
        .setDrawableName("idle")
        .addPosition(playerPosition)
        .addVelocity(playerVelocity)
        .setGravity(9.81f)
        .build();

    final var levelJsonFile = "files/level/level3/level.json";
    final var levelTextureFile = "files/level/texture-atlas.png";
    final var levelDrawable = new Sprite(levelJsonFile, levelTextureFile);
    final var levelModelMatrix = new Matrix4f().translate(0, 0, -1);
    final var levelPosition = new Position(levelModelMatrix, 80.0f);
    this.level = new Entity.EntityBuilder()
        .addDrawable(levelDrawable)
        .addPosition(levelPosition)
        .build();

    final var levelBoundaryJsonFile = "files/level/level3/level-boundary.json";
    this.levelBoundary = new Grid(levelBoundaryJsonFile);

    this.viewMatrix = createViewMatrix();
    this.projectionMatrix = createOrtho2DMatrix(configuration);

    this.playerMovementSystem = new PlayerMovementSystem();
  }

  public void loop(final long gameWindowId) {
    var last = System.currentTimeMillis();
    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      // clock
      var current = System.currentTimeMillis();
      final var deltaT = (current - last) / 1000.0f;
      last = current;

      // logic
      playerMovementSystem.execute(gameWindowId, deltaT, player, levelBoundary);

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
}
