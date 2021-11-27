package io.github.mwttg.tileoperator.core;

import io.github.mwttg.tileoperator.CleanUpUtilities;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL40;

/**
 * A vertex buffer object for vertices (geometry), texture coordinates.
 */
public final class VertexBufferObject {

  private VertexBufferObject() {
  }

  /**
   * Uploads data to the graphic device.
   *
   * @param data     the actual data (vertices/texture coordinates)
   * @param drawType defines how the data is rendered
   * @return the OpenGL id
   */
  public static int create(final float[] data, final int drawType) {
    final var buffer = BufferUtils.createFloatBuffer(data.length);
    buffer.put(data);
    buffer.flip();

    final var id = GL40.glGenBuffers();
    CleanUpUtilities.addVertexBufferObjectId(id);
    GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, id);
    GL40.glBufferData(GL40.GL_ARRAY_BUFFER, buffer, drawType);

    return id;
  }
}
