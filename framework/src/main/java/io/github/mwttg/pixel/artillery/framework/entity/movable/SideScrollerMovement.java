package io.github.mwttg.pixel.artillery.framework.entity.movable;

import java.util.ArrayList;
import java.util.List;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

/**
 * At the moment the simplest movement ever. Only left/right is supported without changing the
 * camera.
 */
public class SideScrollerMovement implements Movable {

  @Override
  public MoveTuple move(final long windowId, final Matrix4f model) {
    final var playerState = getPlayerState(windowId);

    if (playerState.contains(PlayerState.MOVE_LEFT)) {
      return new MoveTuple("left", model.translate(-0.05f, 0f, 0f, model));
    }

    if (playerState.contains(PlayerState.MOVE_RIGHT)) {
      return new MoveTuple("right", model.translate(0.05f, 0f, 0f, model));
    }

    return new MoveTuple("idle", model);
  }


  private List<PlayerState> getPlayerState(final long windowId) {
    final var result = new ArrayList<PlayerState>();

    if (GLFW.glfwGetKey(windowId, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
      result.add(PlayerState.JUMP);
    }

    if (GLFW.glfwGetKey(windowId, GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS) {
      result.add(PlayerState.MOVE_LEFT);
    } else if (GLFW.glfwGetKey(windowId, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS) {
      result.add(PlayerState.MOVE_RIGHT);
    }

    if (result.isEmpty()) {
      result.add(PlayerState.IDLE);
    }

    return result;
  }
}
