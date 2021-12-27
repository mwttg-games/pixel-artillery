package io.github.mwttg.pixel.artillery.framework.entity.boundary.quadtree;

import io.github.mwttg.pixel.artillery.common.BoundingBox;

/**
 * Represents a node inside the {@link QuadTree}.
 */
public class Node {

  private final BoundingBox boundingBox;
  private Node northEast;
  private Node northWest;
  private Node southEast;
  private Node southWest;

  public Node(final BoundingBox boundingBox) {
    this.boundingBox = boundingBox;
  }

  public BoundingBox getBoundingBox() {
    return boundingBox;
  }

  public Node getNorthEast() {
    return northEast;
  }

  public Node getNorthWest() {
    return northWest;
  }

  public Node getSouthEast() {
    return southEast;
  }

  public Node getSouthWest() {
    return southWest;
  }

  public void setNorthEast(Node northEast) {
    this.northEast = northEast;
  }

  public void setNorthWest(Node northWest) {
    this.northWest = northWest;
  }

  public void setSouthEast(Node southEast) {
    this.southEast = southEast;
  }

  public void setSouthWest(Node southWest) {
    this.southWest = southWest;
  }
}
