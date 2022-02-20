package io.github.mwttg.pixel.artillery.framework.ecs.system;

import io.github.mwttg.pixel.artillery.framework.ecs.component.RenderAnimationComponent;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL40;

public class RenderAnimationSystem extends AbstractRenderSystem {

  public static void draw(final RenderAnimationComponent component,
                          final Matrix4f model,
                          final Matrix4f view,
                          final Matrix4f projection) {
    GL40.glBindVertexArray(component.getVertexArrayObjectId());
    GL40.glUseProgram(component.getShaderProgramId());
    enableVertexAttribArray();

    component.getUniforms().upload(model, view, projection, component.getTextureId());
    final var first = getFirstVertexOfPlane(component);
    // count is 6 because every plane is created from 2 triangles (6 vertices complete)
    GL40.glDrawArrays(GL40.GL_TRIANGLES, first, 6);

    disableVertexAttribArray();
  }

  // calculates the animation (which step is the current -> which is the index of the first vertex)
  private static int getFirstVertexOfPlane(final RenderAnimationComponent component) {
    final var now = System.currentTimeMillis();

    if (now - component.getLastTick() > component.getDelayInMs()) {
      component.setLastTick(System.currentTimeMillis());
      component.incCurrentAnimationStep();
    }

    return component.getCurrentAnimationStep() * 6;
  }
}
