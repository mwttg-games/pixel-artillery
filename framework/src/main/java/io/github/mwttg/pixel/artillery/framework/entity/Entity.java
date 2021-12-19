package io.github.mwttg.pixel.artillery.framework.entity;

import io.github.mwttg.pixel.artillery.framework.entity.drawable.Drawable;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.Sprite;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.SpriteAnimation;
import io.github.mwttg.pixel.artillery.framework.entity.movable.Movable;
import org.joml.Matrix4f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An Entity. This is an attempt of implementing an Entity Component System (ECS).
 */
public class Entity {

  private static final Logger LOG = LoggerFactory.getLogger(Entity.class);

  private final Drawable drawable;
  private final Movable movable;

  private Matrix4f model;

  private Entity(final EntityBuilder builder) {
    this.drawable = builder.getDrawable();
    this.model = builder.getModelMatrix();
    this.movable = builder.getMovable();
  }


  /**
   * Draws the entity, if it has a {@link Drawable} component.
   *
   * @param view       the view matrix (the camera)
   * @param projection the projection matrix
   */
  public void draw(final Matrix4f view, final Matrix4f projection) {
    if (drawable == null) {
      LOG.error("This entity has no render component!");
      throw new RuntimeException(
          "Method #draw was called on an entity without Drawable component.");
    }

    if (model == null) {
      LOG.error("This entity has no model matrix!");
      throw new RuntimeException("Method #draw was called on an entity without model matrix.");
    }

    drawable.draw(model, view, projection);
  }

  /**
   * Moves an entity on the screen, if it has a {@link Movable} component.
   *
   * @param windowId the OpenGL windowId of the game window
   */
  public void move(final long windowId) {
    if (movable == null) {
      LOG.error("This entity has no movement component.");
      throw new RuntimeException(
          "Method #move was called on an entity without movement component.");
    }

    this.model = movable.move(windowId, model);
  }

  /**
   * The Entity Builder.
   */
  public static class EntityBuilder {
    private Drawable drawable;
    private Matrix4f modelMatrix;
    private Movable movable;

    /**
     * The Constructor.
     */
    public EntityBuilder() {
    }

    /**
     * Adds a drawable component to the {@link Entity}.
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
     * Adds a model matrix to the {@link Entity}.
     *
     * @param modelMatrix the model matrix
     * @return the {@link EntityBuilder}
     */
    public EntityBuilder addModelMatrix(final Matrix4f modelMatrix) {
      this.modelMatrix = modelMatrix;
      return this;
    }

    /**
     * Adds a movement component to the entity.
     *
     * @param movable An implementation of a {@link Movable}.
     * @return the {@link EntityBuilder}
     */
    public EntityBuilder addMovable(final Movable movable) {
      this.movable = movable;
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

    private Matrix4f getModelMatrix() {
      return modelMatrix;
    }

    private Movable getMovable() {
      return movable;
    }
  }
}
