package io.github.mwttg.pixel.artillery.tools.grid;

import io.github.mwttg.pixel.artillery.tools.common.LevelBlocks;
import java.util.Set;

/**
 * The model class of the definition file for the Grid generator.
 */
public record InputDefinition(Set<String> nonSolidColors, LevelBlocks levelBlocks) {
}
