package io.github.mwttg.pixel.artillery.common;

import java.util.List;

/**
 * A class for persist the tiles of a level to store solid blocks and walkable space.
 */
public record GridData(List<List<Integer>> data) {
}
