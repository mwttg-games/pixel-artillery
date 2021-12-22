package io.github.mwttg.pixel.artillery.framework.entity.drawable;

import io.github.mwttg.pixel.artillery.common.ReadFile;
import io.github.mwttg.pixel.artillery.common.SpriteAnimationData;
import io.github.mwttg.pixel.artillery.common.SpriteData;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL40;

/**
 * One of the {@link Drawable} implementations. A Sprite with an animation.
 */
public class SpriteAnimation extends AbstractTexturedSprite implements Drawable {

  private int delay;
  private int maxAnimationSteps;
  private int currentAnimationStep;
  private long lastTick;

  /**
   * The Constructor (calls the Constructor of the abstract parent class).
   *
   * @param jsonFile    the .json file which contains data for the geometry (vertices) and texture
   *                    coordinates (and some data for the animation)
   * @param textureFile the .png file for the texture
   */
  public SpriteAnimation(final String jsonFile, final String textureFile) {
    super(jsonFile, textureFile);
  }

  /**
   * The Constructor (calls the Constructor of the abstract parent class).
   * This Constructor can be used to link the animation with a name ({@link String}).
   *
   * @param name        The name of the animation
   * @param jsonFile    the .json file which contains data for the geometry (vertices) and texture
   *                    coordinates (and some data for the animation)
   * @param textureFile the .png file for the texture
   */
  public SpriteAnimation(final String name, final String jsonFile, final String textureFile) {
    super(name, jsonFile, textureFile);
  }

  /**
   * This is the implementation for extracting the {@link SpriteData}. Also some init stuff is
   * happening which should be done by the Constructor normally. Unfortunately first call of the
   * Constructor has to be super (call of the abstract class constructor), to avoid reading the
   * .json file two time the init stuff is now here.
   *
   * @param jsonFile the .json file of the Sprite. It must include at least the some data for
   *                 geometry (vertices) and texture coordinates.
   * @return the {@link SpriteData}
   */
  @Override
  SpriteData extractSpriteData(String jsonFile) {
    final var spriteAnimationData = ReadFile.jsonFromResources(jsonFile, SpriteAnimationData.class);
    this.delay = spriteAnimationData.delayInMs();
    this.maxAnimationSteps = spriteAnimationData.animationSteps();
    this.currentAnimationStep = 0;
    this.lastTick = System.currentTimeMillis();

    return spriteAnimationData.spriteData();
  }

  /**
   * This draws/renders the {@link SpriteAnimation} to the Screen.
   *
   * @param model      the model matrix
   * @param view       the view matrix (camera)
   * @param projection the projection matrix
   */
  @Override
  public void draw(final Matrix4f model, final Matrix4f view, final Matrix4f projection) {
    GL40.glBindVertexArray(getVertexArrayObjectId());
    GL40.glUseProgram(getShaderProgramId());
    enableVertexAttribArray();

    getUniforms().upload(model, view, projection, getTextureId());
    final var first = getFirstVertexOfPlane();
    // count is 6 because every plane is created from 2 triangles (6 vertices complete)
    GL40.glDrawArrays(GL40.GL_TRIANGLES, first, 6);

    disableVertexAttribArray();
  }

  // calculates the animation (which step is the current -> which is the index of the first vertex)
  private int getFirstVertexOfPlane() {
    final var now = System.currentTimeMillis();

    if (now - lastTick > delay) {
      lastTick = System.currentTimeMillis();
      currentAnimationStep++;
    }

    if (currentAnimationStep >= maxAnimationSteps) {
      currentAnimationStep = 0;
    }

    return currentAnimationStep * 6;
  }
}
