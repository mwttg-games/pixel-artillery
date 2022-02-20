package io.github.mwttg.pixel.artillery.framework.ecs.system;

import io.github.mwttg.pixel.artillery.framework.ecs.component.RenderComponent;
import io.github.mwttg.pixel.artillery.framework.ecs.component.TransformComponent;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL40;

public class RenderSystem extends AbstractRenderSystem {

  public static void draw(final RenderComponent renderComponent,
                          final TransformComponent transformComponent,
                          final Matrix4f view,
                          final Matrix4f projection) {
    GL40.glBindVertexArray(renderComponent.getVertexArrayObjectId());
    GL40.glUseProgram(renderComponent.getShaderProgramId());
    enableVertexAttribArray();

    renderComponent.getUniforms().upload(transformComponent.getModelMatrix(), view, projection,
        renderComponent.getTextureId());
    GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, renderComponent.getAmountOfPoints());

    disableVertexAttribArray();
  }
}
