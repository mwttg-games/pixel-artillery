package io.github.mwttg.tools.levelgenerator;

import io.github.mwttg.tools.common.TilesFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates the geometry data of the tiles of a level.
 */
final class GeometryGenerator {

  private GeometryGenerator() {
  }

  /**
   * The default tile geometry data gets shifted by x and y.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  static List<Float> createTile(int x, int y) {
    var defaultPlane = TilesFactory.createDefaultTile();
    var result = new ArrayList<>(defaultPlane);
    for (int index = 0; index < defaultPlane.size(); index++) {
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
