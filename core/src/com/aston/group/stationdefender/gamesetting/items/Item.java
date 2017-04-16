package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.gamesetting.items.helpers.Items;
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
    private final SpriteBatch batch = GameEngine.getBatch();
    private final String name;
    private final int id;
    private final boolean placeable;
    private final Texture texture;
    private final Items sku;
    private final int width = 32;
    private final int height = 32;
    private int cost = 0;
    private int value = 0;
    private int health = 0;
    private boolean justSpawned;
    private int x, y;

    /**
     * Construct a new Item with a name
     *
     * @param name The name of the Item
     */
    public Item(String name, int id, int cost, int health, int value, int textureId, boolean placeable, Items sku) {
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.health = health;
        this.value = value;
        this.placeable = placeable;
        this.sku = sku;
        texture = TextureManager.INSTANCE.loadTexture(textureId);
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
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    /**
     * Abstract method to return whether the Item can be placed on the Level
     *
     * @return An Actor that can be placed on the Level, null if the Actor cannot be placed on the Level
     */
    public Actor getPlaceableActor() {
        switch (sku) {
            case TURRET:
                return new Weapon();
            case CLOSE_COMBAT_WEAPON:
                return new Weapon("Close Combat Weapon", 25, 50.0, 2.0, Constants.UNIT_HEALTH, 4.0, 7.0, 2.0, 15, 25, 25);
            case RAPID_FIRE_WEAPON:
                return new Weapon("Rapid Fire Weapon", 25, 5.0, 15.0, 10, 10, 0.5, 1.0, 15, 25, 13);
            default:
                return null;
        }
    }

    /**
     * Returns the ID of the Item
     *
     * @return The ID of the item
     */
    public int getId() {
        return id;
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
     * Returns the width of the Item
     *
     * @return The width of the Item
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the Item
     *
     * @return The height of the Item
     */
    public int getHeight() {
        return height;
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
     * Returns the Item ID and Name
     *
     * @return The Item ID and Name
     */
    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    /**
     * Returns the unique SKU identifier of the Item
     *
     * @return The unique SKU identifier of the Item
     */
    public Items getSku() {
        return sku;
    }
}