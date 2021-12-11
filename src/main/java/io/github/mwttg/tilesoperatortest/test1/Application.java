package io.github.mwttg.tilesoperatortest.test1;

import io.github.mwttg.tileoperator.CleanUpUtilities;
import io.github.mwttg.tileoperator.window.GameWindow;
import io.github.mwttg.tilesoperatortest.TilesOperatorConfiguration;
import java.io.IOException;

/**
 * Test for creating a game window
 */
public class Application {

  public static void main(String[] args) throws IOException {
    final var configuration = TilesOperatorConfiguration.create();
    final var gameWindowId = GameWindow.create(configuration);
    final var mainLoop = new MainLoop(configuration);
    mainLoop.loop(gameWindowId);
    CleanUpUtilities.purge();
  }
}
