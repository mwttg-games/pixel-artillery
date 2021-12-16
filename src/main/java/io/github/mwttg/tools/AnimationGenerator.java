package io.github.mwttg.tools;

import io.github.mwttg.tools.animation.Generator;
import io.github.mwttg.tools.animation.InputDefinition;
import java.io.IOException;

/**
 * Generates an animation.
 */
public class AnimationGenerator {

  /**
   * This is the main function ;) .
   */
  public static void main(String[] args) throws IOException {
    final var currentDirectory = System.getProperty("user.dir");
    final var directory = currentDirectory + "/integration-test-files/animation/";
//    final var directory =
//        "/Users/mwittig/development/private/tiles-operator-test/src/main/resources/files/test2/";
    final var definitionFilename = "valid-input-definition.json";
    final var inputDefinition = InputDefinition.createFrom(directory + definitionFilename);

    final var generator = new Generator(inputDefinition);
    final var output = generator.generate();

    final var destination = directory + "output.json";
    // final var destination = directory + "/level.json";
    output.writeToFile(destination);
  }
}
