package io.github.mwttg.pixel.artillery.framework.entity.boundary.grid;

import io.github.mwttg.pixel.artillery.common.BoundingBox;
import java.util.Optional;

record Neighbours(Optional<BoundingBox> topLeft, Optional<BoundingBox> top,
                  Optional<BoundingBox> topRight, Optional<BoundingBox> left,
                  Optional<BoundingBox> right, Optional<BoundingBox> bottomLeft,
                  Optional<BoundingBox> bottom, Optional<BoundingBox> bottomRight) {
}
