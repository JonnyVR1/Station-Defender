package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.gamesetting.items.helpers.ItemFactory;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Item class represents an in-game item that the player
 * can pick up, buy for credits, and use
 *
 * @author Mohammed Foysal
 */
public class Item {
    private static final int width = 32;
    private static final int height = 32;
    private final SpriteBatch batch = GameEngine.getBatch();
    private final String name;
    private final boolean placeable;
    private final Texture texture;
    private final ItemFactory sku;
    private final int cost;
    private final int value;
    private final int health;
    private boolean justSpawned;
    private int x, y;

    /**
     * Construct a new Item with a name
     *
     * @param name      The name of the Item
     * @param cost      The cost of the Item
     * @param health    The additional health of the Item
     * @param value     The money value of the Item
     * @param texture   The Texture ID to use to get the Item graphic
     * @param placeable Whether the Item is placeable on a Tile or not
     * @param sku       The unique SKU of the Item
     */
    public Item(String name, int cost, int health, int value, TextureManager texture, boolean placeable, ItemFactory sku) {
        this.name = name;
        this.cost = cost;
        this.health = health;
        this.value = value;
        this.placeable = placeable;
        this.sku = sku;
        this.texture = TextureManager.loadTexture(texture);
    }

    /**
     * Returns the width of the Item
     *
     * @return The width of the Item
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Returns the height of the Item
     *
     * @return The height of the Item
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Render the Item.
     */
    public void render() {
        if (texture != null) {
            batch.begin();
            batch.draw(texture, x + 2, y + 1, width, height);
            batch.end();
            batch.begin();
            batch.draw(texture, x, y, width, height);
            batch.end();
        }
    }

    /**
     * Allows the player to use the Item
     *
     * @param itemCallback The ItemCallBack associated with the Item
     */
    public void useItem(ItemCallback itemCallback) {
        itemCallback.onUse(placeable, cost, value, health);
    }

    /**
     * Abstract method to return whether the Item can be placed on the Level
     *
     * @return An Actor that can be placed on the Level, null if the Actor cannot be placed on the Level
     */
    public Actor getPlaceableActor() {
        switch (sku) {
            case WEAPON:
                return new Weapon();
            case CLOSE_COMBAT_WEAPON:
                return new Weapon("Close Combat Weapon", 25, 50.0, 2.0, Constants.UNIT_HEALTH, 4.0, 7.0, 2.0, 15, 25, TextureManager.CLOSE_COMBAT_WEAPON);
            case RAPID_FIRE_WEAPON:
                return new Weapon("Rapid Fire Weapon", 25, 5.0, 15.0, 10, 10, 0.5, 1.0, 15, 25, TextureManager.RAPID_FIRE_WEAPON);
            default:
                return null;
        }
    }

    /**
     * Returns the name of the Item
     *
     * @return The name of the Item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the texture of the Item
     *
     * @return The Texture of the Item
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Returns whether the Item has just been created or not
     *
     * @return Whether the Item has just been created or not
     */
    public boolean isJustSpawned() {
        return justSpawned;
    }

    /**
     * Sets whether the Item has just been created or not
     */
    public void setJustSpawned() {
        justSpawned = true;
    }

    /**
     * Returns the X co-ordinate of the Item
     *
     * @return The X co-ordinate of the Item
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X co-ordinate of the Item
     *
     * @param x The X co-ordinate of the Item
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y co-ordinate of the Item
     *
     * @return The Y co-ordinate of the Item
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y co-ordinate of the Item
     *
     * @param y The Y co-ordinate of the Item
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the cost of the Item
     *
     * @return The cost of the Item
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the value of the Item
     *
     * @return The value of the Item
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the health of the Item
     *
     * @return The health of the Item
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the unique SKU identifier of the Item
     *
     * @return The unique SKU identifier of the Item
     */
    public ItemFactory getSku() {
        return sku;
    }
}