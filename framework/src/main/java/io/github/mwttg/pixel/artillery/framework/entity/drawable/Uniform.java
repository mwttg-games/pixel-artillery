package io.github.mwttg.pixel.artillery.framework.entity.drawable;

import java.nio.FloatBuffer;
import java.util.Map;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL40;

/**
 * Handle Uniforms (activate / upload).
 */
interface Uniform {

  int CAPACITY = 16;
  FloatBuffer MATRIX_BUFFER = BufferUtils.createFloatBuffer(CAPACITY);

  /**
   * Activates texture unit 0.
   *
   * @param locations the locations initialized in the implementation class
   * @param textureId the texture id
   */
  default void activateTexture0(final Map<String, Integer> locations, final int textureId) {
    final var location = locations.get(Location.TEXTURE_SAMPLER);
    GL40.glUniform1i(location, 0);
    GL40.glActiveTexture(GL40.GL_TEXTURE0);
    GL40.glBindTexture(GL40.GL_TEXTURE_2D, textureId);
  }

  /**
   * Uploads the model matrix.
   *
   * @param locations   the locations initialized in the implementation class
   * @param modelMatrix the model matrix
   */
  default void uploadModelMatrix(final Map<String, Integer> locations, final Matrix4f modelMatrix) {
    final var location = locations.get(Location.MODEL_MATRIX);
    final var buffer = modelMatrix.get(MATRIX_BUFFER);
    GL40.glUniformMatrix4fv(location, false, buffer);
  }

  /**
   * Uploads the view matrix (the camera).
   *
   * @param locations  the locations initialized in the implementation class
   * @param viewMatrix the view matrix
   */
  default void uploadViewMatrix(final Map<String, Integer> locations, final Matrix4f viewMatrix) {
    final var location = locations.get(Location.VIEW_MATRIX);
    final var buffer = viewMatrix.get(MATRIX_BUFFER);
    GL40.glUniformMatrix4fv(location, false, buffer);
  }

  /**
   * Uploads the projection matrix.
   *
   * @param locations        the locations initialized in the implementation class
   * @param projectionMatrix the projection matrix
   */
  default void uploadProjectionMatrix(final Map<String, Integer> locations,
                                      final Matrix4f projectionMatrix) {
    final var location = locations.get(Location.PROJECTION_MATRIX);
    final var buffer = projectionMatrix.get(MATRIX_BUFFER);
    GL40.glUniformMatrix4fv(location, false, buffer);
  }

  /**
   * Creates a location (on the graphic device memory) for a special property (for a special shader
   * program).
   *
   * @param shaderProgramId the shader program id
   * @param name            the name of the location (see: {@link Location})
   * @return a map entry of the location name and the location id
   */
  default Map.Entry<String, Integer> createLocationFor(final int shaderProgramId,
                                                       final String name) {
    final var locationId = GL40.glGetUniformLocation(shaderProgramId, name);
    return Map.entry(name, locationId);
  }
}
