package io.github.mwttg.pixel.artillery.framework.entity.boundary.quadtree;

import io.github.mwttg.pixel.artillery.common.BoundingBox;

/**
 * First Prototype of a Quadtree, used for checking where the player can walk.
 * ToDo: Tests missing
 * ToDo: Think about for creating the Quadtree to randomize the 'input list' first
 *   (for a better initial quadtree)
 * After a while I realized a Grid would solve the same problem (at the moment).
 * So... no further implementing at the moment  for this one ô.Ô
 */
public class QuadTree {

  private Node root;

  /**
   * Check if there is another bounding box in the {@link QuadTree} which intersects with this one.
   *
   * @param boundingBox the {@link BoundingBox} which should be checked
   * @return true/false
   */
  public boolean intersect(final BoundingBox boundingBox) {
    return intersectRecursive(root, boundingBox);
  }

  private boolean intersectRecursive(final Node current, final BoundingBox boundingBox) {
    if (current.getBoundingBox().intersect(boundingBox)) {
      return true;
    }

    if (goesNorthEast(current, boundingBox)) {
      return intersectRecursive(current.getNorthEast(), boundingBox);
    } else if (goesNorthWest(current, boundingBox)) {
      return intersectRecursive(current.getNorthWest(), boundingBox);
    } else if (goesSouthEast(current, boundingBox)) {
      return intersectRecursive(current.getSouthEast(), boundingBox);
    } else if (goesSouthWest(current, boundingBox)) {
      return intersectRecursive(current.getSouthWest(), boundingBox);
    }

    throw new RuntimeException("This should be impossible to reach... :/");
  }

  /**
   * Add a {@link BoundingBox} to the {@link QuadTree}.
   *
   * @param boundingBox the one to add
   */
  public void add(final BoundingBox boundingBox) {
    root = addRecursive(root, boundingBox);
  }

  private Node addRecursive(final Node current, final BoundingBox boundingBox) {
    if (current == null) {
      return new Node(boundingBox);
    }

    if (goesNorthEast(current, boundingBox)) {
      current.setNorthEast(addRecursive(current.getNorthEast(), boundingBox));
      return current;
    } else if (goesNorthWest(current, boundingBox)) {
      current.setNorthWest(addRecursive(current.getNorthWest(), boundingBox));
      return current;
    } else if (goesSouthEast(current, boundingBox)) {
      current.setSouthEast(addRecursive(current.getSouthEast(), boundingBox));
      return current;
    } else if (goesSouthWest(current, boundingBox)) {
      current.setSouthWest(addRecursive(current.getSouthWest(), boundingBox));
      return current;
    }

    throw new RuntimeException("This should be impossible to reach... :/");
  }

  private boolean goesNorthEast(final Node current, final BoundingBox boundingBox) {
    return boundingBox.getCenter().getX() > current.getBoundingBox().getCenter().getX()
        && boundingBox.getCenter().getY() > current.getBoundingBox().getCenter().getY();
  }

  private boolean goesNorthWest(final Node current, final BoundingBox boundingBox) {
    return boundingBox.getCenter().getX() <= current.getBoundingBox().getCenter().getX()
        && boundingBox.getCenter().getY() > current.getBoundingBox().getCenter().getY();
  }

  private boolean goesSouthEast(final Node current, final BoundingBox boundingBox) {
    return boundingBox.getCenter().getX() > current.getBoundingBox().getCenter().getX()
        && boundingBox.getCenter().getY() <= current.getBoundingBox().getCenter().getY();
  }

  private boolean goesSouthWest(final Node current, final BoundingBox boundingBox) {
    return boundingBox.getCenter().getX() <= current.getBoundingBox().getCenter().getX()
        && boundingBox.getCenter().getY() <= current.getBoundingBox().getCenter().getY();
  }
}
