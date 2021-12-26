package io.github.mwttg.pixel.artillery.examples.example05;

import io.github.mwttg.pixel.artillery.framework.entity.Entity;
import io.github.mwttg.pixel.artillery.framework.entity.boundary.grid.Grid;
import org.lwjgl.glfw.GLFW;

public class PlayerMovementSystem {

  // left, right, top, down
  private boolean[] blocked = {false, false, false, false};

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

  private void checkCollision2(Entity player, final Grid level, final float deltaX,
                               final float deltaY) {
    final var boundingBox = player.getPosition().getBoundingBox();

    // check left
    blocked[0] = level.isSolid(
        (int) (boundingBox.bottomLeft().getX() + deltaX),
        (int) (boundingBox.bottomLeft().getY() + 0.05f + deltaY))
        || level.isSolid(
        (int) (boundingBox.bottomLeft().getX() + deltaX),
        (int) (boundingBox.topRight().getY() + deltaY));
    // check right
    blocked[1] = level.isSolid(
        (int) (boundingBox.topRight().getX() + deltaX),
        (int) (boundingBox.topRight().getY() + deltaY))
        || level.isSolid(
        (int) (boundingBox.topRight().getX() + deltaX),
        (int) (boundingBox.bottomLeft().getY() + 0.05 + deltaY));
    // check top
    blocked[2] = level.isSolid(
        (int) (boundingBox.bottomLeft().getX() + deltaX),
        (int) (boundingBox.topRight().getY() + deltaY))
        || level.isSolid(
        (int) (boundingBox.topRight().getX() + deltaX),
        (int) (boundingBox.topRight().getY() + deltaY));
    // check bottom
    blocked[3] = level.isSolid(
        (int) (boundingBox.bottomLeft().getX() + deltaX),
        (int) (boundingBox.bottomLeft().getY() + deltaY))
        || level.isSolid(
        (int) (boundingBox.topRight().getX() + deltaX),
        (int) (boundingBox.bottomLeft().getY() + deltaY));
  }

  public void nextTick(final float deltaT, Entity player, final Grid level) {
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

    checkCollision2(player, level, deltaX, deltaY);

    // left & right
    if (blocked[0] || blocked[1]) {
      deltaX = 0.0f;
      player.getVelocity().setHorizontal(0.0f);
    }
    // top & bottom
    if (blocked[2]) {
      deltaY = 0.0f;
      player.getVelocity().setVertical(0.0f);
    }

    if (blocked[3]) {
      deltaY = 0.0f;
      player.getVelocity().setVertical(0.0f);
    }

    player.getPosition().translate(deltaX, deltaY, 0.0f);
  }
}
