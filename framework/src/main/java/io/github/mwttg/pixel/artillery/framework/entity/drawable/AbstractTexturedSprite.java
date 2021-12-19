package io.github.mwttg.pixel.artillery.framework.entity.drawable;

import io.github.mwttg.pixel.artillery.common.SpriteData;
import io.github.mwttg.pixel.artillery.framework.graphics.ShaderFactory;
import io.github.mwttg.pixel.artillery.framework.graphics.TextureLoader;
import io.github.mwttg.pixel.artillery.framework.graphics.VertexArrayObject;
import java.util.Objects;
import org.lwjgl.opengl.GL40;

/**
 * An abstract class to bundle the similarities every textured Sprite has.
 */
abstract class AbstractTexturedSprite {

  private final int completeAmountOfPoints;
  private final int vertexArrayObjectId;
  private final int textureId;
  private final int shaderProgramId;
  private final TexturedUniforms uniforms;

  /**
   * This is the Constructor.
   *
   * @param jsonFile    the .json file which defines the Sprite. It includes the geometry (the
   *                    vertices) and the texture coordinates.
   * @param textureFile the .png file of the texture
   */
  AbstractTexturedSprite(final String jsonFile, final String textureFile) {
    final var spriteData = extractSpriteData(jsonFile);
    this.completeAmountOfPoints = spriteData.vertices().length / 3;
    this.vertexArrayObjectId = VertexArrayObject.create(spriteData);
    this.textureId = TextureLoader.createFromResource(textureFile);
    this.shaderProgramId = ShaderFactory.createTextureShader();
    this.uniforms = new TexturedUniforms(this.shaderProgramId);
  }

  /**
   * At the moment there are static Sprites and dynamic Sprites (animations). The similarity of both
   * is: The need of geometry (vertices) and texture coordinates for the render process. Animated
   * Sprites have a different data structure, so we need something to extract the
   * {@link SpriteData}. This something can be defined in the implementation of this method.
   *
   * @param jsonFile the .json file of the Sprite. It must include at least the some data for
   *                 geometry (vertices) and texture coordinates.
   * @return the {@link SpriteData}
   */
  abstract SpriteData extractSpriteData(final String jsonFile);

  void enableVertexAttribArray() {
    GL40.glEnableVertexAttribArray(0); // vertices
    GL40.glEnableVertexAttribArray(1); // texture coordinates
  }

  void disableVertexAttribArray() {
    GL40.glDisableVertexAttribArray(1); // texture coordinates
    GL40.glDisableVertexAttribArray(0); // vertices
  }

  // after this comment all the getters are bundled

  int getCompleteAmountOfPoints() {
    return completeAmountOfPoints;
  }

  int getVertexArrayObjectId() {
    return vertexArrayObjectId;
  }

  int getTextureId() {
    return textureId;
  }

  int getShaderProgramId() {
    return shaderProgramId;
  }

  TexturedUniforms getUniforms() {
    return uniforms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractTexturedSprite that)) {
      return false;
    }
    return vertexArrayObjectId == that.vertexArrayObjectId && textureId == that.textureId
        && shaderProgramId == that.shaderProgramId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(vertexArrayObjectId, textureId, shaderProgramId);
  }
}
