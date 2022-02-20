package io.github.mwttg.pixel.artillery.framework.ecs.system;

import io.github.mwttg.pixel.artillery.framework.ecs.component.BlockedDirections;
import io.github.mwttg.pixel.artillery.framework.ecs.component.BlockedDirectionsComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.CollisionGridComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.TransformComponent;
import org.joml.Vector3f;

/**
 *
 */
public class BlockedDirectionsSystem {

  public static void update(final BlockedDirectionsComponent blockedDirectionsComponent,
                            final TransformComponent transformComponent,
                            final CollisionGridComponent collisionGridComponent) {
    final var position = new Vector3f();
    transformComponent.getModelMatrix().getTranslation(position);
    final var x = position.x() + 0.5f;
    final var y = position.y() + 0.5f;

    final var blocks = new BlockedDirections(false, false, false, false);

    final var leftTopBottomBlocks =
        collisionGridComponent.getBlockedNeighbours((int) (x - 0.4f), (int) y);
    blocks.mergeTopDownWith(leftTopBottomBlocks);

    final var rightTopBottomBlocks =
        collisionGridComponent.getBlockedNeighbours((int) (x + 0.4f), (int) y);
    blocks.mergeTopDownWith(rightTopBottomBlocks);

    final var topLeftRightBlocks =
        collisionGridComponent.getBlockedNeighbours((int) x, (int) (y + 0.4f));
    blocks.mergeLeftRight(topLeftRightBlocks);

    final var bottomLeftRightBlocks =
        collisionGridComponent.getBlockedNeighbours((int) x, (int) (y - 0.4f));
    blocks.mergeLeftRight(bottomLeftRightBlocks);

    final var xFracPart = getFracPart(x);
    final var yFracPart = getFracPart(y);

    final var blockedLeft = blocks.isLeft() && xFracPart < 0.6f;
    final var blockedRight = blocks.isRight() && xFracPart > 0.4f;
    final var blockedTop = blocks.isTop() && yFracPart > 0.4f;
    final var blockedBottom = blocks.isBottom() && yFracPart < 0.6f;

    final var newBlockedDirections =
        new BlockedDirections(blockedTop, blockedRight, blockedBottom, blockedLeft);

    blockedDirectionsComponent.setBlockedDirections(newBlockedDirections);
  }

  private static float getFracPart(final float number) {
    final var intPart = (int) number;
    return number - intPart;
  }

  public static void updateOld(final BlockedDirectionsComponent blockedDirectionsComponent,
                             final TransformComponent transformComponent,
                             final CollisionGridComponent collisionGridComponent) {
    final var vector = new Vector3f();
    transformComponent.getModelMatrix().getTranslation(vector);
    final var x = vector.x() + 0.5f;
    final var y = vector.y() + 0.5f;

    final var blockedDirections = collisionGridComponent.getBlockedNeighbours((int) x, (int) y);

    final var xIntPart = (int) x;
    final var xFracPart = x - xIntPart;

    final var yIntPart = (int) y;
    final var yFracPart = y - yIntPart;

    final boolean blockedLeft = blockedDirections.isLeft() && xFracPart < 0.6f;
    final boolean blockedRight = blockedDirections.isRight() && xFracPart > 0.4f;

    final boolean blockedTop = blockedDirections.isTop() && yFracPart > 0.4f;
    final boolean blockedBottom = blockedDirections.isBottom() && yFracPart < 0.6f;

    final var newBlockedDirections =
        new BlockedDirections(blockedTop, blockedRight, blockedBottom, blockedLeft);

    blockedDirectionsComponent.setBlockedDirections(newBlockedDirections);
  }
}
