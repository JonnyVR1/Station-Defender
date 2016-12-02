package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.SoundManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Tower is the object which the Humans defend,
 * and which the Aliens attack.
 *
 * @author Jonathon Fitch, Peter Holmes
 */
public class Tower implements Actor {
    private final int height;
    private final int width;
    private final int x;
    private final int y;
    private final SpriteBatch batch;
    private final Texture texture;
    private final BitmapFont font;
    private int health;
    private boolean exists;
    private ParticleEffect particleEffect;

    /**
     * Constructs a new Tower
     *
     * @param x      The X co-ordinate of the Tower
     * @param y      The Y co-ordinate of the Tower
     * @param width  The width of the Tower
     * @param height The height of the Tower
     */
    public Tower(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        exists = true;
        health = Constants.TOWER_HEALTH;
        texture = TextureManager.INSTANCE.loadTexture(6);
        batch = new SpriteBatch();
        font = FontManager.getFont(16);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        if (particleEffect != null) {
            particleEffect.update(delta);
            particleEffect.setPosition(x, y);
            particleEffect.draw(batch);
            if (particleEffect.isComplete())
                particleEffect.dispose();
        }
        batch.draw(texture, x, y, width, height);
        font.setColor(Color.BLACK);
        font.draw(batch, "Health: " + health, (Gdx.graphics.getWidth() / 2) - 499, Gdx.graphics.getHeight() - 50);
        font.setColor(Color.WHITE);
        font.draw(batch, "Health: " + health, (Gdx.graphics.getWidth() / 2) - 500, Gdx.graphics.getHeight() - 50);
        batch.end();
    }

    @Override
    public void act(float delta) {
    }

    /**
     * Check if an objects X &amp; Y co-ordinates or width &amp; height
     * overlaps the Towers X &amp; Y co-ordinates, or width &amp; height
     *
     * @param x      The X co-ordinate of the object to check
     * @param y      The Y co-ordinate of the object to check
     * @param width  The width of the object to check
     * @param height The height of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    public boolean isColliding(int x, int y, int width, int height) {
        return x + width > this.x && x < this.x + this.width && y + height > this.y && y < this.y + this.height;
    }

    /**
     * Determines what happens when the tower gets destroyed.
     */
    @Override
    public void destroy() {
        exists = false;
        SoundManager.INSTANCE.playSound(3);
        particleEffect = TextureManager.INSTANCE.loadParticleEffect(1);
        particleEffect.getEmitters().first().setPosition(x, y);
        particleEffect.start();
    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
    }

    @Override
    public boolean getExists() {
        return exists;
    }

    /**
     * Sets the existence state of the Unit.
     *
     * @param exists The existence state of the Unit
     */
    public void setExists(boolean exists) {
        this.exists = exists;
    }

    /**
     * Returns the height of the Tower
     *
     * @return The height of the Tower
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the Tower
     *
     * @return The width of the Tower
     */
    public int getWidth() {
        return width;
    }

    /**
     * Causes the Units health to lower by the damage parameter.
     *
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage) {
        if ((health - damage) <= 0) {
            destroy();
            health = 0;
        } else
            health -= damage;
    }

    /**
     * Returns the health of the Tower
     *
     * @return The health of the Tower
     */
    public int getHealth() {
        return health;
    }
}