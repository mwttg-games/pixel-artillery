package io.github.mwttg.pixel.artillery.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.StringJoiner;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A bounding box (a rectangle), defined by two points ({@link Point}s).
 */
public class BoundingBox {

  private Point bottomLeft;
  private Point topRight;

  @JsonIgnore
  private Vector3f vector = new Vector3f(); // mutable Vector for calculations, like alloc memory...
  @JsonIgnore
  private float planeSize;

  // for jackson json stuff
  public BoundingBox() {
  }

  /**
   * A Constructor.
   *
   * @param bottomLeft the bottom left point
   * @param topRight the top right point
   */
  public BoundingBox(final Point bottomLeft, final Point topRight) {
    this.bottomLeft = bottomLeft;
    this.topRight = topRight;
    this.planeSize = Math.abs(bottomLeft.getX() - topRight.getX());
  }

  /**
   * A Constructor.
   *
   * @param modelMatrix the model matrix of an entity
   * @param planeSize the plane size of that entity
   */
  public BoundingBox(final Matrix4f modelMatrix, final float planeSize) {
    modelMatrix.getTranslation(vector);
    this.bottomLeft = new Point(vector.x(), vector.y());
    this.topRight = new Point(vector.x() + planeSize, vector.y() + planeSize);
    this.planeSize = planeSize;
  }


  /**
   * Calculates the center of the {@link BoundingBox}.
   *
   * @return the 2D Coordinate {@link Point} of the center
   */
  @JsonIgnore
  public Point getCenter() {
    return new Point(bottomLeft.getX() + (planeSize / 2.0f),
        bottomLeft.getY() + (planeSize / 2.0f));
  }

  /**
   *  A mutable method for translating the {@link BoundingBox}.
   *
   * @param modelMatrix the model matrix of the entiy
   */
  public void translate(final Matrix4f modelMatrix) {
    modelMatrix.getTranslation(vector);
    bottomLeft.setX(vector.x());
    bottomLeft.setY(vector.y());
    topRight.setX(vector.x() + planeSize);
    topRight.setY(vector.y() + planeSize);
  }

  /**
   * Check if this {@link BoundingBox} intersects with another {@link BoundingBox}.
   *
   * @param other the other {@link BoundingBox}
   * @return true/false
   */
  @SuppressWarnings("RedundantIfStatement") // for better human readability
  public boolean intersect(final BoundingBox other) {
    if (bottomLeft.getX() >= other.getTopRight().getX()
        || topRight.getX() <= other.bottomLeft.getX()) {
      return false;
    } else if (bottomLeft.getY() >= other.topRight.getY()
        || topRight.getY() <= other.getBottomLeft().getY()) {
      return false;
    } else {
      return true;
    }
  }

  public Point getBottomLeft() {
    return bottomLeft;
  }

  public Point getTopRight() {
    return topRight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BoundingBox that)) {
      return false;
    }
    return Float.compare(that.planeSize, planeSize) == 0
        && bottomLeft.equals(that.bottomLeft) && topRight.equals(that.topRight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bottomLeft, topRight, planeSize);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BoundingBox.class.getSimpleName() + "[", "]")
        .add("bottomLeft=" + bottomLeft)
        .add("topRight=" + topRight)
        .add("planeSize=" + planeSize)
        .toString();
  }
}
