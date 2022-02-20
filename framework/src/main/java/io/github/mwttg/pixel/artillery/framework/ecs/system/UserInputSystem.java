package io.github.mwttg.pixel.artillery.framework.ecs.system;

import io.github.mwttg.pixel.artillery.framework.ecs.component.InputComponent;
import org.lwjgl.glfw.GLFW;

public class UserInputSystem {

  public static void process(final InputComponent component, final long windowId) {
    if ((GLFW.glfwGetKey(windowId, GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS) &&
        !(GLFW.glfwGetKey(windowId, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS)) {
      component.setMoveLeft(true);
      component.setMoveRight(false);
    } else if (GLFW.glfwGetKey(windowId, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS &&
        !(GLFW.glfwGetKey(windowId, GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS)) {
      component.setMoveRight(true);
      component.setMoveLeft(false);
    } else {
      component.reset();
    }

    if (GLFW.glfwGetKey(windowId, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
      component.setJump(true);
    } else {
      component.setJump(false);
    }
  }
}
