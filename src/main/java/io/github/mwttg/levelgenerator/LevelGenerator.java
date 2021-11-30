package io.github.mwttg.levelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(LevelGenerator.class);

  public void create(final String currentDirectory, final String definitionFilename)
      throws IOException {

    final var definitionFile = new File(currentDirectory + definitionFilename);
    final var objectMapper = new ObjectMapper();
    final var inputDefinition = objectMapper.readValue(definitionFile, InputDefinition.class);

    final var blockFile = new File(currentDirectory + inputDefinition.levelBlocks().filename());
    final var imageProcessor = new ImageProcessor(blockFile);
    final var dimension = imageProcessor.getDimension();
    final var blockSize = inputDefinition.levelBlocks().blockSize();


    List<Float> geometry = new ArrayList<>();
    int x = blockSize / 2;
    int y = blockSize / 2;
    int indexX = 0;
    int indexY = 0;
    do {
      do {
        final var color = imageProcessor.getColorOnPosition(x, y);
        final var tileIndex = inputDefinition.tileIndexByColor().get(color);
        if (tileIndex == null) {
          LOG.warn(
              "No mapping tile (from TileAtlas) found for color '{}'. Position was x = '{}', y = '{}'.",
              color, indexX, indexY);
          break;
        }

        final var tile = GeometryGenerator.createTile(indexX, indexY);
        geometry = Stream.concat(geometry.stream(), tile.stream()).collect(Collectors.toList());

        x = x + blockSize;
        indexX = indexX + 1;
      } while (x < dimension.width());
      x = blockSize / 2;
      indexX = 0;
      y = y + blockSize;
      indexY = indexY + 1;
    } while (y < dimension.height());


    final var levelData = new LevelData(geometry.toArray(new Float[0]));
    final var outputFile = new File(currentDirectory + "/output.json");
    objectMapper.writeValue(outputFile, levelData);
  }
}
