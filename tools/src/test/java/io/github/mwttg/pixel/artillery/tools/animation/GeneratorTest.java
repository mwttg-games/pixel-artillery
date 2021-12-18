package io.github.mwttg.pixel.artillery.tools.animation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.mwttg.pixel.artillery.tools.common.TextureAtlas;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneratorTest {

  private Generator subject;

  @BeforeMethod
  public void setup() {
    final var inputDefinition = inputDefinition();
    subject = new Generator(inputDefinition);
  }

  @Test
  public void testGenerate() {
    final var actual = subject.generate();
    assertThat(actual.tilesGeometry()).containsExactly(3.0f, 0.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 3.0f, 3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 3.0f, 0.0f, 0.0f, 0.0f,
        3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 3.0f, 3.0f, 0.0f, 0.0f, 3.0f, 0.0f);
    assertThat(actual.textureCoordinates()).containsExactly(0.33333334f, 0.6666666f, 0.0f, 1.0f,
        0.0f, 0.6666666f, 0.33333334f, 0.6666666f, 0.33333334f, 1.0f, 0.0f, 1.0f, 0.6666667f,
        0.6666666f, 0.33333334f, 1.0f, 0.33333334f, 0.6666666f, 0.6666667f, 0.6666666f, 0.6666667f,
        1.0f, 0.33333334f, 1.0f);
  }

  private InputDefinition inputDefinition() {
    final var textureAtlas = new TextureAtlas(16, 48, 48);
    return new InputDefinition(textureAtlas, 2, 3.0f);
  }
}