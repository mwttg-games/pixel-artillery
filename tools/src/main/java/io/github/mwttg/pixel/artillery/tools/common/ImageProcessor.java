package io.github.mwttg.pixel.artillery.tools.common;

import io.github.mwttg.pixel.artillery.common.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Scans a .png image block by block and fetches the color.
 */
public class ImageProcessor {

  private final BufferedImage bufferedImage;

  /**
   * The Constructor.
   *
   * @param image the image file (.png)
   * @throws IOException if I/O goes wrong
   */
  public ImageProcessor(final File image) throws IOException {
    this.bufferedImage = ImageIO.read(image);
  }

  /**
   * Gets the color of the pixel on position x, y.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @return the color
   */
  public String getColorOnPosition(final int x, final int y) {
    final var color = bufferedImage.getRGB(x, y);
    final int red = (color & 0x00ff0000) >> 16;
    final int green = (color & 0x0000ff00) >> 8;
    final int blue = color & 0x000000ff;

    return String.format("%02X%02X%02X", red, green, blue);
  }

  /**
   * Returns the dimension (width/height) of the image (.png)
   *
   * @return width and height
   */
  public Dimension getDimension() {
    final var width = bufferedImage.getWidth();
    final var height = bufferedImage.getHeight();

    return new Dimension(width, height);
  }
}
