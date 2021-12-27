package io.github.mwttg.pixel.artillery.framework.entity.position;

import io.github.mwttg.pixel.artillery.common.BoundingBox;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A component for an entity to set a position in the game window.
 * ToDo Think about splitting Position and Bounding Box.
 */
public class Position {

  private Matrix4f model; // mutable
  private BoundingBox boundingBox; // mutable

  /**
   * The Constructor.
   *
   * @param modelMatrix the model matrix for the entity (the initial position in the game window)
   * @param planeSize   the plane size of the tile/sprite used for bonding box checks
   */
  public Position(final Matrix4f modelMatrix, final float planeSize) {
    this.model = modelMatrix;

    Vector3f vector = new Vector3f();
    modelMatrix.getTranslation(vector);
    this.boundingBox = new BoundingBox(modelMatrix, planeSize);
  }

  public void translate(final float x, final float y, final float z) {
    model.translate(x, y, z, model);
    boundingBox.translate(model);
  }

  public Matrix4f getModelMatrix() {
    return model;
  }

  public BoundingBox getBoundingBox() {
    return boundingBox;
  }
}
