# Level Generator

(no better name, for now)

This is a little tool for generating level data (for OpenGL) with a draw application.
It needs two input files to generate the output.

## Overview

| ![Overview][overview] |
| ----------------------|

## in Detail

The idea is to create a level in a draw application as a `.png` image by using colored blocks.
This could look something like this:

| ![the Level as blocks][blocks] |
| ------------------------------ |

In the end result, the Level should use textures. Therefore, a `Texture Atlas` is needed, which could look like this:

| ![a Texture Atlas][texture-atlas] |
| --------------------------------- |

The indexing of the tiles inside the `Texture Atlas` is defined by the `Level Generator` like this:

| ![the Texture Atlas with index][texture-atlas-index] |
| ---------------------------------------------------- |

There is also a mapping needed from the used color of the blocks to the texture which should be rendered in the end.
Something like this:

| ![the mapping of the colored blocks][map] |
| ----------------------------------------- |

For the color mapping and some other information a input `.json` file is needed.
This could look like:
```json
{
  "textureAtlas": {
    "tileSize": 32,
    "width": 256,
    "height": 256
  },
  "levelBlocks": {
    "blockSize": 10,
    "filename": "level-blocks.png"
  },
  "tileIndexByColor": {
    "1E134F": 2,
    "241D79": 20,
    "2932A9": 34,
    "3250CD": 16,
    "5C79D7": 0,
    "6E9ADC": 4,
    "A4C9E9": 36,
    "D5EAF5": 32,
    "E7F5F9": 40,
    "D640B3": 18,
    "10432E": 9,
    "176139": 10,
    "1F7F3D": 11,
    "27A344": 17,
    "31CE44": 19,
    "A9E698": 25,
    "C8EDAF": 26,
    "D9F3C8": 27,
    "E7F7DA": 18,
    "A1A1A1": 5,
    "8B8B8B": 6,
    "717171": 14,
    "525252": 13
  }
}
```

The `Level Generator` will take this input file and the `level block` image file and creates a `.json`  output file.

```json
{
  "tilesGeometry" : [ 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 2.0, ... ],
  "textureCoordinates" : [ 0.125, 0.25, 0.0, 0.375, 0.0, 0.25, 0.125, 0.25, 0.125, 0.375, 0.0, 0.375, 0.125, 0.25, 0.0, 0.375, ... ]
}
```

The `tilesGeometry` float array you can use for creating an OpenGL Vertex Buffer Object for the vertices (the mesh).

| ![the geometry data][mesh] |
| -------------------------- |

The `textureCoordinates` float array you can use for creating an OpenGL Vertex Buffer Object for the uv- coordinates of the texture. 
After that you can create am OpenGL Vertex Array Object (with both Vertex Buffer Objects), load the `Texture Atlas` as a texture and the result should render the textured level mesh in one draw call.

| ![the rendered Level][result] |
| ----------------------------- |

[blocks]: images/level-blocks.png
[map]: images/map-hint.png
[mesh]: images/mesh.png
[overview]: images/Overview.svg
[result]: images/result.png
[texture-atlas]: images/texture-atlas.png
[texture-atlas-index]: images/texture-atlas-index.png