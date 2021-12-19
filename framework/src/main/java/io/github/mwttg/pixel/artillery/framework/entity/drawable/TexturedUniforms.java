package io.github.mwttg.pixel.artillery.framework.entity.drawable;

import io.github.mwttg.pixel.artillery.framework.graphics.MatrixStack;
import java.util.Map;

/**
 * Unit for uploading uniforms for the textured shader program.
 */
class TexturedUniforms implements Uniform {

  private final Map<String, Integer> locations;

  TexturedUniforms(final int shaderProgramId) {
    this.locations = initializeLocations(shaderProgramId);
  }

  void upload(final MatrixStack matrixStack, final int textureId) {
    uploadMatrixStack(locations, matrixStack);
    activateTexture0(locations, textureId);
  }

  private Map<String, Integer> initializeLocations(int shaderProgramId) {
    return Map.ofEntries(
        createLocationFor(shaderProgramId, Location.MODEL_MATRIX),
        createLocationFor(shaderProgramId, Location.VIEW_MATRIX),
        createLocationFor(shaderProgramId, Location.PROJECTION_MATRIX),
        createLocationFor(shaderProgramId, Location.TEXTURE_SAMPLER));
  }
}
