package io.github.mwttg.pixel.artillery.framework.entity.boundary.quadtree;

import io.github.mwttg.pixel.artillery.common.Point;

/**
 * A bounding box (a rectangle), defined by two points ({@link Point}s).
 */
public record BoundingBox(Point bottomLeft, Point topRight) {

  /**
   * Calculates the center of the {@link BoundingBox}.
   *
   * @return the 2D Coordinate {@link Point} of the center
   */
  public Point getCenter() {
    final var x = bottomLeft.getX() + ((topRight.getY() - bottomLeft.getX()) / 2.0f);
    final var y = bottomLeft.getY() + ((topRight.getY() - bottomLeft.getY()) / 2.0f);
    return new Point(x, y);
  }


  /**
   * Check if this {@link BoundingBox} intersects with another {@link BoundingBox}.
   *
   * @param other the other {@link BoundingBox}
   * @return true/false
   */
  @SuppressWarnings("RedundantIfStatement") // for better human readability
  public boolean intersect(final BoundingBox other) {
    if (bottomLeft.getX() > other.topRight().getX() || topRight.getX() < other.bottomLeft.getX()) {
      return false;
    } else if (bottomLeft.getY() > other.topRight.getY() ||
        topRight.getY() < other.bottomLeft().getY()) {
      return false;
    } else {
      return true;
    }
  }
}
