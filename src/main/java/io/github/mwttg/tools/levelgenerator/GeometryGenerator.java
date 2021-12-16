package io.github.mwttg.tools.levelgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
final class GeometryGenerator {

  private static final List<Float> DEFAULT_PLANE = Arrays.asList(
      1.0f, 0.0f, 0.0f,
      0.0f, 1.0f, 0.0f,
      0.0f, 0.0f, 0.0f,
      1.0f, 0.0f, 0.0f,
      1.0f, 1.0f, 0.0f,
      0.0f, 1.0f, 0.0f);
  private static final int SIZE = DEFAULT_PLANE.size();

  private GeometryGenerator() {
  }

  /**
   * The DEFAULT_PLANE geometry data gets shifted by x and y.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  static List<Float> createTile(int x, int y) {
    var result = new ArrayList<>(DEFAULT_PLANE);
    for (int index = 0; index < SIZE; index++) {
      var value = result.get(index);
      if (index % 3 == 0) {
        result.set(index, value + (float) x);
      }
      if ((index + 2) % 3 == 0) {
        result.set(index, value + (float) y);
      }
    }

    return result;
  }
}
