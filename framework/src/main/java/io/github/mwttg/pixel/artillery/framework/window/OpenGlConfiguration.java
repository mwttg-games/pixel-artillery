package io.github.mwttg.pixel.artillery.framework.window;

/**
 * The OpenGL configuration.
 */
public record OpenGlConfiguration(int openGlMajorVersion,
                                  int openGlMinorVersion,
                                  boolean vsync,
                                  boolean wireframe) {

  String prettyPrint() {
    return """
            + OpenGL Configuration
                OpenGL version.................. %s.%s
                vsync .......................... %s
                wireframe ...................... %s
        """.formatted(openGlMajorVersion, openGlMinorVersion, vsync, wireframe);
  }
}
