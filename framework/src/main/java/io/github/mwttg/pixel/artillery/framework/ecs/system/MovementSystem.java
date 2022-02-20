package io.github.mwttg.pixel.artillery.framework.ecs.system;

import io.github.mwttg.pixel.artillery.framework.ecs.GameConfiguration;
import io.github.mwttg.pixel.artillery.framework.ecs.component.BlockedDirectionsComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.KinematicsComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.TransformComponent;

public class MovementSystem {

  public static void update(final TransformComponent transformComponent,
                            final KinematicsComponent kinematicsComponent,
                            final BlockedDirectionsComponent blockedDirectionsComponent,
                            final float deltaTime,
                            final GameConfiguration gameConfiguration) {
    var deltaX = getDeltaX(kinematicsComponent, deltaTime);
    var deltaY = getDeltaY(kinematicsComponent, gameConfiguration, deltaTime);
    float newVerticalVelocity = 0.0f;
    if (kinematicsComponent.isInAir()) {
      newVerticalVelocity =
          getNewVerticalVelocity(kinematicsComponent, gameConfiguration, deltaTime);
    }

    // blocked directions // TODO position corrections (snap to grid)
    final var blocked = blockedDirectionsComponent.getBlockedDirections();
    if (deltaX < 0 && blocked.isLeft()) {
      deltaX = 0.0f;
    }
    if (deltaX > 0 && blocked.isRight()) {
      deltaX = 0.0f;
    }
    if (deltaY > 0 && blocked.isTop()) {
      deltaY = 0.0f;
      newVerticalVelocity = 0.0f;
    }
    if (deltaY < 0 && blocked.isBottom() && kinematicsComponent.isInAir()) {
      deltaY = 0.0f;
      newVerticalVelocity = 0.0f;
      kinematicsComponent.setInAir(false);
    }

    if (deltaY == 0.0 && !blocked.isBottom()) {
      kinematicsComponent.setInAir(true);
    }
    // end

    kinematicsComponent.getVelocity().setVertical(newVerticalVelocity);
    transformComponent.getModelMatrix().translate(deltaX, deltaY, 0.0f);
  }

  private static float getNewVerticalVelocity(final KinematicsComponent component,
                                              final GameConfiguration config,
                                              final float deltaTime) {
    final var gravity = getGravity(component, config);
    return (-gravity * deltaTime) + component.getVelocity().getVertical();
  }

  private static float getDeltaX(final KinematicsComponent component,
                                 final float deltaTime) {
    return component.getVelocity().getHorizontal() * deltaTime;
  }

  private static float getDeltaY(final KinematicsComponent component,
                                 final GameConfiguration config,
                                 final float deltaTime) {
    final var gravity = getGravity(component, config);
    if (component.isInAir()) {
      return (-gravity * deltaTime * deltaTime) +
          (component.getVelocity().getVertical() * deltaTime);
    } else {
      return 0.0f;
    }
  }

  private static float getGravity(final KinematicsComponent component,
                                  final GameConfiguration config) {
    if (component.getVelocity().getVertical() >= 0) {
      return config.getPlayerJumpGravity();
    } else {
      return config.getPlayerFallGravity();
    }
  }
}
