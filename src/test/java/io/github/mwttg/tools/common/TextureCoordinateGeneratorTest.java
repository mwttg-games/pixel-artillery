package io.github.mwttg.tools.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class TextureCoordinateGeneratorTest {

  private static final TextureAtlas SIMPLE_ATLAS = new TextureAtlas(16, 16, 16);
  private static final TextureAtlas COMPLEX_ATLAS = new TextureAtlas(16, 32, 32);

  private TextureCoordinateGenerator subject;

  @Test
  public void testCreateUVs_simple() {
    subject = new TextureCoordinateGenerator(SIMPLE_ATLAS);
    final var actual = subject.createTextureCoordinates(0);
    assertThat(actual).containsExactly(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f,
        0.0f, 1.0f);
  }

  @Test
  public void testCreateUVs_complex_tileIndexIs0() {
    subject = new TextureCoordinateGenerator(COMPLEX_ATLAS);
    final var actual = subject.createTextureCoordinates(0);
    assertThat(actual).containsExactly(0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 1.0f,
        0.0f, 1.0f);
  }

  @Test
  public void testCreateUVs_complex_tileIndexIs1() {
    subject = new TextureCoordinateGenerator(COMPLEX_ATLAS);
    final var actual = subject.createTextureCoordinates(1);
    assertThat(actual).containsExactly(1.0f, 0.5f, 0.5f, 1.0f, 0.5f, 0.5f, 1.0f, 0.5f, 1.0f, 1.0f,
        0.5f, 1.0f);
  }

  @Test
  public void testCreateUVs_complex_tileIndexIs2() {
    subject = new TextureCoordinateGenerator(COMPLEX_ATLAS);
    final var actual = subject.createTextureCoordinates(2);
    assertThat(actual).containsExactly(0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f,
        0.0f, 0.5f);
  }

  @Test
  public void testCreateUVs_complex_tileIndexIs3() {
    subject = new TextureCoordinateGenerator(COMPLEX_ATLAS);
    final var actual = subject.createTextureCoordinates(3);
    assertThat(actual).containsExactly(1.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 0.5f,
        0.5f, 0.5f);
  }
}