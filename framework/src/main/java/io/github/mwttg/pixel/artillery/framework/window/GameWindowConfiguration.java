package io.github.mwttg.pixel.artillery.framework.window;

/**
 * The GameWindow configuration.
 */
public record GameWindowConfiguration(String title,
                                      int width,
                                      int height) {

  String prettyPrint() {
    return """
            + Game Window Configuration
                title .......................... %s
                dimension ...................... %sx%s
        """.formatted(title, width, height);
  }
}
