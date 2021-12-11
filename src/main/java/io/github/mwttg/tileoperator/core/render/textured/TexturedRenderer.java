package io.github.mwttg.tileoperator.core.render.textured;

import io.github.mwttg.tileoperator.core.ShaderFactory;
import io.github.mwttg.tileoperator.core.render.MatrixStack;
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
    GL40.glBindVertexArray(vertexArrayObjectId);
    GL40.glUseProgram(shaderProgramId);
    enableVertexAttribArray();

    uniforms.upload(matrixStack, textureId);
    GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, size);

    disableVertexAttribArray();
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
