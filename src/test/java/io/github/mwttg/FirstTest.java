package io.github.mwttg;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest {

 @Test
 public void testAdd3() {
  final int actual = First.add3(7);
  assertThat(actual).isEqualTo(10);
 }
}