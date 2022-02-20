package io.github.mwttg.pixel.artillery.framework.ecs.system;

import io.github.mwttg.pixel.artillery.framework.ecs.GameConfiguration;
import io.github.mwttg.pixel.artillery.framework.ecs.component.InputComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.KinematicsComponent;

public class KinematicSystem {

  public static void update(final KinematicsComponent kinematicsComponent,
                            final InputComponent inputComponent,
                            final GameConfiguration configuration) {
    if (!inputComponent.isMoveLeft() && !inputComponent.isMoveRight()) {
      kinematicsComponent.getVelocity().setHorizontal(0.0f);
    }

    if (inputComponent.isMoveRight() && !inputComponent.isMoveLeft()) {
      kinematicsComponent.getVelocity().setHorizontal(configuration.getPlayerWalkVelocity());
    }

    if (inputComponent.isMoveLeft() && !inputComponent.isMoveRight()) {
      kinematicsComponent.getVelocity().setHorizontal(-configuration.getPlayerWalkVelocity());
    }

    if (inputComponent.isJump() && !kinematicsComponent.isInAir()) {
      kinematicsComponent.getVelocity().setVertical(configuration.getPlayerInitJumpVelocity());
      kinematicsComponent.setInAir(true);
    }
  }
}
