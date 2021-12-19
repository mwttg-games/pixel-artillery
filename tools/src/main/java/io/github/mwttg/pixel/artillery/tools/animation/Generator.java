package io.github.mwttg.pixel.artillery.tools.animation;

import io.github.mwttg.pixel.artillery.common.SpriteAnimationData;
import io.github.mwttg.pixel.artillery.common.SpriteData;
import io.github.mwttg.pixel.artillery.tools.common.TextureCoordinateGenerator;
import io.github.mwttg.pixel.artillery.tools.common.TilesFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generates the vertices and texture data for an animation for OpenGL.
 * The data is stored in float arrays which can be used to create OpenGL Vertex Array Objects.
 */
public class Generator {

  private final TextureCoordinateGenerator textureGenerator;
  private final float planeSize;
  private final int animationSteps;
  private final int delay;

  /**
   * The Constructor.
   *
   * @param inputDefinition the parameters for creating an animation
   */
  public Generator(final InputDefinition inputDefinition) {
    this.textureGenerator = new TextureCoordinateGenerator(inputDefinition.textureAtlas());
    this.planeSize = inputDefinition.planeSize();
    this.animationSteps = inputDefinition.animationSteps();
    this.delay = inputDefinition.delayInMs();
  }

  /**
   * Creates the {@link SpriteData}.
   *
   * @return the {@link SpriteData}
   */
  public SpriteAnimationData generate() {
    List<Float> geometry = new ArrayList<>();
    List<Float> textureCoordinates = new ArrayList<>();

    for (int index = 0; index < animationSteps; index++) {
      final List<Float> tile = TilesFactory.createTile(planeSize);
      geometry = Stream.concat(geometry.stream(), tile.stream()).collect(Collectors.toList());

      final List<Float> texture = textureGenerator.createTextureCoordinates(index);
      textureCoordinates = Stream.concat(textureCoordinates.stream(), texture.stream())
          .collect(Collectors.toList());
    }

    final var spriteData = new SpriteData(SpriteData.toFloatArray(geometry),
        SpriteData.toFloatArray(textureCoordinates));

    return new SpriteAnimationData(spriteData, animationSteps, delay);
  }
}
