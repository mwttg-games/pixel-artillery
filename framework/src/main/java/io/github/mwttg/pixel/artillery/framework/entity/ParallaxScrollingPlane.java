package io.github.mwttg.pixel.artillery.framework.entity;

import io.github.mwttg.pixel.artillery.framework.entity.drawable.Drawable;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.Sprite;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.SpriteAnimation;
import io.github.mwttg.pixel.artillery.framework.window.ViewPortConfiguration;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A plane which is used for parallax scrolling.
 */
public class ParallaxScrollingPlane {

  private final float nearPlane;
  private final float farPlane;
  private final Drawable drawable;
  private Vector3f startPosition;
  private Matrix4f transform; // mutable call by reference (pointer) e.g. the player model matrix
  private Vector3f transformTranslation = new Vector3f();
  private Vector3f cameraTranslation = new Vector3f();

  /**
   * The Constructor.
   *
   * @param config        The {@link ViewPortConfiguration} for the values of the near and far plane
   *                      (of the camera)
   * @param drawable      the {@link Sprite} or {@link SpriteAnimation} which is drawn on that plane
   * @param startPosition the start position of the plane as 3d vector
   * @param transform     the model matrix where the parallax scrolling plane is connected to (e.g.
   *                      the player modelmatrix)
   */
  public ParallaxScrollingPlane(final ViewPortConfiguration config, final Drawable drawable,
                                final Vector3f startPosition, Matrix4f transform) {
    this.nearPlane = config.nearPlane();
    this.farPlane = config.farPlane();
    this.startPosition = startPosition;
    this.drawable = drawable;
    this.transform = transform;
  }

  /**
   * This draws the {@link ParallaxScrollingPlane}.
   * The model matrix of that plane gets calculated inside this method.
   *
   * @param view the view matrix
   * @param projection the projection matrix
   */
  public void draw(final Matrix4f view, final Matrix4f projection) {
    transform.getTranslation(transformTranslation);
    view.getTranslation(cameraTranslation);
    final var distance = transformTranslation.z() - startPosition.z();
    final var clippingPlane = cameraTranslation.z() + (distance > 0 ? farPlane : nearPlane);
    final var parallaxFactor = Math.abs(distance / clippingPlane);

    final var x = startPosition.x() + transformTranslation.x() * parallaxFactor;
    final var model = new Matrix4f().translate(x, startPosition.y(), startPosition.z());

    drawable.draw(model, view, projection);
  }
}
