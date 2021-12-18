package io.github.mwttg.pixel.artillery.examples.example03;

import io.github.mwttg.pixel.artillery.examples.TilesOperatorConfiguration;
import io.github.mwttg.pixel.artillery.framework.CleanUpUtilities;
import io.github.mwttg.pixel.artillery.framework.window.GameWindow;
import java.io.IOException;

public class Example03 {
  public static void main(String[] args) throws IOException {
    final var configuration = TilesOperatorConfiguration.create();
    final var gameWindowId = GameWindow.create(configuration);
    final var mainLoop = new MainLoop(configuration);
    mainLoop.loop(gameWindowId);
    CleanUpUtilities.purge();
  }
}
