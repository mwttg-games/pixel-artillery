package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class CollisionGridComponent implements Component {

  private final List<List<Integer>> grid;

  public CollisionGridComponent(final List<List<Integer>> grid) {
    this.grid = grid;
  }

  public BlockedDirections getBlockedNeighbours(final int x, final int y) {
    final var top = y - 1 >= grid.size() ? true : grid.get(y + 1).get(x) != 0;
    final var right = x - 1 >= grid.get(0).size() ? true : grid.get(y).get(x + 1) != 0;
    final var bottom = y <= 1 ? true : grid.get(y - 1).get(x) != 0;
    final var left = x <= 1 ? true : grid.get(y).get(x-1) != 0;

    return new BlockedDirections(top, right, bottom, left);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CollisionGridComponent that)) {
      return false;
    }
    return grid.equals(that.grid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(grid);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CollisionGridComponent.class.getSimpleName() + "[", "]")
        .add("grid=" + grid)
        .toString();
  }
}
