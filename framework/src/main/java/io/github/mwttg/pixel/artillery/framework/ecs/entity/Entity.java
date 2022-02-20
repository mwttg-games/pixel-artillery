package io.github.mwttg.pixel.artillery.framework.ecs.entity;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public abstract class Entity {

  private UUID id;

  public Entity() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Entity entity)) {
      return false;
    }
    return id.equals(entity.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Entity.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .toString();
  }
}
