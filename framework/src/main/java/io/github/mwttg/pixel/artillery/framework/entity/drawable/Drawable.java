package io.github.mwttg.pixel.artillery.framework.entity.drawable;

import io.github.mwttg.pixel.artillery.framework.graphics.MatrixStack;

/**
 * An Interface to 'mark' something drawable.
 */
public interface Drawable {

  /**
   * Draws what/who ever implements this interface.
   *
   * @param matrixStack the {@link MatrixStack} including model-, view- and perspective matrix
   */
  void draw(final MatrixStack matrixStack);
}
