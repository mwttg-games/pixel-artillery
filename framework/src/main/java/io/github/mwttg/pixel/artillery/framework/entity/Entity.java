package io.github.mwttg.pixel.artillery.framework.entity;

import io.github.mwttg.pixel.artillery.framework.entity.drawable.Drawable;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.Sprite;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.SpriteAnimation;
import io.github.mwttg.pixel.artillery.framework.graphics.MatrixStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An Entity. This is an attempt of implementing an Entity Component System (ECS).
 */
public class Entity {

  private static final Logger LOG = LoggerFactory.getLogger(Entity.class);

  private final Drawable drawable;

  private Entity(final EntityBuilder builder) {
    this.drawable = builder.getDrawable();
  }

  /**
   * Draws the entity, if it has a drawable component.
   *
   * @param matrixStack the {@link MatrixStack} includes the model-, view- and perspective matrix
   */
  public void draw(final MatrixStack matrixStack) {
    if (drawable == null) {
      LOG.error("This entity has no render component!");
      throw new RuntimeException("Method draw was called on an entity without Drawable component.");
    }
    drawable.draw(matrixStack);
  }

  /**
   * The Entity Builder.
   */
  public static class EntityBuilder {
    private Drawable drawable;

    /**
     * The Constructor.
     */
    public EntityBuilder() {
    }

    /**
     * Adds a drawable component to the entity.
     *
     * @param drawable An implementation of a {@link Drawable} component e.g. {@link Sprite} or
     *                 {@link SpriteAnimation}.
     * @return the {@link EntityBuilder}
     */
    public EntityBuilder addDrawable(final Drawable drawable) {
      this.drawable = drawable;
      return this;
    }

    /**
     * Creates the entity.
     *
     * @return the {@link Entity}
     */
    public Entity build() {
      return new Entity(this);
    }

    // start of all getters
    private Drawable getDrawable() {
      return drawable;
    }
  }
}
