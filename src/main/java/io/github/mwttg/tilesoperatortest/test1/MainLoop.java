package io.github.mwttg.tilesoperatortest.test1;

import io.github.mwttg.tileoperator.window.Configuration;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

public class MainLoop {

  private final Configuration configuration;

  public MainLoop(final Configuration configuration) {
    this.configuration = configuration;
  }

  public void loop(final long gameWindowId) {

    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
      GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

      GLFW.glfwSwapBuffers(gameWindowId);
      GLFW.glfwPollEvents();
    }
  }
}
