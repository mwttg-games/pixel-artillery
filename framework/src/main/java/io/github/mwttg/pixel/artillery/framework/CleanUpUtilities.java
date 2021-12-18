package io.github.mwttg.pixel.artillery.framework;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Functionality to clean up OpenGL.
 */
public final class CleanUpUtilities {

  private static final Logger LOG = LoggerFactory.getLogger(CleanUpUtilities.class);

  private static long gameWindowId;
  private static List<Integer> shaderProgramIds = new ArrayList<>();
  private static List<Integer> shaderIds = new ArrayList<>();
  private static List<Integer> vertexBufferObjectIds = new ArrayList<>();
  private static List<Integer> vertexArrayObjectIds = new ArrayList<>();
  private static List<Integer> textureIds = new ArrayList<>();

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
   * Add a ShaderProgram id for later clean up.
   *
   * @param id of the ShaderProgram
   */
  public static void addShaderProgramId(final int id) {
    shaderProgramIds.add(id);
  }

  /**
   * Add a Shader id for later clean up.
   *
   * @param id of the Shader
   */
  public static void addShaderId(final int id) {
    shaderIds.add(id);
  }

  /**
   * Add a vertex buffer object id for later clean up.
   *
   * @param id of the vertex buffer object
   */
  public static void addVertexBufferObjectId(final int id) {
    vertexBufferObjectIds.add(id);
  }

  /**
   * Add a vertex array object id for later clean up.
   *
   * @param id of the vertex array object
   */
  public static void addVertexArrayObjectId(final int id) {
    vertexArrayObjectIds.add(id);
  }

  /**
   * Add a texture for later clean up.
   *
   * @param id of the texture
   */
  public static void addTextureId(final int id) {
    textureIds.add(id);
  }

  /**
   * Clean up OpenGL.
   */
  public static void purge() {
    LOG.info("Start clean up process");
    LOG.debug("  Remove Textures");
    textureIds.forEach(GL40::glDeleteTextures);
    LOG.debug("  Remove VertexArrayObjects");
    vertexArrayObjectIds.forEach(GL40::glDeleteVertexArrays);
    LOG.debug("  Remove VertexBufferObjects");
    vertexBufferObjectIds.forEach(GL40::glDeleteBuffers);
    LOG.debug("  Remove Shaders");
    shaderIds.forEach(GL40::glDeleteShader);
    LOG.debug("  Remove ShaderPrograms");
    shaderProgramIds.forEach(GL40::glDeleteProgram);
    LOG.debug("  Remove GameWindow");
    cleanUpGameWindow();

    textureIds = new ArrayList<>();
    vertexArrayObjectIds = new ArrayList<>();
    vertexBufferObjectIds = new ArrayList<>();
    shaderIds = new ArrayList<>();
    shaderProgramIds = new ArrayList<>();
  }


  private static void cleanUpGameWindow() {
    GLFW.glfwDestroyWindow(gameWindowId);
    GLFW.glfwTerminate();
  }
}

