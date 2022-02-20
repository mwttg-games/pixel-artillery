package io.github.mwttg.pixel.artillery.examples.example06;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.mwttg.pixel.artillery.common.ReadFile;
import io.github.mwttg.pixel.artillery.common.TilesFactory;
import io.github.mwttg.pixel.artillery.framework.ecs.component.BlockedDirections;
import io.github.mwttg.pixel.artillery.framework.ecs.entity.EntityInitializeHelper;
import io.github.mwttg.pixel.artillery.framework.ecs.entity.LevelTilesEntity;
import io.github.mwttg.pixel.artillery.framework.ecs.entity.PlayerEntity;
import java.util.List;
import org.joml.Matrix4f;

public class EntityFactory {

  public static LevelTilesEntity createLevelTilesEntity() {
    final var spriteData =
        EntityInitializeHelper.getSpriteDataFrom("files/level/level3/level.json");
    final var textureId = EntityInitializeHelper.getTextureIdFrom("files/level/texture-atlas.png");

    final var grid = readFrom("files/level/level3/level-grid.json");

    return new LevelTilesEntity(spriteData.vertices(), spriteData.textureCoordinates(), textureId,
        grid);
  }

  public static PlayerEntity createPlayerEntity() {
    final var modelMatrix = new Matrix4f().translate(5.0f, 5.0f, 0.0f);
    final var spriteData = TilesFactory.createTile(1.0f, 1.0f);
    final var textureId = EntityInitializeHelper.getTextureIdFrom("files/player/blob/player.png");
    final var blockedDirections = new BlockedDirections(false, false, false, false);

    return new PlayerEntity(spriteData.vertices(), spriteData.textureCoordinates(), textureId,
        modelMatrix, blockedDirections);
  }

  private static List<List<Integer>> readFrom(final String filename) {
    final var type = new TypeReference<List<List<Integer>>>() {
    };
    return ReadFile.jsonFromResources(filename, type);
  }
}
