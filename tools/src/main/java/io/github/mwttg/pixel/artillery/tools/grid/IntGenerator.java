package io.github.mwttg.pixel.artillery.tools.grid;

import io.github.mwttg.pixel.artillery.tools.common.ImageProcessor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IntGenerator {
  private final ImageProcessor imageProcessor;
  private final int blockSize;
  private final Set<String> nonSolidColors;

  /**
   * The Constructor.
   *
   * @param inputDefinition the input definition (from .json)
   * @param levelBlockFile  the image file (.png)
   * @throws IOException if something with I/O goes wrong
   */
  public IntGenerator(final InputDefinition inputDefinition, final File levelBlockFile)
      throws IOException {
    this.imageProcessor = new ImageProcessor(levelBlockFile);
    this.blockSize = inputDefinition.levelBlocks().blockSize();
    this.nonSolidColors = inputDefinition.nonSolidColors();
  }

  /**
   * Generates the Grid.
   *
   * @return the Grid
   */
  public List<List<Integer>> generate() {
    final List<List<Integer>> result = new ArrayList<>();
    final var dimension = imageProcessor.getDimension();

    // x, y position inside the level-block image (for loop)
    // indexX, indexY position in openGL (the tile position)
    // because of openGL y-coordinate is reverse than image (png) y-coordinate we scan the
    // block image from bottom to top (outer for loop)
    int indexX = -1;
    int indexY = 0;
    //  blockSize / 2 for checking the color in the middle of the block (of the block image)
    for (int y = dimension.height() - (blockSize / 2); y > 0; y = y - blockSize) {
      var row = new ArrayList<Integer>();
      for (int x = blockSize / 2; x < dimension.width(); x = x + blockSize) {
        indexX = indexX + 1;
        final var color = imageProcessor.getColorOnPosition(x, y);

        if (!nonSolidColors.contains(color)) {
          row.add(1);
        } else {
          row.add(0);
        }
      }
      indexX = -1;
      indexY = indexY + 1;
      result.add(row);
    }

    return result;
  }
}
