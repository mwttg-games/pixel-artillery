package io.github.mwttg.levelgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class GeometryGeneratorTest {

  @Test
  public void testCreateTile_addX() {
    final var actual = GeometryGenerator.createTile(10, 0);
    assertThat(actual).containsExactly(11.0f, 0.0f, 0.0f, 10.0f, 1.0f, 0.0f, 10.0f, 0.0f, 0.0f,
        11.0f, 0.0f, 0.0f, 11.0f, 1.0f, 0.0f, 10.0f, 1.0f, 0.0f);
  }

  @Test
  public void testCreateTile_addY() {
    final var actual = GeometryGenerator.createTile(0, 10);
    assertThat(actual).containsExactly(1.0f, 10.0f, 0.0f, 0.0f, 11.0f, 0.0f, 0.0f, 10.0f, 0.0f,
        1.0f, 10.0f, 0.0f, 1.0f, 11.0f, 0.0f, 0.0f, 11.0f, 0.0f);
  }
}