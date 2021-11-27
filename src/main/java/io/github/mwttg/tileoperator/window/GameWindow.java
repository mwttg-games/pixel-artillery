package io.github.mwttg.tileoperator.window;

import static org.lwjgl.system.MemoryUtil.NULL;

import io.github.mwttg.tileoperator.CleanUpUtilities;
import java.io.IOException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL40;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The magic window where everything happens (like OpenGL operations).
 */
public class GameWindow {

  private static final Logger LOG = LoggerFactory.getLogger(GameWindow.class);

  private GameWindow() {
  }

  /**
   * Creates the game window where 'everything' can be rendered with OpenGL.
   *
   * @param configuration the {@link Configuration} for the GameWindow
   * @return the OpenGL id of that window
   * @throws IOException if the creating fails for any reason
   */
  public static long create(final Configuration configuration) throws IOException {
    LOG.info(configuration.prettyPrint());
    LOG.info("Start GameWindow");
    initializeGlfw();
    configureGameWindow(configuration);
    final var id = createGameWindow(configuration);
    CleanUpUtilities.setGameWindowId(id);
    setKeyCallback(id);
    applyOpenGlConfiguration(id, configuration);
    centerGameWindow(id);

    return id;
  }

  private static void initializeGlfw() throws IOException {
    LOG.debug("  Initialize GLFW");
    GLFWErrorCallback.createPrint(System.err).set();

    if (!GLFW.glfwInit()) {
      throw new IOException("GLFW wasn't initialized correctly.");
    }
  }

  private static void configureGameWindow(final Configuration configuration) {
    final var majorVersion = configuration.openGlConfiguration().openGlMajorVersion();
    final var minorVersion = configuration.openGlConfiguration().openGlMinorVersion();
    LOG.debug("  Configure GameWindow. Using OpenGL version %s.%s.".formatted(majorVersion,
        minorVersion));

    GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL40.GL_TRUE);
    GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL40.GL_TRUE);
    GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, majorVersion);
    GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, minorVersion);
    GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL40.GL_TRUE);
    GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
  }

  private static long createGameWindow(final Configuration configuration) throws IOException {
    final var title = configuration.gameWindowConfiguration().title();
    final var width = configuration.gameWindowConfiguration().width();
    final var height = configuration.gameWindowConfiguration().height();
    LOG.debug(
        "  Create GameWindow with title %s and dimension %sx%s".formatted(title, width, height));

    final var id = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);
    if (id == NULL) {
      throw new IOException("Unable to create GameWindow window");
    }

    return id;
  }

  private static void setKeyCallback(final long id) {
    LOG.debug("  Set key callback");

    final GLFWKeyCallbackI callback =
        (long windowId, int key, int scancode, int action, int mods) -> {
          if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
            GLFW.glfwSetWindowShouldClose(windowId, true);
          }
        };
    GLFW.glfwSetKeyCallback(id, callback);
  }

  private static void applyOpenGlConfiguration(final long id, final Configuration configuration) {
    LOG.debug("  Setup OpenGL");
    final var vsync = configuration.openGlConfiguration().vsync() ? 1 : 0;
    final var wireframe = configuration.openGlConfiguration().wireframe();

    GLFW.glfwMakeContextCurrent(id);
    GL.createCapabilities();
    GL40.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
    GLFW.glfwSwapInterval(vsync);
    GLFW.glfwShowWindow(id);
    GL40.glEnable(GL40.GL_DEPTH_TEST);
    GL40.glEnable(GL40.GL_BLEND);
    GL40.glBlendFunc(GL40.GL_SRC_ALPHA, GL40.GL_ONE_MINUS_SRC_ALPHA);
    GL40.glEnable(GL40.GL_CULL_FACE);
    GL40.glCullFace(GL40.GL_BACK);

    if (wireframe) {
      GL40.glPolygonMode(GL40.GL_FRONT_AND_BACK, GL40.GL_LINE);
    }
  }

  private static void centerGameWindow(final long id) throws IOException {
    LOG.debug("  Center GameWindow");

    final var stack = MemoryStack.stackPush();
    final var pWidth = stack.mallocInt(1);
    final var pHeight = stack.mallocInt(1);
    GLFW.glfwGetWindowSize(id, pWidth, pHeight);
    final var videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
    if (videoMode == null) {
      throw new IOException("No video mode found");
    }

    final var xPos = (videoMode.width() - pWidth.get(0)) / 2;
    final var yPos = (videoMode.height() - pHeight.get(0)) / 2;
    GLFW.glfwSetWindowPos(id, xPos, yPos);
  }
}

