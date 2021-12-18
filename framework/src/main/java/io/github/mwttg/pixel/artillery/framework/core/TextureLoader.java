package io.github.mwttg.pixel.artillery.framework.core;

import io.github.mwttg.pixel.artillery.framework.CleanUpUtilities;
import java.io.File;
import org.lwjgl.opengl.GL40;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Functionality for creating textures.
 */
public final class TextureLoader {

  private static final Logger LOG = LoggerFactory.getLogger(TextureLoader.class);

  /**
   * Creates a texture from an image file from the resource directory.
   *
   * @param filename of that image file
   * @return the OpenGL id
   */
  public static int createFromResource(final String filename) {
    LOG.debug("Load Texture: '{}'.", filename);

    final var stack = MemoryStack.stackPush();
    final var widthBuffer = stack.mallocInt(1);
    final var heightBuffer = stack.mallocInt(1);
    final var colorBuffer = stack.mallocInt(1);

    final var classLoader = ClassLoader.getSystemClassLoader();
    final var url = classLoader.getResource(filename);
    if (url == null) {
      throw new IllegalArgumentException("Texture '%s' does not exist");
    }
    final var file = new File(url.getFile()).getAbsolutePath();
    STBImage.stbi_set_flip_vertically_on_load(true);
    final var buffer = STBImage.stbi_load(file, widthBuffer, heightBuffer, colorBuffer, 4);
    if (buffer == null) {
      throw new RuntimeException(
          "Image file '%s' not loaded: %S".formatted(filename, STBImage.stbi_failure_reason()));
    }

    final var width = widthBuffer.get();
    final var height = heightBuffer.get();
    final var textureId = GL40.glGenTextures();
    CleanUpUtilities.addTextureId(textureId);

    GL40.glBindTexture(GL40.GL_TEXTURE_2D, textureId);
    GL40.glPixelStorei(GL40.GL_UNPACK_ALIGNMENT, 1);
    GL40.glTexImage2D(GL40.GL_TEXTURE_2D, 0, GL40.GL_RGBA, width, height, 0, GL40.GL_RGBA,
        GL40.GL_UNSIGNED_BYTE, buffer);
    // NEAREST Filtering instead of LINEAR for sharp edges
    GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_MIN_FILTER,
        GL40.GL_NEAREST_MIPMAP_NEAREST);
    GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_MAG_FILTER, GL40.GL_NEAREST);
    GL40.glGenerateMipmap(GL40.GL_TEXTURE_2D);
    STBImage.stbi_image_free(buffer);

    return textureId;
  }
}

