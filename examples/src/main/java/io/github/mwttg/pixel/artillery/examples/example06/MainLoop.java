package io.github.mwttg.pixel.artillery.examples.example06;

import io.github.mwttg.pixel.artillery.examples.Matrix;
import io.github.mwttg.pixel.artillery.framework.ecs.GameConfiguration;
import io.github.mwttg.pixel.artillery.framework.ecs.Timer;
import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private final Matrix4f projectionMatrix;
  private final Matrix4f viewMatrix;
  private final Level level01;
  private final Player player;
  private final Timer timer;
  private final GameConfiguration gameConfiguration;

  public MainLoop(final Configuration configuration) {
    this.level01 = initScene();
    this.projectionMatrix = Matrix.createOrtho2DMatrix(configuration);
    this.viewMatrix = Matrix.createViewMatrix();
    this.player = new Player(EntityFactory.createPlayerEntity());
    this.timer = new Timer();
    this.gameConfiguration = new GameConfiguration();
  }

  public void loop(final long gameWindowId) {
    timer.reset();
    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      // clear
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      final var deltaTime = timer.getDeltaTime();
      player.update(gameWindowId, deltaTime, level01.getCollisionGrid(), gameConfiguration);

      level01.render(viewMatrix, projectionMatrix);
      player.render(viewMatrix, projectionMatrix);

      GLFW.glfwPollEvents();
      GLFW.glfwSwapBuffers(gameWindowId);
    }
  }

  private Level initScene() {
    final var levelEntity = EntityFactory.createLevelTilesEntity();
    return new Level(levelEntity);
  }
}
