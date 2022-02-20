package io.github.mwttg.pixel.artillery.framework.ecs;

public class GameConfiguration {

  // TODO make it configurable by reading in a json file

  private final float playerJumpGravity = 22.0f;
  private final float playerFallGravity = 38.0f;
  private final float playerWalkVelocity = 8.0f;
  private final float playerInitJumpVelocity = 12.0f;

  public float getPlayerJumpGravity() {
    return playerJumpGravity;
  }

  public float getPlayerFallGravity() {
    return playerFallGravity;
  }

  public float getPlayerWalkVelocity() {
    return playerWalkVelocity;
  }

  public float getPlayerInitJumpVelocity() {
    return playerInitJumpVelocity;
  }
}
