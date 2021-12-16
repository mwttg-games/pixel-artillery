package io.github.mwttg.tools.levelgenerator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.mwttg.tools.common.TextureAtlas;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LevelGeneratorTest {

  private LevelGenerator subject;

  @BeforeMethod
  public void setup() throws IOException {
    final var currentDirectory = System.getProperty("user.dir");
    final var blockFile =
        new File(currentDirectory + "/integration-test-files/level/level-blocks2.png");

    final var inputDefinition = inputDefinition();
    subject = new LevelGenerator(inputDefinition, blockFile);
  }

  @Test
  public void testCreate() {
    final var actual = subject.create();

    assertThat(actual.tilesGeometry()).containsExactly(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    assertThat(actual.textureCoordinates()).containsExactly(0.33333334f, 0.6666666f, 0.0f, 1.0f,
        0.0f, 0.6666666f, 0.33333334f, 0.6666666f, 0.33333334f, 1.0f, 0.0f, 1.0f);
  }

  private InputDefinition inputDefinition() {
    final var textureAtlas = new TextureAtlas(16, 48, 48);
    final var levelBlocks = new LevelBlocks(10, "level-blocks2.png");
    final var tileIndexByColor = Map.of("31CE44", 0,
        "2932A9", 1);

    return new InputDefinition(textureAtlas, levelBlocks, tileIndexByColor);
  }
}