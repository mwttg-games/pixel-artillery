package io.github.mwttg.pixel.artillery.examples.example02;

import io.github.mwttg.pixel.artillery.examples.ExampleConfiguration;
import io.github.mwttg.pixel.artillery.framework.CleanUpUtilities;
import io.github.mwttg.pixel.artillery.framework.window.GameWindow;
import java.io.IOException;

/**
 * Test for tiles rendering
 * <p>
 * all graphic source files are  in the resource folder (files/test2/)
 * <p>
 * the level.json file was generated with the Level-Generator
 * See: https://github.com/mwttg/level-generator
 */
public class Example02 {
  public static void main(String[] args) throws IOException {
    final var configuration = ExampleConfiguration.create();
    final var gameWindowId = GameWindow.create(configuration);
    final var mainLoop = new MainLoop(configuration);
    mainLoop.loop(gameWindowId);
    CleanUpUtilities.purge();
  }
}
