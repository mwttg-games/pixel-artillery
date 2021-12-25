package io.github.mwttg.pixel.artillery.framework.entity.position;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A component for an entity to set a position in the game window.
 */
public class Position {

  private final float planeSize;
  private Matrix4f model;
  private Point center = new Point(0.0f, 0.0f);
  private Vector3f vector = new Vector3f(); // mutable Vector for calculations, like alloc memory...

  /**
   * The Constructor.
   *
   * @param modelMatrix the model matrix for the entity (the initial position in the game window)
   * @param planeSize   the plane size of the tile/sprite used for bonding box checks
   */
  public Position(final Matrix4f modelMatrix, final float planeSize) {
    this.model = modelMatrix;
    this.planeSize = planeSize;
    calculateCenter();
    // ToDo set something like a boundary for collision detection?
  }

  public void translate(final float x, final float y, final float z) {
    model.translate(x, y, z, model);
    calculateCenter();
  }

  public Matrix4f getModelMatrix() {
    return model;
  }

  public Vector3f getCoordinates() {
    model.getTranslation(vector);
    return vector;
  }

  // mutable
  private void calculateCenter() {
    model.getTranslation(vector);
    final var centerX = vector.x() - (planeSize / 2.0f);
    final var centerY = vector.y() - (planeSize / 2.0f);
    center.setX(centerX);
    center.setY(centerY);
  }
}
