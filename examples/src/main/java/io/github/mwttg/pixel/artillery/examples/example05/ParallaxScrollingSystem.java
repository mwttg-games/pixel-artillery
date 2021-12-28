package io.github.mwttg.pixel.artillery.examples.example05;

import io.github.mwttg.pixel.artillery.framework.entity.Entity;
import io.github.mwttg.pixel.artillery.framework.entity.ParallaxScrollingPlane;
import io.github.mwttg.pixel.artillery.framework.entity.drawable.Sprite;
import io.github.mwttg.pixel.artillery.framework.window.Configuration;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class ParallaxScrollingSystem {

  private final ParallaxScrollingPlane plane0;
  private final ParallaxScrollingPlane plane1;
  private final ParallaxScrollingPlane plane2;
  private final ParallaxScrollingPlane plane3;
  private final ParallaxScrollingPlane plane4;

  public ParallaxScrollingSystem(final Configuration configuration, final Entity player) {
    final var plane0Drawable = new Sprite(32, 20, "files/parallax/parallax0.png");
    this.plane0 = new ParallaxScrollingPlane(configuration.viewPortConfiguration(), plane0Drawable,
        new Vector3f(0.0f, 0.0f, -200.0f), player.getPosition().getModelMatrix());

    final var plane1Drawable = new Sprite(64, 25, "files/parallax/parallax1.png");
    this.plane1 = new ParallaxScrollingPlane(configuration.viewPortConfiguration(), plane1Drawable,
        new Vector3f(0.0f, 0.0f, -150.0f), player.getPosition().getModelMatrix());

    final var plane2Drawable = new Sprite(64, 25, "files/parallax/parallax2.png");
    this.plane2 = new ParallaxScrollingPlane(configuration.viewPortConfiguration(), plane2Drawable,
        new Vector3f(0.0f, 0.0f, -100.0f), player.getPosition().getModelMatrix());

    final var plane3Drawable = new Sprite(64, 25, "files/parallax/parallax3.png");
    this.plane3 = new ParallaxScrollingPlane(configuration.viewPortConfiguration(), plane3Drawable,
        new Vector3f(0.0f, 0.0f, -50.0f), player.getPosition().getModelMatrix());

    final var plane4Drawable = new Sprite(64, 25, "files/parallax/parallax4.png");
    this.plane4 = new ParallaxScrollingPlane(configuration.viewPortConfiguration(), plane4Drawable,
        new Vector3f(0.0f, 0.0f, -25.0f), player.getPosition().getModelMatrix());
  }

  public void draw(final Matrix4f view, final Matrix4f projection) {
    plane0.draw(view, projection);
    plane1.draw(view, projection);
    plane2.draw(view, projection);
    plane3.draw(view, projection);
    plane4.draw(view, projection);
  }
}
