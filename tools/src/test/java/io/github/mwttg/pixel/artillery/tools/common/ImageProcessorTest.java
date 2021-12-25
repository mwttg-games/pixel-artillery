package io.github.mwttg.pixel.artillery.tools.common;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.mwttg.pixel.artillery.common.Dimension;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ImageProcessorTest {

  private ImageProcessor subject;

  @BeforeMethod
  public void setup() throws IOException {
    final var currentDirectory = System.getProperty("user.dir");
    final var file = new File(currentDirectory + "/integration-test-files/level/image.png");
    subject = new ImageProcessor(file);
  }

  @Test
  public void testGetDimension() {
    final var actual = subject.getDimension();
    final var expected = new Dimension(10, 20);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testGetColor_red() {
    final var actual = subject.getColorOnPosition(0, 0);
    assertThat(actual).isEqualTo("FF0000");
  }

  @Test
  public void testGetColor_green() {
    final var actual = subject.getColorOnPosition(5, 10);
    assertThat(actual).isEqualTo("00FF00");
  }

  @Test
  public void testGetColor_blue() {
    final var actual = subject.getColorOnPosition(9, 19);
    assertThat(actual).isEqualTo("0000FF");
  }
}