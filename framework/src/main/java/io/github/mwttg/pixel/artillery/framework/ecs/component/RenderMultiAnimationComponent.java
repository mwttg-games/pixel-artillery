package io.github.mwttg.pixel.artillery.framework.ecs.component;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * A component that holds the data for multiple animated sprites (e.g. walking, jumping).
 */
public class RenderMultiAnimationComponent implements Component {

  private final Map<String, RenderAnimationComponent> animationByName;
  private String currentAnimationName;

  public RenderMultiAnimationComponent(final Map<String, RenderAnimationComponent> animationByName,
                                       final String currentAnimationName) {
    this.animationByName = animationByName;
    this.currentAnimationName = currentAnimationName;
  }

  public RenderAnimationComponent getRenderAnimationComponent() {
    return animationByName.get(currentAnimationName);
  }

  // setter

  public void setCurrentAnimationName(final String name) {
    this.currentAnimationName = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RenderMultiAnimationComponent that)) {
      return false;
    }
    return animationByName.equals(that.animationByName) &&
        currentAnimationName.equals(that.currentAnimationName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(animationByName, currentAnimationName);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RenderMultiAnimationComponent.class.getSimpleName() + "[", "]")
        .add("animationByName=" + animationByName)
        .add("currentAnimationName='" + currentAnimationName + "'")
        .toString();
  }
}
