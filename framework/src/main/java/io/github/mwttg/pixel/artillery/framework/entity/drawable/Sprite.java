package io.github.mwttg.pixel.artillery.framework.entity.drawable;

import io.github.mwttg.pixel.artillery.common.ReadFile;
import io.github.mwttg.pixel.artillery.common.SpriteData;
import io.github.mwttg.pixel.artillery.framework.graphics.MatrixStack;
import org.lwjgl.opengl.GL40;

/**
 * One of the {@link Drawable} implementations. A static Sprite.
 */
public class Sprite extends AbstractTexturedSprite implements Drawable {

  /**
   * The Constructor. (calls the Constructor of the abstract parent class).
   *
   * @param jsonFile    the .json file which contains data for the geometry (vertices) and texture
   *                    coordinates
   * @param textureFile the .png file for the texture
   */
  public Sprite(final String jsonFile, final String textureFile) {
    super(jsonFile, textureFile);
  }

  @Override
  SpriteData extractSpriteData(final String jsonFile) {
    return ReadFile.jsonFromResources(jsonFile, SpriteData.class);
  }

  /**
   * This draws/renders the {@link Sprite} to the Screen.
   *
   * @param matrixStack the {@link MatrixStack} including model-, view- and perspective matrix
   */
  @Override
  public void draw(final MatrixStack matrixStack) {
    GL40.glBindVertexArray(getVertexArrayObjectId());
    GL40.glUseProgram(getShaderProgramId());
    enableVertexAttribArray();

    getUniforms().upload(matrixStack, getTextureId());
    GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, getCompleteAmountOfPoints());

    disableVertexAttribArray();
  }
}
