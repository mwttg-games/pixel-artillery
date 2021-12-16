package io.github.mwttg.tools.common;

import java.util.List;

// @formatter:off
/**
 * <p>Wavefront OBJ file example</p>
 * # Blender v2.92.0 OBJ File: ''
 * # www.blender.org
 * o Plane
 * v 0.000000 0.000000 0.000000
 * v 1.000000 0.000000 0.000000
 * v 0.000000 1.000000 0.000000
 * v 1.000000 1.000000 0.000000
 * vt 1.000000 0.000000
 * vt 0.000000 1.000000
 * vt 0.000000 0.000000
 * vt 1.000000 1.000000
 * s off
 * f 2/1 3/2 1/3
 * f 2/1 4/4 3/2
 *
 * <p>OpenGL Texture coordinates example</p>
 * The texture coordinates for the example plane from above would look like:
 * 3 (0.0, 1.0, 0.0)                  4 (1.0, 1.0, 0.0)
 *               ------------------------
 *               | \                    |
 *               |    \                 |
 *               |       \              |
 *               |          \           |
 *               |              \       |
 *               |                 \    |
 *               |                    \ |
 *               ------------------------
 * 1 (0.0, 0.0, 0.0)                  2 (1.0, 0.0, 0.0)
 *
 * <p>The Texture Atlas</p>
 * the tile index (inside the atlas) is defined like:
 * (Coordinates for textures in OpenGL starting by 0.0 to 1.0)
 * (0.0, 1.0)                 (1.0, 1.0)
 *       -------------------------
 *       | 0 | 1 | 2 | 3 | 4 |...|
 *       -------------------------
 *       |         ...           |
 *       -------------------------
 *       |...|...|...|n-2|n-1| n |
 *       -------------------------
 * (0.0, 0.0)                 (1.0, 0.0)
 */
// @formatter:on

public final class TextureCoordinateGenerator {

  private final int tileSize;
  private final int width;
  private final int height;
  private final int tilesPerRow;

  public TextureCoordinateGenerator(final TextureAtlas atlas) {
    this.tileSize = atlas.tileSize();
    this.width = atlas.width();
    this.height = atlas.height();
    this.tilesPerRow = width / tileSize;
  }

  /**
   * UVs for a tile ( = 2 triangles).
   */
  public List<Float> createTextureCoordinates(final int tileIndex) {
    final var tileRow = tileIndex / tilesPerRow;
    final var tileColumn = tileIndex % tilesPerRow;

    final var right = (float) ((tileColumn * tileSize) + tileSize) / (float) width;
    final var bottom = 1.0f - ((float) ((tileRow * tileSize) + tileSize) / (float) height);

    final var left = (float) (tileColumn * tileSize) / (float) width;
    final var top = 1.0f - (float) (tileRow * tileSize) / (float) height;

    // obj index   [-----2-----]  [---3---]  [-----1----]  [-----2-----]  [---4----]  [---3---]
    return List.of(right, bottom, left, top, left, bottom, right, bottom, right, top, left, top);
  }
}
