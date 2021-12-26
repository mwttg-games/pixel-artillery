package io.github.mwttg.pixel.artillery.framework.entity.position;

import io.github.mwttg.pixel.artillery.common.Point;
import io.github.mwttg.pixel.artillery.framework.entity.boundary.quadtree.BoundingBox;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A component for an entity to set a position in the game window.
 */
public class Position {

  private final float planeSize;
  private Matrix4f model;
  private Vector3f vector = new Vector3f(); // mutable Vector for calculations, like alloc memory...
  private BoundingBox boundingBox;

  /**
   * The Constructor.
   *
   * @param modelMatrix the model matrix for the entity (the initial position in the game window)
   * @param planeSize   the plane size of the tile/sprite used for bonding box checks
   */
  public Position(final Matrix4f modelMatrix, final float planeSize) {
    this.model = modelMatrix;
    this.planeSize = planeSize;
    calculateBoundingBox();
  }

  public void translate(final float x, final float y, final float z) {
    model.translate(x, y, z, model);
    calculateBoundingBox();
  }

  public Matrix4f getModelMatrix() {
    return model;
  }

  public BoundingBox getBoundingBox() {
    return boundingBox;
  }

  // mutable
  private void calculateBoundingBox() {
    model.getTranslation(vector);
    boundingBox = new BoundingBox(
        new Point(vector.x() + 0.25f, vector.y()),
        new Point(vector.x() + planeSize - 0.25f, vector.y() + planeSize - 0.65f));
  }
}
