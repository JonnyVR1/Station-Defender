package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemWoodenBlocks represents a wooden block Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemWoodenBlocks extends Item {

    /**
     * Construct a new Wooden blocks Item
     */
    public ItemWoodenBlocks() {
        super("Wooden Blocks");
        id = 12;
        cost = 3;
        texture = TextureManager.INSTANCE.loadTexture(27);
        placeable = false;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Actor getPlaceableActor() {
        // TODO: create a Wooden Blocks actor
        return null;
    }
}