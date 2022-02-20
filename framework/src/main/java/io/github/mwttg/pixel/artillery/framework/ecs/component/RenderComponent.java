package io.github.mwttg.pixel.artillery.framework.ecs.component;

import io.github.mwttg.pixel.artillery.framework.entity.drawable.TexturedUniforms;
import io.github.mwttg.pixel.artillery.framework.graphics.ShaderFactory;
import io.github.mwttg.pixel.artillery.framework.graphics.VertexArrayObject;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * A component that holds everything to render a static sprite.
 */
public class RenderComponent implements Component {

  private final int vertexArrayObjectId;
  private final int textureId;
  private final int shaderProgramId;
  private final TexturedUniforms uniforms;
  private final int amountOfPoints;

  public RenderComponent(final float[] vertices,
                         final float[] textureCoordinates,
                         final int textureId) {
    this.amountOfPoints = vertices.length / 3;
    this.vertexArrayObjectId = VertexArrayObject.create(vertices, textureCoordinates);
    this.textureId = textureId;
    this.shaderProgramId = ShaderFactory.createTextureShader();
    this.uniforms = new TexturedUniforms(this.shaderProgramId);
  }

  // getter

  public int getAmountOfPoints() {
    return amountOfPoints;
  }

  public int getVertexArrayObjectId() {
    return vertexArrayObjectId;
  }

  public int getTextureId() {
    return textureId;
  }

  public int getShaderProgramId() {
    return shaderProgramId;
  }

  public TexturedUniforms getUniforms() {
    return uniforms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RenderComponent that)) {
      return false;
    }
    return vertexArrayObjectId == that.vertexArrayObjectId && textureId == that.textureId &&
        shaderProgramId == that.shaderProgramId && amountOfPoints == that.amountOfPoints &&
        uniforms.equals(that.uniforms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vertexArrayObjectId, textureId, shaderProgramId, uniforms, amountOfPoints);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RenderComponent.class.getSimpleName() + "[", "]")
        .add("vertexArrayObjectId=" + vertexArrayObjectId)
        .add("textureId=" + textureId)
        .add("shaderProgramId=" + shaderProgramId)
        .add("uniforms=" + uniforms)
        .add("amountOfPoints=" + amountOfPoints)
        .toString();
  }
}
