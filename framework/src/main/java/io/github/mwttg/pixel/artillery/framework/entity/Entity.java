package io.github.mwttg.pixel.artillery.framework.entity;

import io.github.mwttg.pixel.artillery.framework.entity.drawable.Drawable;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.Sprite;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.SpriteAnimation;
import io.github.mwttg.pixel.artillery.framework.entity.position.Position;
import io.github.mwttg.pixel.artillery.framework.entity.velocity.Velocity;
import java.util.HashMap;
import java.util.Map;
import org.joml.Matrix4f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An Entity. This is an attempt of implementing an Entity Component System (ECS).
 */
public class Entity {

  private static final Logger LOG = LoggerFactory.getLogger(Entity.class);
  public static final String DEFAULT_DRAWABLE_NAME = "default";

  private final Map<String, Drawable> drawableByName;

  private Position position;
  private String drawableName;
  private Velocity velocity;
  private float gravity;

  private Entity(final EntityBuilder builder) {
    this.drawableByName = builder.getDrawableByName();
    this.drawableName = builder.getDrawableName();
    this.position = builder.getPosition();
    this.velocity = builder.getVelocity();
    this.gravity = builder.getGravity();
  }


  /**
   * Draws the entity, if it has a {@link Drawable} component.
   *
   * @param view       the view matrix (the camera)
   * @param projection the projection matrix
   */
  public void draw(final Matrix4f view, final Matrix4f projection) {
    if (drawableByName == null) {
      LOG.error("This entity has no render component!");
      throw new RuntimeException(
          "Method #draw was called on an entity without Drawable component.");
    }

    if (position == null) {
      LOG.error("This entity has no model matrix!");
      throw new RuntimeException("Method #draw was called on an entity without model matrix.");
    }

    final var currentDrawable = drawableByName.get(drawableName);
    final var model = position.getModelMatrix();
    currentDrawable.draw(model, view, projection);
  }

  public void setDrawableName(String drawableName) {
    this.drawableName = drawableName;
  }

  // start of Getters
  public Position getPosition() {
    return position;
  }

  public Velocity getVelocity() {
    return velocity;
  }

  public float getGravity() {
    return gravity;
  }

  /**
   * The Entity Builder.
   */
  public static class EntityBuilder {
    private final Map<String, Drawable> drawableByName = new HashMap<>();
    private String drawableName;
    private Position position;
    private Velocity velocity;
    private float gravity;

    /**
     * The Constructor.
     */
    public EntityBuilder() {
    }

    /**
     * Adds a drawable component to the {@link Entity} (with a default name).
     *
     * @param drawable An implementation of a {@link Drawable} component e.g. {@link Sprite} or
     *                 {@link SpriteAnimation}.
     * @return the {@link EntityBuilder}
     */
    public EntityBuilder addDrawable(final Drawable drawable) {
      this.drawableByName.put(DEFAULT_DRAWABLE_NAME, drawable);
      setDrawableName(DEFAULT_DRAWABLE_NAME);
      return this;
    }

    /**
     * Adds a drawable with name to the {@link Entity}.
     *
     * @param name     the name of the animation (to find/fetch it again)
     * @param drawable An implementation of a {@link Drawable} component e.g. {@link Sprite} or
     *                 {@link SpriteAnimation}.
     * @return the {@link EntityBuilder}
     */
    public EntityBuilder addDrawable(final String name, final Drawable drawable) {
      this.drawableByName.put(name, drawable);
      return this;
    }

    /**
     * Sets the initial animation by setting the name.
     *
     * @param name the animation name
     * @return the {@link EntityBuilder}
     */
    public EntityBuilder setDrawableName(final String name) {
      this.drawableName = name;
      return this;
    }

    public EntityBuilder addPosition(final Position position) {
      this.position = position;
      return this;
    }

    public EntityBuilder addVelocity(final Velocity velocity) {
      this.velocity = velocity;
      return this;
    }

    public EntityBuilder setGravity(final float gravity) {
      this.gravity = gravity;
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
    private Map<String, Drawable> getDrawableByName() {
      return drawableByName;
    }

    private String getDrawableName() {
      return drawableName;
    }

    private Position getPosition() {
      return position;
    }

    private Velocity getVelocity() {
      return velocity;
    }

    public float getGravity() {
      return gravity;
    }
  }
}
