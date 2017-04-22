package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.actors.helpers.ParticleEffectHelper;
import com.aston.group.stationdefender.callbacks.UnitCallback;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.MouseInput;
import com.aston.group.stationdefender.utils.TextureManager;
import com.aston.group.stationdefender.utils.hud.Hud;
import com.aston.group.stationdefender.utils.hud.HudElement;
import com.aston.group.stationdefender.utils.hud.HudUnit;
import com.aston.group.stationdefender.utils.indicators.IndicatorManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Abstract superclass inherited by Weapon and Alien subclasses.
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public abstract class Unit implements Actor {
    final double speed; //How many tiles it can move per "tick".
    final IndicatorManager indicatorManager = new IndicatorManager();
    final int width; //Unit's width
    final int height; //Unit's height
    final Texture texture;
    final String name; //Name of the type of unit.
    final SpriteBatch batch = GameEngine.getBatch();
    private final double rateOfFire; //How many times the unit fires per "tick".
    private final double range; //How many tiles forward the Unit can fire.
    private final ParticleEffectHelper particleEffectHelper = new ParticleEffectHelper();
    private final double chanceToHit; //Chance of a hit
    private final boolean facingLeft; //Whether the Unit is facing left or not
    double damage; //How much damage each successful hit causes.
    int x; //Unit's position on the X-Axis
    int y; //Unit's position on the Y-Axis
    boolean isAdjacent; //Checks if the Unit is adjacent to any other unit.  This information is retrieved from the Level.
    Actor adjacentActor; //The Unit that this Unit is adjacent to.
    private UnitCallback unitCallback; //The UnitCallBack used for the Unit
    private boolean exists = true; //Whether the Unit is alive or dead.
    private double health; //How much damage the Unit can take before being destroyed.
    private long lastTime; //The last time a unit fired a missile.
    private HudElement hudElement;

    /**
     * Construct a new Unit with given name, speed, damage, rateOfFile, health, range, chance to hit width, height,
     * facing left and texture parameters
     *
     * @param name        The name of the Unit
     * @param speed       The speed of the Unit
     * @param damage      The damage the Unit inflicts
     * @param rateOfFire  The rate of fire of the Unit
     * @param health      The health of the Unit
     * @param range       The range of the Unit
     * @param chanceToHit The chance of the Weapon to score a hit
     * @param width       The width of the Unit
     * @param height      The height of the Unit
     * @param facingLeft  Whether the Unit is facing left or not
     * @param texture     The texture graphic of the Unit
     */
    Unit(String name, double speed, double damage, double rateOfFire, double health, double range, double chanceToHit, int width, int height, boolean facingLeft, TextureManager texture) {
        this.name = name;
        this.speed = speed;
        this.damage = damage;
        this.rateOfFire = rateOfFire;
        this.health = health;
        this.range = range;
        this.width = width;
        this.height = height;
        this.chanceToHit = chanceToHit;
        this.facingLeft = facingLeft;
        this.texture = TextureManager.loadTexture(texture);
    }

    /**
     * Returns the damage that the Unit inflicts
     *
     * @return The damage that the unit inflicts.
     */
    public double getDamage() {
        return damage;
    }

    @Override
    public abstract void render(float delta);

    @Override
    public boolean getExists() {
        return exists;
    }

    public void destroy() {
        if (hudElement != null) {
            Hud.removeHudElement(hudElement);
            hudElement = null;
        }
        exists = false;
        particleEffectHelper.destroy(x, y);
    }

    @Override
    public void dispose() {
        indicatorManager.dispose();
        texture.dispose();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUnit() {
        return true;
    }

    /**
     * Returns the rate of fire of the Unit
     *
     * @return The rate of fire of the Unit.
     */
    public double getRateOfFire() {
        return rateOfFire;
    }

    /**
     * Returns the current health of the Unit
     *
     * @return The current health of the Unit.
     */
    public double getHealth() {
        return health;
    }

    /**
     * Returns the range that the Unit can fire
     *
     * @return The range that the Unit can fire.
     */
    public double getRange() {
        return range;
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
        indicatorManager.addIndicator('-' + Integer.toString((int) damage), Color.RED);
    }

    /**
     * Checks if the Unit is adjacent to another entity.
     *
     * @return Boolean which says if the Unit is adjacent to another entity.
     */
    boolean isAdjacent() {
        return isAdjacent;
    }

    /**
     * Sets whether the Unit is adjacent to another object
     *
     * @param isAdjacent Whether the Unit is adjacent to another object
     */
    public void setIsAdjacent(boolean isAdjacent) {
        this.isAdjacent = isAdjacent;
    }

    /**
     * Returns the Actor adjacent to the Unit
     *
     * @return The Actor that the Unit is adjacent to, null if no Actor is adjacent to the Unit
     */
    Actor getAdjacentActor() {
        if (isAdjacent()) {
            return adjacentActor;
        } else {
            return null;
        }
    }

    /**
     * Sets the Actor adjacent to the Unit
     *
     * @param actor The Actor that the Unit is adjacent to
     */
    public void setAdjacentActor(Actor actor) {
        if (actor != null) {
            adjacentActor = actor;
            isAdjacent = true;
        } else {
            adjacentActor = null;
            isAdjacent = false;
        }
    }

    /**
     * Returns whether there is a Unit adjacent to the current Unit
     *
     * @param actor The Unit to check whether Unit is adjacent to the current Unit
     * @return true if there is a Unit adjacent to the current Unit,
     * false if there is not a Unit adjacent to the current Unit
     */
    public boolean isUnitAdjacent(Actor actor) {
        if (actor == this)
            return false;
        if (facingLeft) {
            return actor.getX() + actor.getWidth() > x && actor.getX() < x + width && y + height > y && y < y + height;
        } else {
            return actor.getX() + actor.getWidth() > x && actor.getX() < x + width && y + height > y && y < y + height;
        }
    }

    /**
     * Checks if the Health of the Unit is less than 1.
     *
     * @return true if health is above 0, false if health is 0 or below
     */
    boolean checkIsNotZeroHealth() {
        return health >= 1;
    }

    /**
     * Makes the Unit fire a Projectile using a Weapon
     *
     * @return The total damage done by the number of fires
     */
    double fire() {
        Random rng = new Random();
        int hit = 0;
        for (int i = 0; i < rateOfFire; i++) {
            if (chanceToHit >= rng.nextInt(10)) {
                hit++;
            }
        }
        return (hit * damage);
    }

    /**
     * Returns the height of the Unit
     *
     * @return The height of the Unit
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the Unit
     *
     * @return The width of the Unit
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the X co-ordinate of the Unit
     *
     * @return The X co-ordinate of the Unit
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X co-ordinate of the Unit
     *
     * @param x The X co-ordinate of the Unit
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y co-ordinate of the Unit
     *
     * @return The Y co-ordinate of the Unit
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y co-ordinate of the Unit
     *
     * @param y The Y co-ordinate of the Unit
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns whether the Unit is facing left or not
     *
     * @return true if the Unit is facing left, false if the Unit is facing right
     */
    public boolean isFacingLeft() {
        return facingLeft;
    }

    /**
     * Sets the UnitCallBack for the Unit
     *
     * @param unitCallback The UnitCallBack to set to the Unit
     */
    public void setUnitCallback(UnitCallback unitCallback) {
        this.unitCallback = unitCallback;
    }

    /**
     * Render the particle effect
     *
     * @param delta The time in seconds since the last render
     * @param batch The SpriteBatch to render the particle effect on
     */
    void renderParticleEffect(float delta, Batch batch) {
        particleEffectHelper.renderParticleEffect(delta, batch, x, y);
    }

    /**
     * Helper method for the RapidFire classes
     *
     * @return true if the class should overload, false if it shouldn't
     */
    boolean rapidFireHelper() {
        boolean result = false;
        double damageDealt = fire();
        if ((damageDealt / getDamage()) == getRateOfFire()) {
            result = true;
        } else {
            adjacentActor.takeDamage(damageDealt);
        }
        return result;
    }

    /**
     * Helper method that lets units fire a missile
     *
     * @param dXPos  The difference in X position for the start of the missile
     * @param dSpeed The difference in speed for the missile
     */
    void unitFireHelper(int dXPos, int dSpeed) {
        if (unitCallback != null && System.currentTimeMillis() - lastTime >= (10000 / rateOfFire)) {
            unitCallback.onFire(x + dXPos, y + 35, speed + dSpeed, damage);
            lastTime = System.currentTimeMillis();
        }
    }

    /**
     * Checks whether the MouseInput is colliding with the Weapon.
     * If it does then create a new HUD element for the Weapon
     */
    void checkInput() {
        if (MouseInput.isColliding(x, y, width, height)) {
            if (hudElement == null) {
                hudElement = new HudUnit(this, x, y);
                Hud.addHudElement(hudElement);
            }
        } else if (Hud.isNotColliding()) {
            Hud.removeHudElement(hudElement);
            hudElement = null;
        }
    }
}