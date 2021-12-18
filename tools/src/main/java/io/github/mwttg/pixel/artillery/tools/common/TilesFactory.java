package io.github.mwttg.pixel.artillery.tools.common;

import java.util.Arrays;
import java.util.List;

/**
 * A Factory for generating 3D square planes.
 * The plane is rotated by 90 degree around the x-axis, so it can be used for OpenGL "2D"
 *
 * <p>A Plane in .obj format</p>
 * # Blender v2.92.0 OBJ File: ''
 * # www.blender.org
 * o Plane
 * v 0.000000 0.000000 -0.000000
 * v 1.000000 0.000000 -0.000000
 * v 0.000000 1.000000 0.000000
 * v 1.000000 1.000000 0.000000
 * vt 1.000000 0.000000
 * vt 0.000000 1.000000
 * vt 0.000000 0.000000
 * vt 1.000000 1.000000
 * s off
 * f 2/1 3/2 1/3
 * f 2/1 4/4 3/2
 */
public final class TilesFactory {

  private TilesFactory() {
  }

  /**
   * Creates a default square plane of the  size 1.0fx1.0f.
   *
   * @return the plane
   */
  public static List<Float> createDefaultTile() {
    return Arrays.asList(
        1.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f);
  }

  /**
   * Creates a square plane with a specific size.
   *
   * @param size the size in x-, and y-direction
   * @return the plane
   */
  public static List<Float> createTile(final float size) {
    return Arrays.asList(
        size, 0.0f, 0.0f,
        0.0f, size, 0.0f,
        0.0f, 0.0f, 0.0f,
        size, 0.0f, 0.0f,
        size, size, 0.0f,
        0.0f, size, 0.0f);
  }
}
