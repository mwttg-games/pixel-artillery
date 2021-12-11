package io.github.mwttg.tilesoperatortest;

import io.github.mwttg.tileoperator.window.Configuration;
import io.github.mwttg.tileoperator.window.GameWindowConfiguration;
import io.github.mwttg.tileoperator.window.OpenGlConfiguration;
import io.github.mwttg.tileoperator.window.ViewPortConfiguration;

public class TilesOperatorConfiguration {

  private TilesOperatorConfiguration() {
  }

  public static Configuration create() {
    final var gameWindowConfig = new GameWindowConfiguration("Test-Window", 800, 600);
    final var openGlConfig = new OpenGlConfiguration(4, 1, true, false);
    final var viewPortConfig = new ViewPortConfiguration(0.01f, 300.0f);

    return new Configuration(gameWindowConfig, openGlConfig, viewPortConfig);
  }
}
