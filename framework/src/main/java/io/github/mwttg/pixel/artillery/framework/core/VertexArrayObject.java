package io.github.mwttg.pixel.artillery.framework.core;

import io.github.mwttg.pixel.artillery.framework.CleanUpUtilities;
import org.lwjgl.opengl.GL40;

/**
 * A vertex array object which holds the vertex buffer objects (ids) which are needed for rendering
 * an object.
 */
public final class VertexArrayObject {

  private VertexArrayObject() {
  }

  /**
   * Creates a VertexArrayObject (Object which hold all needed VertexBufferObject).
   *
   * @param vertices           the geometry data
   * @param textureCoordinates the texture coordinates data
   * @return the OpenGL id
   */
  public static int create(final float[] vertices, final float[] textureCoordinates) {
    final var id = GL40.glGenVertexArrays();
    CleanUpUtilities.addVertexArrayObjectId(id);
    GL40.glBindVertexArray(id);

    final var vertexVboId = VertexBufferObject.create(vertices, GL40.GL_STATIC_DRAW);
    GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, vertexVboId);
    GL40.glVertexAttribPointer(0, 3, GL40.GL_FLOAT, false, 0, 0);

    final var uvVboId = VertexBufferObject.create(textureCoordinates, GL40.GL_STATIC_DRAW);
    GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, uvVboId);
    GL40.glVertexAttribPointer(1, 2, GL40.GL_FLOAT, false, 0, 0);

    return id;
  }
}
