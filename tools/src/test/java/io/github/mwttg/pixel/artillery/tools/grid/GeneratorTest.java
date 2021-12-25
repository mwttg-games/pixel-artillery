package io.github.mwttg.pixel.artillery.tools.grid;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.mwttg.pixel.artillery.tools.common.LevelBlocks;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneratorTest {

  private Generator subject;

  @BeforeMethod
  public void setup() throws IOException {
    final var currentDirectory = System.getProperty("user.dir");
    final var filename = currentDirectory + "/integration-test-files/grid/level-blocks.png";
    final var inputDefinition = inputDefinition(filename);
    subject = new Generator(inputDefinition, new File(filename));
  }

  @Test
  public void testGenerate() {
    final var actual = subject.generate();
    assertThat(actual).containsExactly(
        List.of(1, 1, 0, 1, 1),
        List.of(0, 0, 0, 0, 0),
        List.of(0, 1, 1, 1, 0),
        List.of(0, 0, 0, 0, 0),
        List.of(1, 1, 1, 1, 1));
  }

  private InputDefinition inputDefinition(final String filename) {
    final var levelBlocks = new LevelBlocks(10, filename);
    final var nonSolidColors = Set.of("000000");
    return new InputDefinition(nonSolidColors, levelBlocks);
  }
}