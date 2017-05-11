package com.aston.group.stationdefender.gamesetting.items.helpers;

import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class allows the Items to be stacked into a QuickSlot Inventory
 *
 * @author Mohammed Foysal
 */
public class ItemStack implements Iterable<Item> {
    private final int maxItems = 64;
    private final List<Item> items = new ArrayList<>();
    private final SpriteBatch batch = GameEngine.getBatch();
    private final BitmapFont font = FontManager.getFont(16);
    private final int width = 32;
    private final int height = 32;
    private final String itemName;
    private int x, y;

    /**
     * Construct a new ItemStack with a specific Item
     *
     * @param item The specific Item to add to the ItemStack
     */
    public ItemStack(Item item) {
        itemName = item.getName();
        addItem(item);
    }

    /**
     * Adds an item to the ItemStack
     *
     * @param item The Item to add to the ItemStack
     */
    public void addItem(Item item) {
        if (items.size() < maxItems)
            items.add(item);
    }

    /**
     * Remove an Item from the ItemStack
     *
     * @param item The Item to remove from the Stack
     */
    public void removeItem(Item item) {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item chosenItem = iterator.next();
            if (chosenItem != null && chosenItem.getName().equals(item.getName())) {
                iterator.remove();
                return;
            }
        }
    }

    /**
     * Gets an Item from the ItemStack
     *
     * @return The Item if it is in the ItemStack, null if it is not
     */
    public Item getItem() {
        if (!items.isEmpty())
            return items.get(items.size() - 1);
        else
            return null;
    }

    /**
     * Return the Name of the Item the ItemStack holds
     *
     * @return The name of the Item the ItemStack holds
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Render the ItemStack
     */
    public void render() {
        if (!items.isEmpty() && items.get(0).getTexture() != null) {
            batch.begin();
            batch.draw((items.get(0)).getTexture(), x, y, width, height);
            batch.end();
            batch.begin();
            font.draw(batch, Integer.toString(items.size()), x + 20, y + 10);
            batch.end();
        }

        if (isColliding(Input.getX(), Input.getY())) {
            String name;
            if (getItem() == null) {
                name = "Empty";
            } else {
                name = getItem().getName();
            }
            batch.begin();
            font.setColor(Color.BLACK);
            font.draw(batch, name, x - 10, y + height + 19);
            font.setColor(Color.WHITE);
            font.draw(batch, name, x - 10, y + height + 20);
            batch.end();
        }
    }

    /**
     * Sets the X co-ordinate of the ItemStack
     *
     * @param x The X co-ordinate of the ItemStack
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Y co-ordinate of the ItemStack
     *
     * @param y The Y co-ordinate of the ItemStack
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    /**
     * Returns whether the ItemStack is full or not
     *
     * @return Whether the ItemStack is full or not
     */
    public boolean isFull() {
        return items.size() >= maxItems;
    }

    /**
     * Checks whether the params collides with the QuickSlot box
     *
     * @param x The x to be compared with the QuickSlot
     * @param y The y to be compared with the QuickSlot
     * @return isColliding - returns true if params collide, false if not
     */
    private boolean isColliding(int x, int y) {
        return x + 1 > this.x && x < this.x + width && y + 1 > this.y && y < this.y + height;
    }
}