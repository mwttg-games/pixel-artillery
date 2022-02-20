package io.github.mwttg.pixel.artillery.examples.example06;

import io.github.mwttg.pixel.artillery.framework.ecs.GameConfiguration;
import io.github.mwttg.pixel.artillery.framework.ecs.component.CollisionGridComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.entity.PlayerEntity;
import io.github.mwttg.pixel.artillery.framework.ecs.system.BlockedDirectionsSystem;
import io.github.mwttg.pixel.artillery.framework.ecs.system.KinematicSystem;
import io.github.mwttg.pixel.artillery.framework.ecs.system.MovementSystem;
import io.github.mwttg.pixel.artillery.framework.ecs.system.RenderSystem;
import io.github.mwttg.pixel.artillery.framework.ecs.system.UserInputSystem;
import org.joml.Matrix4f;

public class Player {

  private final PlayerEntity entity;

  public Player(final PlayerEntity playerEntity) {
    this.entity = playerEntity;
  }

  public void update(final long gameWindowId,
                     final float deltaTime,
                     final CollisionGridComponent levelGridComponent,
                     final GameConfiguration configuration) {
    UserInputSystem.process(
        entity.getInputComponent(),
        gameWindowId);
    KinematicSystem.update(
        entity.getKinematicsComponent(),
        entity.getInputComponent(),
        configuration);
    BlockedDirectionsSystem.update(
        entity.getBlockedDirectionsComponent(),
        entity.getTransformComponent(),
        levelGridComponent
    );
    MovementSystem.update(
        entity.getTransformComponent(),
        entity.getKinematicsComponent(),
        entity.getBlockedDirectionsComponent(),
        deltaTime,
        configuration);
  }

  public void render(final Matrix4f viewMatrix,
                     final Matrix4f projectionMatrix) {
    RenderSystem.draw(
        entity.getRenderComponent(),
        entity.getTransformComponent(),
        viewMatrix,
        projectionMatrix);
  }
}
