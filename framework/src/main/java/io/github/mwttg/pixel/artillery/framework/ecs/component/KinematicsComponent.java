package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A component to have a state about the kinematics.
 */
public class KinematicsComponent implements Component {

  private final Velocity velocity;
  private boolean inAir;

  public KinematicsComponent() {
    this.velocity = new Velocity(0.0f, 0.0f);
  }

  public Velocity getVelocity() {
    return velocity;
  }

  public boolean isInAir() {
    return inAir;
  }

  public void setInAir(boolean inAir) {
    this.inAir = inAir;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof KinematicsComponent that)) {
      return false;
    }
    return inAir == that.inAir && velocity.equals(that.velocity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(velocity, inAir);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", KinematicsComponent.class.getSimpleName() + "[", "]")
        .add("velocity=" + velocity)
        .add("inAir=" + inAir)
        .toString();
  }
}
