package io.github.mwttg.pixel.artillery.framework.entity.boundary.quadtree;

/**
 * A bounding box (a rectangle), defined by two points ({@link Position}s).
 */
public record BoundingBox(Position bottomLeft, Position topRight) {

  /**
   * Calculates the center of the {@link BoundingBox}.
   *
   * @return the 2D Coordinate {@link Position} of the center
   */
  public Position getCenter() {
    final var x = bottomLeft.x() + ((topRight.y() - bottomLeft.x()) / 2.0f);
    final var y = bottomLeft.y() + ((topRight.y() - bottomLeft.y()) / 2.0f);
    return new Position(x, y);
  }


  /**
   * Check if this {@link BoundingBox} intersects with another {@link BoundingBox}.
   *
   * @param other the other {@link BoundingBox}
   * @return true/false
   */
  @SuppressWarnings("RedundantIfStatement") // for better human readability
  public boolean intersect(final BoundingBox other) {
    if (bottomLeft.x() > other.topRight().x() || topRight.x() < other.bottomLeft.x()) {
      return false;
    } else if (bottomLeft.y() > other.topRight.y() || topRight.y() < other.bottomLeft().y()) {
      return false;
    } else {
      return true;
    }
  }
}
