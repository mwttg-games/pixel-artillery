package io.github.mwttg.tools.levelgenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Heart of the Generator containing the algorithm for creating the geometry and texture data
 * (float arrays) for OpenGL.
 */
public class LevelGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(LevelGenerator.class);

  private final TextureCoordinateGenerator textureGenerator;
  private final ImageProcessor imageProcessor;
  private final Map<String, Integer> indexByColor;
  private final Dimension dimension;
  private final int blockSize;

  /**
   * Constructor.
   *
   * @param inputDefinition the {@link InputDefinition}
   * @param blockFile       the level as a block image (.png)
   * @throws IOException when I/O foes wrong
   */
  public LevelGenerator(final InputDefinition inputDefinition, final File blockFile)
      throws IOException {
    final var textureAtlas = inputDefinition.textureAtlas();
    this.indexByColor = inputDefinition.tileIndexByColor();
    this.textureGenerator = new TextureCoordinateGenerator(textureAtlas);
    this.imageProcessor = new ImageProcessor(blockFile);
    this.dimension = imageProcessor.getDimension();
    this.blockSize = inputDefinition.levelBlocks().blockSize();
  }

  /**
   * Creates the {@link LevelData}.
   *
   * @return {@link LevelData}
   */
  public LevelData create() {
    List<Float> geometry = new ArrayList<>();
    List<Float> textureCoordinates = new ArrayList<>();

    // x, y position inside the level-block image (for loop)
    // indexX, indexY position in openGL (the tile position)
    // because of openGL y-coordinate is reverse than image (png) y-coordinate we scan the
    // block image from bottom to top (outer for loop)
    int indexX = -1;
    int indexY = 0;
    //  blockSize / 2 for checking the color in the middle of the block (of the block image)
    for (int y = dimension.height() - (blockSize / 2); y > 0; y = y - blockSize) {
      for (int x = blockSize / 2; x < dimension.width(); x = x + blockSize) {
        indexX = indexX + 1;
        final var color = imageProcessor.getColorOnPosition(x, y);
        final var tileIndex = indexByColor.get(color);
        if (tileIndex == null) {
          LOG.warn(
              "No mapping tile (from TileAtlas) found for color '{}'. "
                  + "Position was x = '{}', y = '{}'.",
              color, indexX, indexY);
          continue;
        }

        final var tile = GeometryGenerator.createTile(indexX, indexY);
        geometry = Stream.concat(geometry.stream(), tile.stream()).collect(Collectors.toList());

        final var texture = textureGenerator.createTextureCoordinates(tileIndex);
        textureCoordinates = Stream.concat(textureCoordinates.stream(), texture.stream())
            .collect(Collectors.toList());
      }
      indexX = -1;
      indexY = indexY + 1;
    }

    return new LevelData(geometry.toArray(new Float[0]), textureCoordinates.toArray(new Float[0]));
  }
}
