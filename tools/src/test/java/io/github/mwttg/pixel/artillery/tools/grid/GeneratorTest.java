package io.github.mwttg.pixel.artillery.tools.grid;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.mwttg.pixel.artillery.common.BoundingBox;
import io.github.mwttg.pixel.artillery.common.Point;
import io.github.mwttg.pixel.artillery.tools.common.LevelBlocks;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
        List.of(
            Optional.of(new BoundingBox(new Point(0.0f, 0.0f), new Point(1.0f, 1.0f))),
            Optional.of(new BoundingBox(new Point(1.0f, 0.0f), new Point(2.0f, 1.0f))),
            Optional.empty(),
            Optional.of(new BoundingBox(new Point(3.0f, 0.0f), new Point(4.0f, 1.0f))),
            Optional.of(new BoundingBox(new Point(4.0f, 0.0f), new Point(5.0f, 1.0f)))),
        List.of(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()),
        List.of(
            Optional.empty(),
            Optional.of(new BoundingBox(new Point(1.0f, 2.0f), new Point(2.0f, 3.0f))),
            Optional.of(new BoundingBox(new Point(2.0f, 2.0f), new Point(3.0f, 3.0f))),
            Optional.of(new BoundingBox(new Point(3.0f, 2.0f), new Point(4.0f, 3.0f))),
            Optional.empty()),
        List.of(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()),
        List.of(
            Optional.of(new BoundingBox(new Point(0.0f, 4.0f), new Point(1.0f, 5.0f))),
            Optional.of(new BoundingBox(new Point(1.0f, 4.0f), new Point(2.0f, 5.0f))),
            Optional.of(new BoundingBox(new Point(2.0f, 4.0f), new Point(3.0f, 5.0f))),
            Optional.of(new BoundingBox(new Point(3.0f, 4.0f), new Point(4.0f, 5.0f))),
            Optional.of(new BoundingBox(new Point(4.0f, 4.0f), new Point(5.0f, 5.0f)))));
  }

  private InputDefinition inputDefinition(final String filename) {
    final var levelBlocks = new LevelBlocks(10, filename);
    final var nonSolidColors = Set.of("000000");
    return new InputDefinition(nonSolidColors, levelBlocks);
  }
}