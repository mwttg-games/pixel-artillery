package io.github.mwttg.pixel.artillery.framework.ecs.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.mwttg.pixel.artillery.common.ReadFile;
import io.github.mwttg.pixel.artillery.common.SpriteData;
import io.github.mwttg.pixel.artillery.framework.graphics.TextureLoader;

public class EntityInitializeHelper {

  public static SpriteData getSpriteDataFrom(final String resourceFilename) {
    final var type = new TypeReference<SpriteData>() {
    };
    return ReadFile.jsonFromResources(resourceFilename, type);
  }

  public static int getTextureIdFrom(final String resourceFilename) {
    return TextureLoader.createFromResource(resourceFilename);
  }
}
