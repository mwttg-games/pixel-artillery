package io.github.mwttg.pixel.artillery.framework.core.render.textured;

import io.github.mwttg.pixel.artillery.framework.core.ShaderFactory;
import io.github.mwttg.pixel.artillery.framework.core.render.MatrixStack;
import org.lwjgl.opengl.GL40;


/**
 * A Renderer for a textured entity.
 */
public final class TexturedRenderer {

  private final int shaderProgramId;
  private final TexturedUniforms uniforms;

  public TexturedRenderer() {
    this.shaderProgramId = ShaderFactory.createTextureShader();
    this.uniforms = new TexturedUniforms(this.shaderProgramId);
  }

  /**
   * Draws a textured entity to the screen, but you can decide which part of the entity by using
   * first and count. This can be used for animations e.g. use 3 times the same plane as geometry
   * data and 3 different texture coordinates for these planes and then use first and count to
   * switch between them.
   *
   * @param matrixStack         the model-, view- and projection-matrix
   * @param vertexArrayObjectId the VAO id (containing VBO of geometry and VBO for texture
   *                            coordinates)
   * @param textureId           the id of the texture
   * @param first               the first data point (geometry and texture) of the entity
   * @param count               how many data point (geometry and texture) to draw the entity
   */
  public void draw(final MatrixStack matrixStack, final int vertexArrayObjectId,
                   final int textureId, final int first, final int count) {
    GL40.glBindVertexArray(vertexArrayObjectId);
    GL40.glUseProgram(shaderProgramId);
    enableVertexAttribArray();

    uniforms.upload(matrixStack, textureId);
    GL40.glDrawArrays(GL40.GL_TRIANGLES, first, count);

    disableVertexAttribArray();
  }

  /**
   * Draws a textured entity to the screen.
   *
   * @param matrixStack         the model-, view- and projection-matrix
   * @param vertexArrayObjectId the VAO id (containing VBO of geometry and VBO for texture
   *                            coordinates)
   * @param textureId           the id of the texture
   * @param size                the size of the geometry data (how many 3D points is the textured
   *                            entity build of?)
   */
  public void draw(final MatrixStack matrixStack, final int vertexArrayObjectId,
                   final int textureId, final int size) {
    draw(matrixStack, vertexArrayObjectId, textureId, 0, size);
  }

  private void enableVertexAttribArray() {
    GL40.glEnableVertexAttribArray(0); // vertices
    GL40.glEnableVertexAttribArray(1); // texture coordinates
  }

  private void disableVertexAttribArray() {
    GL40.glDisableVertexAttribArray(1); // texture coordinates
    GL40.glDisableVertexAttribArray(0); // vertices
  }
}
