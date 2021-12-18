package io.github.mwttg.pixel.artillery.examples.example01;

import io.github.mwttg.pixel.artillery.examples.ExampleConfiguration;
import io.github.mwttg.pixel.artillery.framework.CleanUpUtilities;
import io.github.mwttg.pixel.artillery.framework.window.GameWindow;
import java.io.IOException;

/**
 * Test for creating a game window
 */
public class Example01 {

  public static void main(String[] args) throws IOException {
    final var configuration = ExampleConfiguration.create();
    final var gameWindowId = GameWindow.create(configuration);
    final var mainLoop = new MainLoop(configuration);
    mainLoop.loop(gameWindowId);
    CleanUpUtilities.purge();
  }
}
