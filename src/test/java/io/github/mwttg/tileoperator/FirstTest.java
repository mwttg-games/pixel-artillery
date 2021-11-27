package io.github.mwttg.tileoperator;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.mwttg.tileoperator.First;
import org.testng.annotations.Test;

public class FirstTest {

  @Test
  public void testAdd3() {
    final int actual = First.add3(7);
    assertThat(actual).isEqualTo(10);
  }
}