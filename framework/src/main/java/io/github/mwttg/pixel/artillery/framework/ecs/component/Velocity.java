package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.Objects;
import java.util.StringJoiner;

public class Velocity {

  private float horizontal;
  private float vertical;

  public Velocity(float horizontal, float vertical) {
    this.horizontal = horizontal;
    this.vertical = vertical;
  }

  // getter

  public float getHorizontal() {
    return horizontal;
  }

  public float getVertical() {
    return vertical;
  }

  // setter

  public void setHorizontal(float horizontal) {
    this.horizontal = horizontal;
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
    return Float.compare(velocity.horizontal, horizontal) == 0 &&
        Float.compare(velocity.vertical, vertical) == 0;
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
