package io.github.mwttg.pixel.artillery.framework.ecs.entity;

import io.github.mwttg.pixel.artillery.framework.ecs.component.CollisionGridComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.RenderComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.TransformComponent;
import java.util.List;
import org.joml.Matrix4f;

public class LevelTilesEntity extends Entity {

  private final RenderComponent renderComponent;
  private final TransformComponent transformComponent;
  private final CollisionGridComponent collisionGridComponent;
  // TODO BoundingBox component?

  public LevelTilesEntity(final float[] vertices, final float[] textureCoordinates,
                          final int textureId, final List<List<Integer>> grid) {
    this.renderComponent = new RenderComponent(vertices, textureCoordinates, textureId);
    this.transformComponent = new TransformComponent(new Matrix4f().translate(0.0f, 0.0f, -1.0f));
    this.collisionGridComponent = new CollisionGridComponent(grid);
  }

  public RenderComponent getRenderComponent() {
    return renderComponent;
  }

  public TransformComponent getTransformComponent() {
    return transformComponent;
  }

  public CollisionGridComponent getCollisionGridComponent() {
    return collisionGridComponent;
  }
}
