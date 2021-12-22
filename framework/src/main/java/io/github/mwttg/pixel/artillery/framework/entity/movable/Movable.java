package io.github.mwttg.pixel.artillery.framework.entity.movable;

import io.github.mwttg.pixel.artillery.framework.entity.Entity;
import org.joml.Matrix4f;

/**
 * An Interface to 'mark'/make something movable.
 */
public interface Movable {

  /**
   * Moves the {@link Entity}.
   *
   * @param windowId the OpenGL game window id
   * @param model    the model matrix
   * @return the {@link MoveTuple} which contains the animation name and the model matrix
   */
  MoveTuple move(final long windowId, final Matrix4f model);
}
