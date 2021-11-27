package io.github.mwttg;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class FirstTest {

  @Test
  public void testAdd3() {
    final int actual = First.add3(7);
    assertThat(actual).isEqualTo(10);
  }
}