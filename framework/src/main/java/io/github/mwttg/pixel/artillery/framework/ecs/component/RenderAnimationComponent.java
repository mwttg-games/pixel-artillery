package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A component that holds everything to render an animated sprite.
 */
public class RenderAnimationComponent extends RenderComponent implements Component {

  private final int delayInMs;
  private final int maxAnimationSteps;
  private int currentAnimationStep;
  private long lastTick;

  public RenderAnimationComponent(final float[] vertices,
                                  final float[] textureCoordinates,
                                  final int textureId,
                                  final int delayInMs,
                                  final int maxAnimationSteps) {
    super(vertices, textureCoordinates, textureId);
    this.delayInMs = delayInMs;
    this.maxAnimationSteps = maxAnimationSteps;
    this.currentAnimationStep = 0;
    this.lastTick = System.currentTimeMillis();
  }

  // getter

  public int getDelayInMs() {
    return delayInMs;
  }

  public int getMaxAnimationSteps() {
    return maxAnimationSteps;
  }

  public int getCurrentAnimationStep() {
    return currentAnimationStep;
  }

  public long getLastTick() {
    return lastTick;
  }

  // setter

  public void incCurrentAnimationStep() {
    this.currentAnimationStep++;
    if (currentAnimationStep >= maxAnimationSteps) {
      currentAnimationStep = 0;
    }
  }

  public void setLastTick(long lastTick) {
    this.lastTick = lastTick;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RenderAnimationComponent that)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return delayInMs == that.delayInMs && maxAnimationSteps == that.maxAnimationSteps &&
        currentAnimationStep == that.currentAnimationStep && lastTick == that.lastTick;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), delayInMs, maxAnimationSteps, currentAnimationStep,
        lastTick);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RenderAnimationComponent.class.getSimpleName() + "[", "]")
        .add("delayInMs=" + delayInMs)
        .add("maxAnimationSteps=" + maxAnimationSteps)
        .add("currentAnimationStep=" + currentAnimationStep)
        .add("lastTick=" + lastTick)
        .toString();
  }
}
