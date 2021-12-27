package io.github.mwttg.pixel.artillery.framework.entity.boundary.grid;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.mwttg.pixel.artillery.common.BoundingBox;
import io.github.mwttg.pixel.artillery.common.Point;
import io.github.mwttg.pixel.artillery.common.ReadFile;
import java.util.List;
import java.util.Optional;

/**
 * A component for an entity. Can be used to have a model for walkable and solid areas of a level.
 * Simple boundary bix checks.
 */
public class Grid {

  private final List<List<Optional<BoundingBox>>> grid;

  /**
   * The Constructor.
   *
   * @param filename the filename of the json file (generated with the tool GridGenerator).
   */
  public Grid(final String filename) {
    final var type = new TypeReference<List<List<Optional<BoundingBox>>>>() {
    };
    this.grid = ReadFile.jsonFromResources(filename, type);
  }

  /**
   * Checks if a {@link BoundingBox} is colliding with the Grid.
   *
   * @param currentCenter used for getting the grid coordinates (normally the center of an entity)
   * @param boundingBox   the bounding box which should be checked
   * @return the directions which are blocked
   */
  public BlockedDirections checkCollision(final Point currentCenter,
                                          final BoundingBox boundingBox) {
    final var neighbours = getNeighbours(currentCenter);

    var left = false;
    if (neighbours.left().isPresent()) {
      left = neighbours.left().get().intersect(boundingBox);
    }

    var top = false;
    if (neighbours.top().isPresent()) {
      top = neighbours.top().get().intersect(boundingBox);
    }

    var right = false;
    if (neighbours.right().isPresent()) {
      right = neighbours.right().get().intersect(boundingBox);
    }

    var bottom = false;
    if (neighbours.bottom().isPresent()) {
      bottom = neighbours.bottom().get().intersect(boundingBox);
    }

    return new BlockedDirections(left, top, right, bottom);
  }

  private Neighbours getNeighbours(final Point point) {
    final var x = (int) point.getX();
    final var y = (int) point.getY();

    final var topLeft = grid.get(y + 1).get(x - 1);
    final var top = grid.get(y + 1).get(x);
    final var topRight = grid.get(y + 1).get(x + 1);
    final var left = grid.get(y).get(x - 1);
    final var right = grid.get(y).get(x + 1);
    final var bottomLeft = grid.get(y - 1).get(x - 1);
    final var bottom = grid.get(y - 1).get(x);
    final var bottomRight = grid.get(y - 1).get(x + 1);

    return new Neighbours(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight);
  }
}
