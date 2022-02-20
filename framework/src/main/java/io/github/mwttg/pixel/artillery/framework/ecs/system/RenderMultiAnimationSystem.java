package io.github.mwttg.pixel.artillery.framework.ecs.system;

import io.github.mwttg.pixel.artillery.framework.ecs.component.RenderMultiAnimationComponent;
import org.joml.Matrix4f;

public class RenderMultiAnimationSystem {

  public static void draw(final RenderMultiAnimationComponent component,
                          final Matrix4f model,
                          final Matrix4f view,
                          final Matrix4f projection) {
    final var renderAnimationComponent = component.getRenderAnimationComponent();
    RenderAnimationSystem.draw(renderAnimationComponent, model, view, projection);
  }
}
