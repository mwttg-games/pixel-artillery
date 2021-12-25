package io.github.mwttg.pixel.artillery.framework.entity.velocity;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A component for an entity. Used for movement.
 */
public class Velocity {
  private float horizontal;
  private float vertical;

  /**
   * The Constructor.
   *
   * @param horizontal the initial horizontal velocity
   * @param vertical   the initial vertical velocity
   */
  public Velocity(final float horizontal, final float vertical) {
    this.horizontal = horizontal;
    this.vertical = vertical;
  }

  public float getHorizontal() {
    return horizontal;
  }

  public void setHorizontal(float horizontal) {
    this.horizontal = horizontal;
  }

  public float getVertical() {
    return vertical;
  }

  public void setVertical(float vertical) {
    this.vertical = vertical;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Velocity velocity)) {
      return false;
    }
    return Float.compare(velocity.horizontal, horizontal) == 0
        && Float.compare(velocity.vertical, vertical) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(horizontal, vertical);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Velocity.class.getSimpleName() + "[", "]")
        .add("horizontal=" + horizontal)
        .add("vertical=" + vertical)
        .toString();
  }
}
