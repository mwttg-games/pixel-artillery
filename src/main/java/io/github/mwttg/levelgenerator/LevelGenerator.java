package io.github.mwttg.levelgenerator;

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

  LevelGenerator(final InputDefinition inputDefinition, final File blockFile)
      throws IOException {
    final var textureAtlas = inputDefinition.textureAtlas();
    this.indexByColor = inputDefinition.tileIndexByColor();
    this.textureGenerator = new TextureCoordinateGenerator(textureAtlas);
    this.imageProcessor = new ImageProcessor(blockFile);
    this.dimension = imageProcessor.getDimension();
    this.blockSize = inputDefinition.levelBlocks().blockSize();
  }

  LevelData create() {
    List<Float> geometry = new ArrayList<>();
    List<Float> textureCoordinates = new ArrayList<>();

    int x = blockSize / 2; // for checking the color in the middle of the block
    int y = blockSize / 2;
    int indexX = 0;
    int indexY = 0;
    do {
      do {
        final var color = imageProcessor.getColorOnPosition(x, y);
        final var tileIndex = indexByColor.get(color);
        if (tileIndex == null) {
          LOG.warn(
              "No mapping tile (from TileAtlas) found for color '{}'. "
                  + "Position was x = '{}', y = '{}'.",
              color, indexX, indexY);
          break;
        }

        final var tile = GeometryGenerator.createTile(indexX, indexY);
        geometry = Stream.concat(geometry.stream(), tile.stream()).collect(Collectors.toList());

        final var texture = textureGenerator.createTextureCoordinates(tileIndex);
        textureCoordinates = Stream.concat(textureCoordinates.stream(), texture.stream())
            .collect(Collectors.toList());

        x = x + blockSize;
        indexX = indexX + 1;
      } while (x < dimension.width());
      x = blockSize / 2;
      indexX = 0;
      y = y + blockSize;
      indexY = indexY + 1;
    } while (y < dimension.height());


    return new LevelData(geometry.toArray(new Float[0]), textureCoordinates.toArray(new Float[0]));
  }
}
