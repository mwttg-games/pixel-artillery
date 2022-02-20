package io.github.mwttg.pixel.artillery.framework.entity.drawable;

import java.util.Map;
import org.joml.Matrix4f;

/**
 * Unit for uploading uniforms for the textured shader program.
 */
public class TexturedUniforms implements Uniform {

  private final Map<String, Integer> locations;

  public TexturedUniforms(final int shaderProgramId) {
    this.locations = initializeLocations(shaderProgramId);
  }

  public void upload(final Matrix4f model, final Matrix4f view, final Matrix4f projection,
              final int textureId) {
    uploadModelMatrix(locations, model);
    uploadViewMatrix(locations, view);
    uploadProjectionMatrix(locations, projection);
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
