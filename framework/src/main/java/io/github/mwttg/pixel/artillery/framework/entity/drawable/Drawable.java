package io.github.mwttg.pixel.artillery.framework.entity.drawable;

import org.joml.Matrix4f;

/**
 * An Interface to 'mark'/make something drawable.
 */
public interface Drawable {

  /**
   * Draws whatever/whoever implements this interface.
   *
   * @param model      the model matrix
   * @param view       the view matrix (camera)
   * @param projection the projection matrix
   */
  void draw(final Matrix4f model, final Matrix4f view, final Matrix4f projection);
}
