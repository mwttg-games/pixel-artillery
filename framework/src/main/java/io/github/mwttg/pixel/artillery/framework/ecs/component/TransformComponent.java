package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.Objects;
import java.util.StringJoiner;
import org.joml.Matrix4f;

/**
 * A component to hold the position, rotation and scaling.
 */
public class TransformComponent implements Component {

  private Matrix4f modelMatrix;

  public TransformComponent(final Matrix4f modelMatrix) {
    this.modelMatrix = modelMatrix;
  }

  // getter

  public Matrix4f getModelMatrix() {
    return modelMatrix;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransformComponent that)) {
      return false;
    }
    return modelMatrix.equals(that.modelMatrix);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modelMatrix);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", TransformComponent.class.getSimpleName() + "[", "]")
        .add("modelMatrix=" + modelMatrix)
        .toString();
  }
}
