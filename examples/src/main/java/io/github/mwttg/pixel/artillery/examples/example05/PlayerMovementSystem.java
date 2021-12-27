package io.github.mwttg.pixel.artillery.examples.example05;

import io.github.mwttg.pixel.artillery.framework.entity.Entity;
import io.github.mwttg.pixel.artillery.framework.entity.boundary.grid.Grid;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

public class PlayerMovementSystem {

  // left, right, top, down
  private boolean[] blocked = {false, false, false, false};

  public void execute(final long gameWindowId, final float deltaT, Entity player,
                      final Grid level, Matrix4f camera) { // camera is mutable
    processKeys(gameWindowId, player);
    nextTick(deltaT, player, level, camera);
  }

  /**
   * The whole Entity is mutable and gets manipulated inside this method!
   */
  public void processKeys(final long gameWindowId, Entity player) {
    if (GLFW.glfwGetKey(gameWindowId, GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS) {
      player.setDrawableName("left");
      player.getVelocity().setHorizontal(-4.0f);
    } else if (GLFW.glfwGetKey(gameWindowId, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS) {
      player.setDrawableName("right");
      player.getVelocity().setHorizontal(4.0f);
    } else {
      player.setDrawableName("idle");
      player.getVelocity().setHorizontal(0.0f);
    }

    if (GLFW.glfwGetKey(gameWindowId, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
      player.getVelocity().setVertical(6.0f);
      blocked[3] = false;
    }
  }

  private void checkCollision(Entity player, final Grid level) {
    final var currentCenter = player.getPosition().getBoundingBox().getCenter();
    final var currentBoundingBox = player.getPosition().getBoundingBox();
    final var blockedDirections = level.checkCollision(currentCenter, currentBoundingBox);
    blocked[0] = blockedDirections.left();
    blocked[1] = blockedDirections.right();
    blocked[2] = blockedDirections.top();
    blocked[3] = blockedDirections.bottom();
  }



  public void nextTick(final float deltaT, Entity player, final Grid level, Matrix4f camera) {
    var deltaX = player.getVelocity().getHorizontal() * deltaT;

    float deltaY;
    if (!blocked[3]) {
      deltaY =
          (-player.getGravity() * deltaT * deltaT) + (player.getVelocity().getVertical() * deltaT);
      final var newVerticalVelocity =
          (-player.getGravity() * deltaT) + player.getVelocity().getVertical();
      player.getVelocity().setVertical(newVerticalVelocity);
    } else {
      deltaY = 0.0f;
    }

    checkCollision(player, level);

    // left
    if (blocked[0]) {
      deltaX = snapLeft(player);
      player.getVelocity().setHorizontal(0.0f);
    }

    // right
    if (blocked[1]) {
      deltaX = snapRight(player);
      player.getVelocity().setHorizontal(0.0f);
    }

    // top
    if (blocked[2]) {
      deltaY = snapTop(player);
      player.getVelocity().setVertical(0.0f);
    }

    // bottom
    if (blocked[3]) {
      deltaY = snapBottom(player);
      player.getVelocity().setVertical(0.0f);
    }

    player.getPosition().translate(deltaX, deltaY, 0.0f);
    camera.translate(-deltaX, -deltaY, 0.0f);
  }

  private float snapBottom(Entity player) {
    final var y = player.getPosition().getBoundingBox().getBottomLeft().getY();
    final var iPart = (int) y;
    final var fPart = (iPart + 1.0f) - y;

    if (fPart < 0.001f) {
      return 0.0f;
    }
    return fPart;
  }

  private float snapLeft(Entity player) {
    final var x = player.getPosition().getBoundingBox().getBottomLeft().getX();
    final var iPart = (int) x;

    return (iPart + 1.0f) - x;
  }

  private float snapRight(Entity player) {
    final var x = player.getPosition().getBoundingBox().getTopRight().getX();
    final var iPart = (int) x;

    return (iPart) - x;
  }

  private float snapTop(Entity player) {
    final var y = player.getPosition().getBoundingBox().getTopRight().getY();
    final var iPart = (int) y;

    return (iPart) - y;
  }
}
