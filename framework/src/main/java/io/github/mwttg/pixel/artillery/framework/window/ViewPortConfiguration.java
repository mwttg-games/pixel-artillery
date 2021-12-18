package io.github.mwttg.pixel.artillery.framework.window;

/**
 * The ViewPort configuration.
 */
public record ViewPortConfiguration(float nearPlane, float farPlane) {

  String prettyPrint() {
    return """
            + View Port Configuration
                Near Plane ..................... %s
                Far Plane ...................... %s
        """.formatted(nearPlane, farPlane);
  }
}
