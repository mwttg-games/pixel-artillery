package io.github.mwttg.pixel.artillery.tools;

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
  public static void main(String[] args) throws IOException {
    // final var currentDirectory = System.getProperty("user.dir");
    // final var directory = currentDirectory + "/integration-test-files/animation/";
    final var directory =
        "/Users/mwittig/development/private/pixel-artillery/tools/src/main/resources/files/test3/";
    final var definitionFilename = "valid-input-definition.json";
    final var inputDefinition = InputDefinition.createFrom(directory + definitionFilename);

    final var generator = new Generator(inputDefinition);
    final var output = generator.generate();

    final var destination = directory + "output.json";
    output.writeToFile(destination);
  }
}
