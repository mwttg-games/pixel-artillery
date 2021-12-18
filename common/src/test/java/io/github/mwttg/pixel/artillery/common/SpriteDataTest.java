package io.github.mwttg.pixel.artillery.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.testng.annotations.Test;

public class SpriteDataTest {

  @Test
  public void testToFloatArray() {
    final var input = List.of(1.0f, 2.0f, 3.0f);
    final var actual = SpriteData.toFloatArray(input);
    assertThat(actual).containsExactly(1.0f, 2.0f, 3.0f);
  }
}