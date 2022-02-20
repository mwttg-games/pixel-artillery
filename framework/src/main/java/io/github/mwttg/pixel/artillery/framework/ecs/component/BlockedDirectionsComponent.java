package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.Objects;
import java.util.StringJoiner;

public class BlockedDirectionsComponent implements Component {

  private BlockedDirections blockedDirections;

  public BlockedDirectionsComponent(final BlockedDirections blockedDirections) {
    this.blockedDirections = blockedDirections;
  }

  public BlockedDirections getBlockedDirections() {
    return blockedDirections;
  }

  public void setBlockedDirections(final BlockedDirections blockedDirections) {
    this.blockedDirections = blockedDirections;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BlockedDirectionsComponent that)) {
      return false;
    }
    return blockedDirections.equals(that.blockedDirections);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockedDirections);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BlockedDirectionsComponent.class.getSimpleName() + "[",
        "]")
        .add("blockedDirections=" + blockedDirections)
        .toString();
  }
}
