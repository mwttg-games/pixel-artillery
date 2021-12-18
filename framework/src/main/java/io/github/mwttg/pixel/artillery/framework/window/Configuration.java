package io.github.mwttg.pixel.artillery.framework.window;

/**
 * The whole configuration for the library.
 */
public record Configuration(GameWindowConfiguration gameWindowConfiguration,
                            OpenGlConfiguration openGlConfiguration,
                            ViewPortConfiguration viewPortConfiguration) {

  /**
   * Like #toString but pretty.
   *
   * @return formatted toString
   */
  public String prettyPrint() {
    return """
        Configuration
        %s%s%s
        """.formatted(
        gameWindowConfiguration.prettyPrint(),
        openGlConfiguration.prettyPrint(),
        viewPortConfiguration.prettyPrint());
  }
}
