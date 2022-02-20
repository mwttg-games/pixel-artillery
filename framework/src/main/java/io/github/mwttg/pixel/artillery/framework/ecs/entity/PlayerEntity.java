package io.github.mwttg.pixel.artillery.framework.ecs.entity;

import io.github.mwttg.pixel.artillery.framework.ecs.component.BlockedDirections;
import io.github.mwttg.pixel.artillery.framework.ecs.component.BlockedDirectionsComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.InputComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.KinematicsComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.RenderComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.TransformComponent;
import org.joml.Matrix4f;

public class PlayerEntity extends Entity {

  private final TransformComponent transformComponent;
  private final RenderComponent renderComponent;
  private final InputComponent inputComponent;
  private final BlockedDirectionsComponent blockedDirectionsComponent;
  private final KinematicsComponent kinematicsComponent;

  public PlayerEntity(final float[] vertices,
                      final float[] textureCoordinates,
                      final int textureId,
                      final Matrix4f modelMatrix,
                      final BlockedDirections blockedDirections) {
    this.transformComponent = new TransformComponent(modelMatrix);
    this.renderComponent = new RenderComponent(vertices, textureCoordinates, textureId);
    this.inputComponent = new InputComponent();
    this.blockedDirectionsComponent = new BlockedDirectionsComponent(blockedDirections);
    this.kinematicsComponent = new KinematicsComponent();
  }

  public TransformComponent getTransformComponent() {
    return transformComponent;
  }

  public RenderComponent getRenderComponent() {
    return renderComponent;
  }

  public InputComponent getInputComponent() {
    return inputComponent;
  }

  public BlockedDirectionsComponent getBlockedDirectionsComponent() {
    return blockedDirectionsComponent;
  }
  public KinematicsComponent getKinematicsComponent() {
    return kinematicsComponent;
  }
}
