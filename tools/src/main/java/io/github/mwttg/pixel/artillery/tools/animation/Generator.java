package io.github.mwttg.pixel.artillery.tools.animation;

import io.github.mwttg.pixel.artillery.tools.common.TextureCoordinateGenerator;
import io.github.mwttg.pixel.artillery.tools.common.TilesFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generates the geometry and texture data for an animation for OpenGL.
 * The data is stored in float arrays which can be used to create OpenGL Vertex Array Objects.
 */
public class Generator {

  private final TextureCoordinateGenerator textureGenerator;
  private final float planeSize;
  private final int animationSteps;

  /**
   * Constructor.
   *
   * @param inputDefinition the parameters for creating an animation
   */
  public Generator(final InputDefinition inputDefinition) {
    this.textureGenerator = new TextureCoordinateGenerator(inputDefinition.textureAtlas());
    this.planeSize = inputDefinition.planeSize();
    this.animationSteps = inputDefinition.animationSteps();
  }

  /**
   * Creates the {@link Animation}.
   *
   * @return the {@link Animation}
   */
  public Animation generate() {
    List<Float> geometry = new ArrayList<>();
    List<Float> textureCoordinates = new ArrayList<>();

    for (int index = 0; index < animationSteps; index++) {
      final List<Float> tile = TilesFactory.createTile(planeSize);
      geometry = Stream.concat(geometry.stream(), tile.stream()).collect(Collectors.toList());

      final List<Float> texture = textureGenerator.createTextureCoordinates(index);
      textureCoordinates = Stream.concat(textureCoordinates.stream(), texture.stream())
          .collect(Collectors.toList());
    }

    return new Animation(geometry.toArray(new Float[0]), textureCoordinates.toArray(new Float[0]));
  }
}
