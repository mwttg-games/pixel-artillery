package io.github.mwttg.pixel.artillery.examples;

import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import io.github.mwttg.pixel.artillery.framework.window.GameWindowConfiguration;
import io.github.mwttg.pixel.artillery.framework.window.OpenGlConfiguration;
import io.github.mwttg.pixel.artillery.framework.window.ViewPortConfiguration;

public class ExampleConfiguration {

  private ExampleConfiguration() {
  }

  public static Configuration create() {
    final var gameWindowConfig = new GameWindowConfiguration("Test-Window", 800, 600);
    final var openGlConfig = new OpenGlConfiguration(4, 1, true, false);
    final var viewPortConfig = new ViewPortConfiguration(0.01f, 300.0f);

    return new Configuration(gameWindowConfig, openGlConfig, viewPortConfig);
  }
}
