package io.github.mwttg.pixel.artillery.tools.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageProcessor {

  private final BufferedImage bufferedImage;

  ImageProcessor(final File image) throws IOException {
    this.bufferedImage = ImageIO.read(image);
  }

  String getColorOnPosition(final int x, final int y) {
    final var color = bufferedImage.getRGB(x, y);
    final int red = (color & 0x00ff0000) >> 16;
    final int green = (color & 0x0000ff00) >> 8;
    final int blue = color & 0x000000ff;

    return String.format("%02X%02X%02X", red, green, blue);
  }

  Dimension getDimension() {
    final var width = bufferedImage.getWidth();
    final var height = bufferedImage.getHeight();

    return new Dimension(width, height);
  }
}
