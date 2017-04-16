package com.aston.group.stationdefender.gamesetting.items.helpers;

import com.aston.group.stationdefender.gamesetting.items.Item;

/**
 * This class is responsible for handling what Items are created
 *
 * @author Mohammad Foysal
 */
public class ItemFactory {

    /**
     * Returns a new Item from the given list
     *
     * @param items The enum list of Items that can be created
     * @return The new Item to be placed within a Lane
     */
    public static Item getItem(Items items) {
        switch (items) {
            case CREDIT:
                return new Item("Credits", 0, 0, 10, 10, true, Items.CREDIT);
            case TURRET:
                return new Item("Weapon", 10, 0, 0, 8, true, Items.TURRET);
            case CLOSE_COMBAT_WEAPON:
                return new Item("Close Combat Weapon", 15, 0, 0, 25, true, Items.CLOSE_COMBAT_WEAPON);
            case RAPID_FIRE_WEAPON:
                return new Item("Rapid Fire Weapon", 15, 0, 0, 13, true, Items.RAPID_FIRE_WEAPON);
            case HEALTH:
                return new Item("Health", 3, 25, 0, 24, true, Items.HEALTH);
            /*case BANDAGES:
                return new Item("Bandages", 3, 5, 0, 26, true, Items.BANDAGES);
            case BRICKS:
                return new Item("Bricks", 5, 0, 0, 28, true, Items.BRICKS);
            case CEMENT:
                return new Item("Cement", 8, 0, 0, 30, true, Items.CEMENT);
            case WATER:
                return new Item("Water", 3, 0, 0, 29, true, Items.WATER);
            case WOOD_BLOCK:
                return new Item("Wooden Blocks", 3, 0, 0, 27, true, Items.WOOD_BLOCK);*/
            default:
                return new Item("Empty", 0, 0, 0, 0, false, Items.UNKNOWN);
        }
    }

    /**
     * Returns a new Item at random
     *
     * @return The new Item to be placed within a Lane
     */
    public static Item getRandomItem() {
        int rand = (int) (Math.random() * (Items.values().length));
        return ItemFactory.getItem(Items.values()[rand]);
    }

    /**
     * Return either a new Item or null at random
     *
     * @return A new Item at random or null if no Item is to be returned
     */
    public static Item getItemByChance() {
        int rand = (int) (Math.random() * 100);
        if (rand <= 35) {
            return ItemFactory.getRandomItem();
        } else {
            return null;
        }
    }
}