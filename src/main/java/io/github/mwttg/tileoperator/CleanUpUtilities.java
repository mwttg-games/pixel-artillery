package io.github.mwttg.tileoperator;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Functionality to clean up OpenGL.
 */
public final class CleanUpUtilities {

  private static final Logger LOG = LoggerFactory.getLogger(CleanUpUtilities.class);

  private static long gameWindowId;

  private CleanUpUtilities() {
  }

  /**
   * Add the GameWindow id for later cleanup.
   *
   * @param id of the GameWindow
   */
  public static void setGameWindowId(final Long id) {
    gameWindowId = id;
  }

  /**
   * Clean up OpenGL.
   */
  public static void purge() {
    LOG.info("Start clean up process");
    LOG.debug("  Remove GameWindow");
    cleanUpGameWindow();
  }

  private static void cleanUpGameWindow() {
    GLFW.glfwDestroyWindow(gameWindowId);
    GLFW.glfwTerminate();
  }
}

