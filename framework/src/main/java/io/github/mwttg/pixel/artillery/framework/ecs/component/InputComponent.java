package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A component to check which action is triggered.
 */
public class InputComponent implements Component {

  private boolean moveLeft;
  private boolean moveRight;
  private boolean jump;

  public void reset() {
    moveLeft = false;
    moveRight = false;
    jump = false;
  }

  public boolean isMoveLeft() {
    return moveLeft;
  }

  public void setMoveLeft(boolean moveLeft) {
    this.moveLeft = moveLeft;
  }

  public boolean isMoveRight() {
    return moveRight;
  }

  public void setMoveRight(boolean moveRight) {
    this.moveRight = moveRight;
  }

  public boolean isJump() {
    return jump;
  }

  public void setJump(boolean jump) {
    this.jump = jump;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InputComponent that)) {
      return false;
    }
    return moveLeft == that.moveLeft && moveRight == that.moveRight && jump == that.jump;
  }

  @Override
  public int hashCode() {
    return Objects.hash(moveLeft, moveRight, jump);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InputComponent.class.getSimpleName() + "[", "]")
        .add("moveLeft=" + moveLeft)
        .add("moveRight=" + moveRight)
        .add("jump=" + jump)
        .toString();
  }
}
