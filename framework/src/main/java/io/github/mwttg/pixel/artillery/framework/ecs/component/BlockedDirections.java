package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.Objects;
import java.util.StringJoiner;

public class BlockedDirections {

  private boolean top;
  private boolean right;
  private boolean bottom;
  private boolean left;

  public BlockedDirections(boolean top, boolean right, boolean bottom, boolean left) {
    this.top = top;
    this.right = right;
    this.bottom = bottom;
    this.left = left;
  }

  public void mergeTopDownWith(final BlockedDirections other) {
    this.top = this.top || other.isTop();
    this.bottom = this.bottom || other.isBottom();
  }

  public void mergeLeftRight(final BlockedDirections other) {
    this.right = this.right || other.isRight();
    this.left = this.left || other.isLeft();
  }

  public boolean isTop() {
    return top;
  }

  public boolean isRight() {
    return right;
  }

  public boolean isBottom() {
    return bottom;
  }

  public boolean isLeft() {
    return left;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BlockedDirections that)) {
      return false;
    }
    return top == that.top && right == that.right && bottom == that.bottom && left == that.left;
  }

  @Override
  public int hashCode() {
    return Objects.hash(top, right, bottom, left);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BlockedDirections.class.getSimpleName() + "[", "]")
        .add("top=" + top)
        .add("right=" + right)
        .add("bottom=" + bottom)
        .add("left=" + left)
        .toString();
  }
}
