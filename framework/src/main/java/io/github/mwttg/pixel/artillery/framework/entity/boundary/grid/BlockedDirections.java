package io.github.mwttg.pixel.artillery.framework.entity.boundary.grid;

/**
 * A model class for collision detection to persist the directions, which are blocked.
 */
public record BlockedDirections(boolean left, boolean top, boolean right, boolean bottom) {
}
