package io.github.mwttg.pixel.artillery.framework.core.sprites;

import io.github.mwttg.pixel.artillery.common.ReadFile;
import io.github.mwttg.pixel.artillery.common.SpriteData;
import io.github.mwttg.pixel.artillery.framework.core.TextureLoader;
import io.github.mwttg.pixel.artillery.framework.core.VertexArrayObject;

/**
 * Data class for a sprite to bundle all needed data.
 */
public record Sprite(int vertexArrayObjectId, int textureId, int size) {

  /**
   * Creates a Sprite (OpenGL ids & size) from a .json file and a texture file (.png).
   *
   * @param jsonFile the .json file inside the resource folder
   * @param textureFile the image (.png) file inside the resource folder
   * @return the data class
   */
  public static Sprite fromResources(final String jsonFile, final String textureFile) {
    final var spriteData = ReadFile.jsonFromResources(jsonFile, SpriteData.class);
    final var vertexArrayObjectId = VertexArrayObject.create(spriteData);
    final var size = spriteData.vertices().length / 3;
    final var textureId = TextureLoader.createFromResource(textureFile);

    return new Sprite(vertexArrayObjectId, textureId, size);
  }
}
