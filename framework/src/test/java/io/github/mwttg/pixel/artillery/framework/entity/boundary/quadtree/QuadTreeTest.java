package io.github.mwttg.pixel.artillery.framework.entity.boundary.quadtree;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.mwttg.pixel.artillery.common.BoundingBox;
import io.github.mwttg.pixel.artillery.common.Point;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QuadTreeTest {

  private QuadTree subject;

  @BeforeMethod
  public void setup() {
    subject = new QuadTree();
  }

  @Test
  public void testIntersect() {
    final var boundary1 = new BoundingBox(new Point(1.0f, 1.0f), new Point(2.0f, 2.0f));
    final var boundary2 = new BoundingBox(new Point(2.0f, 1.0f), new Point(3.0f, 2.0f));
    final var boundary3 = new BoundingBox(new Point(1.0f, 2.0f), new Point(2.0f, 3.0f));
    final var boundary4 = new BoundingBox(new Point(2.0f, 2.0f), new Point(3.0f, 3.0f));
    subject.add(boundary1);
    subject.add(boundary2);
    subject.add(boundary3);
    subject.add(boundary4);

    final var other = new BoundingBox(new Point(0.5f, 0.5f), new Point(1.5f, 1.5f));

    final var actual = subject.intersect(other);
    assertThat(actual).isTrue();
  }
}