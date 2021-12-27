package io.github.mwttg.pixel.artillery.common;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A class for a mutable Point.
 */
public class Point {

  //CHECKSTYLE:OFF - no one letter member names allowed :/
  private float x;
  private float y;
  //CHECKSTYLE:ON

  // for jackson
  public Point() {
  }

  public Point(final float x, final float y) {
    this.x = x;
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Point point)) {
      return false;
    }
    return Float.compare(point.x, x) == 0 && Float.compare(point.y, y) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Point.class.getSimpleName() + "[", "]")
        .add("x=" + x)
        .add("y=" + y)
        .toString();
  }
}
