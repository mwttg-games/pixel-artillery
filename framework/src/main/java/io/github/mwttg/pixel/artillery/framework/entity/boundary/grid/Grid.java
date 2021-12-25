package io.github.mwttg.pixel.artillery.framework.entity.boundary.grid;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.mwttg.pixel.artillery.common.ReadFile;
import java.util.List;

/**
 * A component for an entity. Can be used to have a model for walkable and solid areas of a level.
 * Simple boundary bix checks.
 */
public class Grid {

  private final List<List<Integer>> grid;

  /**
   * The Constructor.
   *
   * @param filename the filename of the json file (generated with the tool GridGenerator).
   */
  public Grid(final String filename) {
    final var type = new TypeReference<List<List<Integer>>>() {
    };
    this.grid = ReadFile.jsonFromResources(filename, type);
  }

  public boolean isSolid(final int x, final int y) {
    return grid.get(y).get(x) == 1;
  }
}
