package io.github.mwttg.tools.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class TilesFactoryTest {

  @Test
  public void testCreateDefaultTile() {
    final var actual = TilesFactory.createDefaultTile();
    assertThat(actual).containsExactly(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
  }

  @Test
  public void testCreateTile_Size3() {
    final var actual = TilesFactory.createTile(3.0f);
    assertThat(actual).containsExactly(3.0f, 0.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3.0f,
        0.0f, 0.0f, 3.0f, 3.0f, 0.0f, 0.0f, 3.0f, 0.0f);
  }
}