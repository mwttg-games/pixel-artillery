package io.github.mwttg.pixel.artillery.framework.ecs.system;

import org.lwjgl.opengl.GL40;

abstract class AbstractRenderSystem {

  static void enableVertexAttribArray() {
    GL40.glEnableVertexAttribArray(0); // vertices
    GL40.glEnableVertexAttribArray(1); // texture coordinates
  }

  static void disableVertexAttribArray() {
    GL40.glDisableVertexAttribArray(1); // texture coordinates
    GL40.glDisableVertexAttribArray(0); // vertices
  }
}
