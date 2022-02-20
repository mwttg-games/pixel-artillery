package io.github.mwttg.pixel.artillery.examples.example06;

import io.github.mwttg.pixel.artillery.framework.ecs.component.CollisionGridComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.entity.LevelTilesEntity;
import io.github.mwttg.pixel.artillery.framework.ecs.system.RenderSystem;
import org.joml.Matrix4f;

public class Level {

  private final LevelTilesEntity levelTiles;

  public Level(final LevelTilesEntity levelTilesEntity) {
    this.levelTiles = levelTilesEntity;
  }

  public void render(final Matrix4f viewMatrix, final Matrix4f projectionMatrix) {
    RenderSystem.draw(
        levelTiles.getRenderComponent(),
        levelTiles.getTransformComponent(),
        viewMatrix,
        projectionMatrix);
  }

  public CollisionGridComponent getCollisionGrid() {
    return levelTiles.getCollisionGridComponent();
  }
}
