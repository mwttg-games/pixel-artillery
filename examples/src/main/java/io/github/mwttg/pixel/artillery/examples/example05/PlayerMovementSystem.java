package io.github.mwttg.pixel.artillery.examples.example05;

import io.github.mwttg.pixel.artillery.framework.entity.Entity;
import io.github.mwttg.pixel.artillery.framework.entity.boundary.grid.Grid;
import org.lwjgl.glfw.GLFW;

public class PlayerMovementSystem {

  private boolean jumping = false;

  public void execute(final long gameWindowId, final float deltaT, Entity player,
                      final Grid level) {
    processKeys(gameWindowId, player);
    nextTick(deltaT, player, level);
  }

  /**
   * The whole Entity is mutable and gets manipulated inside this method!
   */
  public void processKeys(final long gameWindowId, Entity player) {
    if (GLFW.glfwGetKey(gameWindowId, GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS) {
      player.setDrawableName("left");
      player.getVelocity().setHorizontal(-3.0f);
    } else if (GLFW.glfwGetKey(gameWindowId, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS) {
      player.setDrawableName("right");
      player.getVelocity().setHorizontal(3.0f);
    } else {
      player.setDrawableName("idle");
      player.getVelocity().setHorizontal(0.0f);
    }

    if (GLFW.glfwGetKey(gameWindowId, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
      player.getVelocity().setVertical(10.0f);
      jumping = true;
    }
  }

  public void nextTick(final float deltaT, Entity player, final Grid level) {
    final var deltaX = player.getVelocity().getHorizontal() * deltaT;

    final float deltaY;
    if (jumping) {
      deltaY =
          (-player.getGravity() * deltaT * deltaT) + (player.getVelocity().getVertical() * deltaT);
      final var newVerticalVelocity =
          (-player.getGravity() * deltaT) + player.getVelocity().getVertical();
      player.getVelocity().setVertical(newVerticalVelocity);
    } else {
      deltaY = 0.0f;
    }

    if (hasPlayerFeetOnGround(player, level)) {
      jumping = false;
    }

    player.getPosition().translate(deltaX, deltaY, 0.0f);
  }

  private boolean hasPlayerFeetOnGround(Entity player, final Grid level) {
    final var position = player.getPosition().getCoordinates();
    final var x = ((int) position.x());
    final var y = (int) (position.y() - 0.25f);

    final var r = level.isSolid(x, y);
    return r;
  }
}
