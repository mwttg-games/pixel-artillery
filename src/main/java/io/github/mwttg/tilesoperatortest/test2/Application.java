package io.github.mwttg.tilesoperatortest.test2;

import io.github.mwttg.tileoperator.CleanUpUtilities;
import io.github.mwttg.tileoperator.window.GameWindow;
import io.github.mwttg.tilesoperatortest.TilesOperatorConfiguration;
import java.io.IOException;

/**
 * Test for tiles rendering
 *
 * all graphic source files are  in the resource folder (files/test2/)
 *
 * the level.json file was generated with the Level-Generator
 *   See: https://github.com/mwttg/level-generator
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
