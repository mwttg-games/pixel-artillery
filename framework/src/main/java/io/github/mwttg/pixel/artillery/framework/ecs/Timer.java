package io.github.mwttg.pixel.artillery.framework.ecs;

/**
 * A Timer for calculating the delta time (in seconds) between two time points
 */
public class Timer {

  private long lastTick;

  public Timer() {
    this.lastTick = System.currentTimeMillis();
  }

  public void reset() {
    this.lastTick = System.currentTimeMillis();
  }

  public float getDeltaTime() {
    final var currentTick = System.currentTimeMillis();
    final var delta = (currentTick - lastTick) / 1000.0f;
    lastTick = currentTick;
    return delta;
  }
}
