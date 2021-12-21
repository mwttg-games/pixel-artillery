package io.github.mwttg.pixel.artillery.tools;

import io.github.mwttg.pixel.artillery.common.ReadFile;
import io.github.mwttg.pixel.artillery.tools.animation.Generator;
import io.github.mwttg.pixel.artillery.tools.animation.InputDefinition;
import java.io.IOException;

/**
 * Generates an animation.
 */
public class AnimationGenerator {

  /**
   * This is the main function ;) .
   */
  public static void main(String[] args) {
    // final var currentDirectory = System.getProperty("user.dir");
    // final var directory = currentDirectory + "/integration-test-files/animation/";
    final var directory =
        "/Users/mwittig/development/private/pixel-artillery/examples/src/main/resources/files/"
            + "example04/";
    final var definitionFilename = "animation-definition.json";
    final var inputDefinition =
        ReadFile.jsonFrom(directory + definitionFilename, InputDefinition.class);

    final var generator = new Generator(inputDefinition);
    final var output = generator.generate();

    final var destination = directory + "output.json";
    output.writeToFile(destination);
  }
}
