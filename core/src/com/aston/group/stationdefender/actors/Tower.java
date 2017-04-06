package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.actors.helpers.ParticleEffectHelper;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.aston.group.stationdefender.utils.indicators.IndicatorManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Tower is the object which the Humans defend,
 * and which the Aliens attack.
 *
 * @author Jonathon Fitch, Peter Holmes
 */
public class Tower implements Actor {
    private final int height = 400;
    private final int width = 100;
    private final SpriteBatch batch = GameEngine.getBatch();
    private final Texture texture = TextureManager.INSTANCE.loadTexture(6);
    private final BitmapFont font = FontManager.getFont(16);
    private final ParticleEffectHelper particleEffectHelper = new ParticleEffectHelper();
    private final IndicatorManager indicatorManager = new IndicatorManager();
    private int x = 0;
    private int y = 100;
    private int health = Constants.TOWER_HEALTH;
    private boolean exists = true;

    @Override
    public void render(float delta) {
        batch.begin();
        particleEffectHelper.renderParticleEffect(delta, batch, x, y);
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

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
        indicatorManager.dispose();
    }

    @Override
    public boolean getExists() {
        return exists;
    }

    /**
     * Causes the Units health to lower by the damage parameter.
     *
     * @param damage Causes the Unit's health to deplete.
     */
    public void takeDamage(double damage) {
        indicatorManager.addIndicator("-" + Integer.toString((int) damage), Color.RED);
        if (health - damage <= 0) {
            health = 0;
            exists = false;
            particleEffectHelper.destroy(x, y);
        } else
            health -= damage;
    }

    /**
     * Add health the the Tower
     *
     * @param health The amount of health to add to the Tower
     */
    public void addHealth(int health) {
        indicatorManager.addIndicator("+" + Integer.toString( health), Color.YELLOW);
        this.health += health;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}