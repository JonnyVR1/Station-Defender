package com.aston.group.stationdefender.gamesetting.items.helpers;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * This class is responsible for handling what Items are created
 *
 * @author Mohammad Foysal
 */
public enum ItemFactory {
    CREDIT,
    WEAPON,
    CLOSE_COMBAT_WEAPON,
    RAPID_FIRE_WEAPON,
    HEALTH,
    /*BANDAGES,
    BRICKS,
    CEMENT,
    WATER,
    WOOD_BLOCK,*/
    UNKNOWN;

    /**
     * Returns a new Item from the given list
     *
     * @param itemFactory The enum list of Items that can be created
     * @return The new Item to be placed within a Lane
     */
    public static Item getItem(ItemFactory itemFactory) {
        Item item = null;
        switch (itemFactory) {
            case CREDIT:
                item = new Item("Credits", 0, 0, 10, TextureManager.ITEM_CREDIT, true, CREDIT);
                break;
            case WEAPON:
                item = new Item("Weapon", 10, 0, 0, TextureManager.WEAPON, true, WEAPON);
                break;
            case CLOSE_COMBAT_WEAPON:
                item = new Item("Close Combat Weapon", 15, 0, 0, TextureManager.CLOSE_COMBAT_WEAPON, true, CLOSE_COMBAT_WEAPON);
                break;
            case RAPID_FIRE_WEAPON:
                item = new Item("Rapid Fire Weapon", 15, 0, 0, TextureManager.RAPID_FIRE_WEAPON, true, RAPID_FIRE_WEAPON);
                break;
            case HEALTH:
                item = new Item("Health", 3, 25, 0, TextureManager.ITEM_HEALTH, true, HEALTH);
                break;
            /*case BANDAGES:
                item = new Item("Bandages", 3, 5, 0, TextureManager.ITEM_BANDAGES, true, BANDAGES);
            case BRICKS:
                item = new Item("Bricks", 5, 0, 0, TextureManager.ITEM_BRICKS, true, BRICKS);
            case CEMENT:
                item = new Item("Cement", 8, 0, 0, TextureManager.ITEM_CEMENT, true, CEMENT);
            case WATER:
                item = new Item("Water", 3, 0, 0, TextureManager.ITEM_WATER, true, WATER);
            case WOOD_BLOCK:
                item = new Item("Wooden Blocks", 3, 0, 0, TextureManager.ITEM_WOOD, true, WOOD_BLOCK);*/
            case UNKNOWN:
                return new Item("Empty", 0, 0, 0, TextureManager.UNKNOWN, false, ItemFactory.UNKNOWN);
        }
        return item;
    }

    /**
     * Returns a new Item at random
     *
     * @return The new Item to be placed within a Lane
     */
    public static Item getRandomItem() {
        int rand = (int) (Math.random() * (values().length));
        return getItem(values()[rand]);
    }

    /**
     * Return either a new Item or null at random
     *
     * @return A new Item at random or null if no Item is to be returned
     */
    public static Item getItemByChance() {
        int rand = (int) (Math.random() * 100);
        if (rand <= 35) {
            return getRandomItem();
        } else {
            return null;
        }
    }
}